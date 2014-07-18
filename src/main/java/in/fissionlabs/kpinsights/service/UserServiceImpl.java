package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;
import in.fissionlabs.kpinsights.model.dto.UserDTO;
import in.fissionlabs.kpinsights.security.PasswordService;
import in.fissionlabs.kpinsights.security.RandomStringGenerator;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UserDAO userDAO;

	@Autowired
	PasswordService passwordService;
	
	@Autowired
	BaseService baseService;

	@Override
	public User createUser(UserDTO userDTO){

		String tempPassword = passwordService.encodePassword(RandomStringGenerator.generateTemporaryPassword());
		User user = new User();

		user.setEmailAddress(userDTO.getEmailAddress());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(tempPassword);
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		user.setEnabled(true);
		user.setCredentialsExpired(false);
		user.setCreateTimestamp(new Date());

		userDAO.create(user);
		
		// TODO send confirmation email with user`name and password.
		
		return user;
	}

	@Override 
	public DisplaySingleResult showUser(UserDTO userDTO){

		DisplaySingleResult newDTO = new DisplaySingleResult();
		Status status = new Status();

		User user = baseService.getUniqueUser(userDTO.getEmailAddress());

		if(StringUtils.isNotEmpty(user.getId().toString())){
			status.setHasErrors(false);
			status.setInfo("Found user for e-mail : "+userDTO.getEmailAddress());
		}else{
			status.setHasErrors(true);
			status.setInfo("No user found for id : "+userDTO.getEmailAddress());
		}

		newDTO.setResult(user);
		newDTO.setStatus(status);

		return newDTO; 
	}

	@Override
	public DisplaySingleResult updateUser(UserDTO userDTO) {

		
		User user = baseService.getUniqueUser(userDTO.getEmailAddress());
		
		return updateUser(user,userDTO);
	}
	
	@Override
	public Status deleteUser(UserDTO userDTO){
		Status status = new Status();
		User user = baseService.getUniqueUser(userDTO.getEmailAddress());
		
		Boolean success = userDAO.delete(user);

		status.setHasErrors(success);
		if(success){
			status.setInfo("User deleted successfully.");
		}else{
			status.setInfo("Error deleting user. Please try again.");
		}

		return status;
	}


	@Override
	public DisplayAllResults showAllUsers(){

		DisplayAllResults newDTO = new DisplayAllResults();

		Status status = new Status();

		List<User> usersList = (List<User>) userDAO.findAll(User.class);

		if(!CollectionUtils.isEmpty(usersList)){
			status.setHasErrors(false);
			status.setInfo("Total no. of users found : "+usersList.size());
		}else{
			status.setHasErrors(true);
			status.setInfo("No users found : "+usersList.size());
		}

		newDTO.setResults(usersList);
		newDTO.setStatus(status);
		return newDTO;
	}

	@Override
	public DisplaySingleResult updateUserGroups(UserDTO userDTO) {

		User user = baseService.getUniqueUser(userDTO.getEmailAddress());
		user.setGroups(userDTO.getGroups());
		
		return updateUser(user);
	}

	@Override
	public DisplaySingleResult updateUserRoles(UserDTO userDTO) {
		User user = baseService.getUniqueUser(userDTO.getEmailAddress());
		user.setRoles(userDTO.getRoles());
		return updateUser(user);
	}
	
	private DisplaySingleResult updateUser(User user, UserDTO userDTO){
		
		user.setEmailAddress(userDTO.getEmailAddress());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setRoles(userDTO.getRoles());
		user.setGroups(userDTO.getGroups());
		
		return updateUser(user);
	}
	
	private DisplaySingleResult updateUser(User user) {

		DisplaySingleResult newDTO = new DisplaySingleResult();
		Boolean success = userDAO.save(user);
		Status status = new Status();
		status.setHasErrors(success);

		if(success){
			status.setInfo("User updated successfully.");
		}else{
			status.setInfo("Error updating User. Please try again.");
		}

		newDTO.setResult(user);
		newDTO.setStatus(status);

		return newDTO;
	}
}
