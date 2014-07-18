package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.controller.AccessController;
import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.dao.UserTokenDAO;
import in.fissionlabs.kpinsights.mail.DefaultMailService;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.UserToken;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;
import in.fissionlabs.kpinsights.model.velocity.PasswordResetRequest;
import in.fissionlabs.kpinsights.security.RandomStringGenerator;

import java.util.Calendar;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("AccessService")
public class AccessServiceImpl implements AccessService 
{	
	@Autowired
	BaseService baseService;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserTokenDAO userTokenDAO;
	
	@Autowired
	DefaultMailService defaultMailService;
	
	@Autowired
	GenericQueryRestrictionService restrictionService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder; 
	
	private HashSet<SearchCriteria> restrictions;
	
	private static Logger logger = Logger.getLogger(AccessServiceImpl.class);
	
	
	
	@Override
	public Boolean verifyUser(String emailAddress){

		logger.debug("Into verifyUser with email: " + emailAddress);
		
		boolean userExists = false;
		User user = baseService.getUniqueUser(emailAddress);

		if(user!=null){
			userExists = true;
		}
		return userExists; 
	}
	
	
	@Override
	public Boolean sendPasswordResetMail(String emailAddress)
	{
		logger.debug("Into verifyUser with email: " + emailAddress);
		String passwordAuthenticationToken = RandomStringGenerator.generateAuthenticationToken();
		User user = baseService.getUniqueUser(emailAddress);

		PasswordResetRequest request = new PasswordResetRequest(user.getFirstName(),passwordAuthenticationToken);
		
		restrictions = restrictionService.resetSearchParameters(GenericQueryRestrictionService.USER, user, true);
		restrictions = restrictionService.updateSearchParameters(restrictions, GenericQueryRestrictionService.AUTHENTICATION_TOKEN_TYPE, "RESET_PW", true);
		UserToken userToken = new UserToken(passwordAuthenticationToken, user, "RESET_PW", ExpiryDateGenerator.setExpiryDate(Calendar.DATE, 2));
		userTokenDAO.save(userToken);

		return defaultMailService.sendPasswordResetMail(emailAddress, request);
	}


	

	@Override
	public Boolean verifyPasswordResetAuthenticationToken(String emailAddress, String passwordResetAuthenticationToken)
	{
		logger.debug("Into verifyUser with email: " + emailAddress + " and passwordResetAuthenticationToken : "+passwordResetAuthenticationToken);
		return authenticationService.authenticateToken(emailAddress, passwordResetAuthenticationToken);
	}


	@Override
	public Boolean updateUserPassword(String emailAddress, String newPassword) 
	{
		logger.debug("Into verifyUser with email: " + emailAddress + " and new password : "+newPassword);
		User user = baseService.getUniqueUser(emailAddress);
		user.setPassword(passwordEncoder.encode(newPassword));
		return userDAO.save(user);
	}
	
	
}
