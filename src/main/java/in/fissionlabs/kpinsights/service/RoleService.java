package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.Role;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Create Role
 * <li> Update Role
 * <li> Delete Role
 * <li> Display Role
 * <li> Display all Roles
 * </ul>
 * @author bismoy
 *
 */
public interface RoleService {

	
	/**
	 * <h4>Create Role</h4>
	 * <p>Displays status of user creation by calling createRole method from RoleServiceImpl class</p>
	 * @param roleName name of the given role of a user
	 * @returns role creation status
	 */
	Status createRole(String roleName);
	
	/**
	 * <h4>Update Role</h4>
	 * <p>Displays role update status by calling updateRole method from RoleServiceImpl class</p>
	 * @param id user's id #
	 * @param roleName name of role assigned to a user
	 * @returns role update status
	 */
	
	Status updateRole(Long id, String roleName);
	
	
	/**
	 * <h4>Delete Role</h4>
	 * <p>Displays role deletion status by calling deleteRole method from RoleServiceImpl class</p>
	 * @param Id user's id #
	 * @returns role deletion status
	 */
	
	Status deleteRole(Long Id);
	
	/**
	 * <h4>Display Role</h4>
	 * <p>Displays role of a user by calling displayRole method from RoleServiceImpl class</p>
	 * @param id user's id #
	 * @returns role information of users if present
	 */
	
	Role displayRole(Long id);
	
	/**
	 * <h4>Display all Roles</h4>
	 * <p>Displays all existing roles by calling displayAllRoles method from RoleServiceImpl class</p>
	 * @return
	 */
	
	DisplayAllResults displayAllRoles();
	
}
