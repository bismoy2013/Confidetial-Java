package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.Group;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Create Group
 * <li> Update Group
 * <li> Delete Group
 * <li> Display Group
 * <li> Display all Groups
 * </ul>
 * @author bismoy
 *
 */
public interface GroupService 
{

	
	/**
	 * <h4>Create Group</h4>
	 * <p>Displays group creation status by calling createGroup method from GroupServiceImpl class</p>
	 * @param groupName name of group users belong to
	 * @returns group creation status
	 */
	Status createGroup(String groupName);
	
	/**
	 * <h4>Update Group</h4>
	 * <p>Updates group by calling updateGroup method from GroupServiceImpl class</p>
	 * @param id group id
	 * @param groupName name of group users belong to
	 * @returns status of group creation
	 */
	
	Status updateGroup(Long id, String groupName);
	
	/**
	 * <h4>Delete Group</h4>
	 * <p>Deletes group using it group id by calling updateGroup method from GroupServiceImpl class</p>
	 * @param Id group id #
	 * @returns group deletion status
	 */
	
	Status deleteGroup(Long Id);
	
	/**
	 * <h4>Display Group</h4>
	 * <p>Displays group name using group id by calling displayGroup method from GroupServiceImpl class</p>
	 * @param id group id #
	 * @returns existence status of a group after searching using group id
	 */
	
	Group displayGroup(Long id);
	
	
	/**
	 * <h4>Display all groups</h4>
	 * <p>Displays all existing groups from the database by calling displayAllGroups method from GroupServiceImpl class</p>
	 * @returns all existing groups
	 */
	
	DisplayAllResults displayAllGroups();
	
}
