package in.fissionlabs.kpinsights.controller;

import in.fissionlabs.kpinsights.model.Role;
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
 * <li> Create Role
 * <li> Display Role
 * <li> Update Role
 * <li> Delete Role
 * <li> Show Roles
 * </ul>
 * @author bismoy
 *
 */
public class RoleAdminController extends BaseController{
	
	private static Logger logger = Logger.getLogger(RoleAdminController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value=URIConstants.ROLE_CREATE, method=RequestMethod.POST)
	
	/**
	 * <h4>Create Role</h4>
	 * <p>Displays status about the role creation</p>
	 * @param roleName name of role assigned to a user
	 * @returns create role status
	 */
	public Status createRole(@RequestParam(required=true, value="roleName") String roleName){
	
		logger.debug("Inside createRole method with role name : "+roleName);
		Status status = new Status();
		status = adminService.createRole(roleName);
		
		return status;
	}
	
	@RequestMapping(value=URIConstants.ROLE_SHOW, method=RequestMethod.POST)
	/**
	 * <h4>Display Role</h4>
	 * <p>Displays role by calling displayRole method from adminService class</p>
	 * @param roleId role id #
	 * @returns role that matches given roleId parameter provided
	 */
	public Role displayRole(@RequestParam(required=true, value="id") Long roleId){
		logger.debug("Inside displayRole method with role Id : "+roleId);
	
		return adminService.displayRole(roleId);
	}
	
	@RequestMapping(value=URIConstants.ROLE_UPDATE, method=RequestMethod.POST)
	
	/**
	 * <h4>Update Role</h4>
	 * <p>Displays status about role update by calling updateRole method from adminService class</p>
	 * @param roleId role id #
	 * @param roleName role name
	 * @returns role update status
	 */
	public Status updateRole(@RequestParam(required=true, value="id") Long roleId, 
							 @RequestParam(required=true, value="roleName") String roleName)
	{
		logger.debug("Inside updateRole method with role id : "+roleId + "role Name : "+roleName);
		Status status = new Status();
		status = adminService.updateRole(roleId, roleName);
		
		return status;
	}
	
	@RequestMapping(value=URIConstants.ROLE_DELETE, method=RequestMethod.POST)
	
	/**
	 * <h4>Delete Role</h4>
	 * <p>Displays status about the role deletion by calling deleteRole method from adminService class</p>
	 * @param id user's id #
	 * @returns user deletion status
	 */
	
	public Status deleteRole(@RequestParam(required=true, value="id") Long id){
		
		logger.debug("Inside deleteRole method with id : "+id);
	
		Status status = new Status();
		status = adminService.deleteRole(id);
		
		return status;
	}
	
	@RequestMapping(value=URIConstants.ROLE_SHOW_ALL, method=RequestMethod.POST)
	
	/**
	 * <h4>Show Roles</h4>
	 * <p>Displays all the existing roles by calling displayAllRoles method from adminService class</p>
	 * @returns DisplayAllResults object which contains all existing roles
	 */
	
	public DisplayAllResults showRoles(){
	
		logger.debug("Inside showRoles method . ");
		return adminService.displayAllRoles();
	}

}
