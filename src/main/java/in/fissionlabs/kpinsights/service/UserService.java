package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;
import in.fissionlabs.kpinsights.model.dto.UserDTO;
/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Create User
 * <li> Show User
 * <li> Update User
 * <li> Delete User
 * <li> Show all Users
 * <li> Update User groups
 * <li> Update User roles
 * </ul>
 * @author bismoy
 *
 */
public interface UserService 
{
	
	/**
	 * <h4>Create User</h4>
	 * <p>Creates User by calling create method from userDAO class</p>
	 * @param userDTO an object that contains user's information
	 * @returns status about the user creation
	 */
	User createUser(UserDTO userDTO);
	
	/**
	 * <h4>Show User</h4>
	 * <p>Displays status of user by calling showUser method from UserServiceImpl class</p>
	 * @param userDTO an object that contains user's information
	 * @returns status about user using it's relevant id
	 */
	
	DisplaySingleResult showUser(UserDTO userDTO);
	
	/**
	 * <h4>Update User</h4>
	 * <p>Displays user update status by calling updateUser method from UserServiceImpl class</p>
	 * @param userDTO an object that contains user's information
	 * @returns user update status
	 */
	
	
	DisplaySingleResult updateUser(UserDTO userDTO);
	
	/**
	 * <h4>Delete User</h4>
	 * <p>Displays status of user deletion by calling deleteUser method from UserServiceImpl class</p>
	 * @param userDTO an object that contains user's information 
	 * @returns user deletion status
	 */
	
	Status deleteUser(UserDTO userDTO);
	
	/**
	 * <h4>Show all Users</h4>
	 * <p>Displays all users if exist in the database or not by calling showAllUsers method from UserServiceImpl class</p>
	 * @returns total number of users from the database
	 */
	
	DisplayAllResults showAllUsers();

	
	/**
	 * <h4>Update User groups</h4>
	 * <p>Displays status of user groups update by calling updateUserGroups method from UserServiceImpl class</p>
	 * @param userDTO an object that contains user's information
	 * @returns user groups' update status
	 */
	
	DisplaySingleResult updateUserGroups(UserDTO userDTO);

	
	/**
	 * <h4>Update User roles</h4>
	 * <p>Displays status of user roles update by calling updateUserRoles method from UserServiceImpl class</p>
	 * @param userDTO an object that contains user's information
	 * @returns user roles' update status
	 */
	
	DisplaySingleResult updateUserRoles(UserDTO userDTO);
	
}
