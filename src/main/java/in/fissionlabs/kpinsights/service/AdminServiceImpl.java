package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.Group;
import in.fissionlabs.kpinsights.model.Role;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	RoleService roleService;
	
	@Autowired 
	GroupService groupService;

	@Override
	public Status createRole(String roleName) {
		
		return roleService.createRole(roleName);
	}

	@Override
	public Role displayRole(Long id) {
		return roleService.displayRole(id);
	}

	@Override
	public Status updateRole(Long id, String roleName) {
		return roleService.updateRole(id, roleName);
	}

	@Override
	public Status deleteRole(Long id) {
		return roleService.deleteRole(id);
	}

	@Override
	public DisplayAllResults displayAllRoles() 
	{
		return roleService.displayAllRoles();
	}

	@Override
	public Status createGroup(String groupName) {
		return groupService.createGroup(groupName);
	}

	@Override
	public Group displayGroup(Long id) {
		return groupService.displayGroup(id);
	}

	@Override
	public Status updateGroup(Long id, String groupName) {
		return groupService.updateGroup(id, groupName);
	}

	@Override
	public Status deleteGroup(Long id) {
		return groupService.deleteGroup(id);
	}

	@Override
	public DisplayAllResults displayAllGroups() {
		return groupService.displayAllGroups();
	}

}
