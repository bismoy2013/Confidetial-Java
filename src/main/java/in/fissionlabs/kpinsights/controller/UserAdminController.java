package in.fissionlabs.kpinsights.controller;

import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;
import in.fissionlabs.kpinsights.model.dto.UserDTO;
import in.fissionlabs.kpinsights.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
/**
 *  <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Create User
 * <li> Update User
 * <li> Show User
 * <li> Delete User
 * <li> Show all Users
 * <li> add User group
 * <li> add User role
 * </ul>
 * @author bismoy
 *
 */

public class UserAdminController extends BaseController {

	private static final Logger logger = Logger.getLogger(UserAdminController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value=URIConstants.USER_CREATE,method = RequestMethod.POST)
	
	/**
	 * <h4>Create User</h4>
	 * <p>Display User Create Information by calling createUser method from userService class</p>
	 * @param userDTO an object containing all user information
	 * @returns user creation status
	 */
	public User createUser(@RequestBody UserDTO userDTO){
		
		logger.info("Inside createUser method.");
		return userService.createUser(userDTO);
		
	}
	
	@RequestMapping(value=URIConstants.USER_UPDATE,method = RequestMethod.POST)
	
	/**
	 * <h4>Update User</h4>
	 * <p>Displays user update status by calling updateUser method from userService class</p>
	 * @param userDTO an object containing all user information
	 * @returns user update status
	 */
	public DisplaySingleResult updateUser(@RequestBody UserDTO userDTO){
		
		logger.debug("Inside updateUser method .");
		return userService.updateUser(userDTO);
		
	}
	
	@RequestMapping(value=URIConstants.USER_SHOW,method = RequestMethod.POST)
	
	
	/**
	 * <h4>Show User</h4>
	 * <p>Displays user information by calling showUser method from userService class</p>
	 * @param userDTO an object containing all user information
	 * @returns user information
	 */
	public DisplaySingleResult showUser(@RequestBody UserDTO userDTO){
		logger.debug("Inside showUser method .");
		
		return userService.showUser(userDTO);
		
	}
	
	@RequestMapping(value=URIConstants.USER_DELETE,method = RequestMethod.POST)
	
	/**
	 * <h4>Delete user</h4>
	 * <p>Displays delete User status by calling deleteUser method from userService class</p>
	 * @param userDTO an object containing all user information
	 * @returns user delete status
	 */
	public Status deleteUser(@RequestBody UserDTO userDTO){
		
		logger.debug("Inside deleteUser method .");
		
		return userService.deleteUser(userDTO);
		
	}
	
	@RequestMapping(value=URIConstants.USER_SHOW_ALL,method = RequestMethod.POST)
	
	/**
	 * <h4>Show all Users</h4>
	 * <p>Shows all existing users by calling showAllUsers method from userService class</p>
	 * @returns all existing users
	 */
	public DisplayAllResults showAllUsers(){
		logger.debug("Inside showAllUsers method . ");
		
		return userService.showAllUsers();
		
	}
	
	@RequestMapping(value=URIConstants.USER_GROUP_UPDATE,method = RequestMethod.POST)
	/**
	 * <h4>Add User Group</h4>
	 * <p>Displays status after a new user group is added by calling updateUserGroups method from userService class</p>
	 * @param userDTO an object containing all user information
	 * @returns status after a new user group is added
	 */
	public DisplaySingleResult addUserGroup(@RequestBody UserDTO  userDTO){
		
		logger.debug("Inside addUserGroup method .");
		return userService.updateUserGroups(userDTO);
		
	}
	
	@RequestMapping(value=URIConstants.USER_ROLE_UPDATE,method = RequestMethod.POST)
	/**
	 * <h4>Add User Role</h4>
	 * <p>Displays status after user role is added by calling updateUserRoles method from userService class</p>
	 * @param userDTO an object containing all user information
	 * @returns status after a new user role is added
	 */
	public DisplaySingleResult adduserRole(@RequestBody UserDTO userDTO){
		logger.debug("Inside adduserRole method . ");
		
		return userService.updateUserRoles(userDTO);
		
	}
}
