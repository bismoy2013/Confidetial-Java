package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.controller.AccessController;
import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.dao.UserTokenDAO;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.UserToken;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;
import in.fissionlabs.kpinsights.model.dto.UserDTO;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;
import in.fissionlabs.kpinsights.security.RandomStringGenerator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("AuthenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserDAO userDAO;

	@Autowired
	UserTokenDAO userTokenDAO;

	@Autowired
	GenericQueryRestrictionService restrictionService;

	private HashSet<SearchCriteria> restrictions = null;
	
	private static Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);

	// Status information messages
	private static final String LOGIN_ERROR= "Error logging User in. Invalid username and/or password.";
	private static final String LOGIN_SUCCESS= "User authenticated successfully.";
	private static final String LOGOUT_ERROR= "Error logging user out. Please try again.";
	private static final String LOGOUT_SUCCESS= "User logged out successfully.";

	@Override
	public DisplaySingleResult authenticateUser(String emailAddress, String password) {

		// Generate default DisplaySingleResult, Status, UserToken and User objects. Update them later if necessary.
		DisplaySingleResult result = new DisplaySingleResult();
		Status status = new Status(true,LOGIN_ERROR);
		UserDTO userDTO = null;
		User user = null;
		String authenticationToken = null;


		restrictions = restrictionService.resetSearchParameters(GenericQueryRestrictionService.EMAIL_ADDRESS, emailAddress, true);

		user = (User) userDAO.findByCriteria(User.class, restrictions, true);

		if(!StringUtils.isEmpty(user.getUsername())){

			if(isValidPassword(user,password)){

				authenticationToken = manageAuthenticationTokens(user,"SESSION");

				user.setAuthenticationToken(authenticationToken);
				user.setLastSignedInDate(new Date());
				userDAO.update(user);
				userDTO = new UserDTO(user);
				status.setHasErrors(false);
				status.setInfo(LOGIN_SUCCESS);
			}
		}

		
		result.setResult(userDTO);
		result.setStatus(status);

		return result;
	}

	@Override
	public DisplaySingleResult deAuthenticateUser(String authenticationToken){
		
		DisplaySingleResult result = new DisplaySingleResult();
		Status status = new Status(true, LOGOUT_ERROR);
		UserToken userToken = findUserTokenFromAuthneticationToken(authenticationToken);
		Boolean success = userTokenDAO.delete(userToken);
		if(success){
			status.setHasErrors(false);
			status.setInfo(LOGOUT_SUCCESS);
			result.setStatus(status);
		}
		return result;
	}

	@Override
	public boolean authenticateToken(String emailAddress, String authenticationToken)
	{
		UserToken userToken = findUserTokenFromAuthneticationToken(authenticationToken);
		return !(userToken.isExpired()) && userToken.getUser().getEmailAddress().equals(emailAddress);
	}

	@Override
	public User findUserFromAuthenticationToken(String authenticationToken){

		return findUserTokenFromAuthneticationToken(authenticationToken).getUser();
	}

	@Override
	public UserToken findUserTokenFromAuthneticationToken(String authenticationToken){
		restrictions = restrictionService.resetSearchParameters(GenericQueryRestrictionService.AUTHENTICATION_TOKEN, authenticationToken, true);
		return (UserToken) userTokenDAO.findByCriteria(UserToken.class, restrictions, true);
	}

	private Boolean isValidPassword(User user, String password){
		return passwordEncoder.matches(password, user.getPassword());
	}

	
	public String manageAuthenticationTokens(User user, String tokenType){
		
		String authenticationToken = null;
		
		restrictions = restrictionService.resetSearchParameters(GenericQueryRestrictionService.USER, user, true);
		restrictions = restrictionService.updateSearchParameters(restrictions, GenericQueryRestrictionService.AUTHENTICATION_TOKEN_TYPE, tokenType, true);

		UserToken userToken = (UserToken) userTokenDAO.findByCriteria(UserToken.class, restrictions, true);

		if((userToken != null && userToken.isExpired()) || userToken == null ){
			if(userToken != null){
				userTokenDAO.delete(userToken);
			}
			
			authenticationToken = RandomStringGenerator.generateAuthenticationToken();
			userToken = new UserToken(authenticationToken, user, tokenType, ExpiryDateGenerator.setExpiryDate(Calendar.HOUR, 2));
			userTokenDAO.save(userToken);
		}
		else{

			authenticationToken = userToken.getAuthenticationToken();

		}
		
		return authenticationToken;
	}

	@Override
	public String manageAuthenticationTokens(String emailAddress,
			String tokenType) {
		restrictions = restrictionService.resetSearchParameters(GenericQueryRestrictionService.EMAIL_ADDRESS, emailAddress, true);

		User user = (User) userDAO.findByCriteria(User.class, restrictions, true);
				
		return manageAuthenticationTokens(user, tokenType);
	}
}
