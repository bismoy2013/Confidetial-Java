package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.Group;
import in.fissionlabs.kpinsights.model.Role;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;


/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Create role
 * <li> Display role
 * <li> Update role
 * <li> Delete role
 * <li> Display all roles
 * <li> Create group
 * <li> Display group
 * <li> Update group
 * <li> Delete group
 * <li> Display all groups
 * </ul>
 * @author bismoy
 *
 */
public interface AdminService 
{
	/**
	 * <h4>Create Role</h4>
	 * <p>Creates role by calling createRole method from roleService class</p>
	 * @param roleName name of a given role
	 * @returns status about the role creation
	 */
	
	Status createRole(String roleName);
	
	/**
	 * <h4>Display Role</h4>
	 * <p>Displays role by calling diplayRole method from roleService class</p>
	 * @param id user's id #
	 * @returns the displayed role
	 */
	
	Role displayRole(Long id);
	
	/**
	 * <h4>Update Role</h4>
	 * <p>Updates role by calling updateRole method from roleService class</p>
	 * @param id user's id #
	 * @param roleName name of the role assigned to a user
	 * @returns updated role of a user
	 */
	
	Status updateRole(Long id, String roleName);
	
	/**
	 * <h4>Delete Role</h4>
	 * <p>Deletes role by calling deleteRole method from roleService class</p>
	 * @param id user's id #
	 * @returns status of user deletion
	 */
	
	Status deleteRole(Long id);
	
	/**
	 * <h4>Display All Roles</h4>
	 * <p>Displays all roles by calling displayAllRoles method from roleService class</p>
	 * @returns status of all roles
	 */
	
	DisplayAllResults displayAllRoles();
	
	/**
	 * <h4>Create Group</h4>
	 * <p>Creates group by calling createGroup method from groupService class</p>
	 * @param groupName given name for a group
	 * @returns status of group creation
	 */
	
	Status createGroup(String groupName);
	
	/**
	 * <h4>Display Group</h4>
	 * <p>Displays group by calling displayGroup method from groupService class</p>
	 * @param id user's id #
	 * @returns status of group existing with relevant id #
	 */
	
	Group displayGroup(Long id);
	
	/**
	 * <h4>Update Group</h4>
	 * <p>Updates group by calling updateGroup method from groupService class</p>
	 * @param id user's id #
	 * @param groupName name of a group
	 * @returns status of group update
	 */
	
	Status updateGroup(Long id, String groupName);
	
	/**
	 * <h4>Delete Group</h4>
	 * <p>Deletes group by calling deleteGroup method from groupService class</p>
	 * @param id user's id #
	 * @returns status of group deletion
	 */
	
	Status deleteGroup(Long id);
	
	/**
	 * <h4>Display All Groups</h4>
	 * <p>Displays all groups </p>
	 * @returns all groups from the database
	 */
	
	DisplayAllResults displayAllGroups();
}
