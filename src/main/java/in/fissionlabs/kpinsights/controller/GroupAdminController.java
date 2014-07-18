package in.fissionlabs.kpinsights.controller;

import in.fissionlabs.kpinsights.model.Group;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
import in.fissionlabs.kpinsights.service.AdminService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Create Group
 * <li> Display Group
 * <li> Update Group
 * <li> Delete Group
 * <li> Show Groups
 * </ul>
 * @author bismoy
 *
 */
public class GroupAdminController extends BaseController{

private static Logger logger = Logger.getLogger(GroupAdminController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value=URIConstants.GROUP_CREATE, method=RequestMethod.POST)
	
	/**
	 * <h4>Create Group</h4>
	 * <p>Displays status of group creation by calling createGroup method</p>
	 * @param groupName name of group
	 * @returns group creation status
	 */
	
	public Status createGroup(@RequestParam(required=true, value="groupName") String groupName){
	
		logger.debug("Inside createGroup method with given group name : " +groupName);
		Status status = new Status();
		status = adminService.createGroup(groupName);
		
		return status;
	}
	
	@RequestMapping(value=URIConstants.GROUP_SHOW, method=RequestMethod.POST)
	
	/**
	 * <h4>Display Group</h4>
	 * <p>Displays group by calling displayGroup method from AdminService class</p>
	 * @param groupId id # of a group
	 * @returns group using group id
	 */
	
	public Group displayGroup(@RequestParam(required=true, value="id") Long groupId)
	{
		logger.debug("Inside displayGroup method with given group id : " + groupId);
		return adminService.displayGroup(groupId);
	}
	
	@RequestMapping(value=URIConstants.GROUP_UPDATE, method=RequestMethod.POST)
	
	/**
	 * <h4>Update Group</h4>
	 * <p>Displays status of group update by calling updateGroup method</p>
	 * @param groupId id # of a group
	 * @param groupName name of a group
	 * @returns group update status
	 */
	
	public Status updateGroup(@RequestParam(required=true, value="id") Long groupId, 
							 @RequestParam(required=true, value="roleName") String groupName){
		
	
		logger.debug("Inside updateGroup method with given group name : " +groupName);
		Status status = new Status();
		status = adminService.updateGroup(groupId, groupName);
		
		return status;
	}
	
	@RequestMapping(value=URIConstants.GROUP_DELETE, method=RequestMethod.POST)
	
	/**
	 * <h4>Delete Group</h4>
	 * <p>Deletes group by calling deleteGroup method</p>
	 * @param id user's id #
	 * @returns group deletion status
	 */
	
	public Status deleteGroup(@RequestParam(required=true, value="id") Long id){
	
		logger.debug("Inside deleteGroup method with given id : " + id);
		Status status = new Status();
		status = adminService.deleteGroup(id);
		
		return status;
	}
	
	/**
	 * <h4>Show Groups</h4>
	 * <p>Displays all existing groups by calling showGroups method</p>
	 * @returns all existing groups
	 */
	
	
	@RequestMapping(value=URIConstants.GROUP_SHOW_ALL, method=RequestMethod.POST)
	public DisplayAllResults showGroups(){
	
		logger.debug("Inside showGroups method");
		return adminService.displayAllGroups();
	}
}
