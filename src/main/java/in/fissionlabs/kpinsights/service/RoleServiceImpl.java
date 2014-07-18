package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.dao.RoleDAO;
import in.fissionlabs.kpinsights.model.Role;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDAO;

	@Override
	public Status createRole(String roleName) {
		boolean success = false;
		Status status = new Status();
		Role role = new Role();
		role.setName(roleName);

		success = roleDAO.create(role);

		if(success){
			status.setHasErrors(false);
			status.setInfo("Role created successfully.");
		}else{
			status.setHasErrors(true);
			status.setInfo("Error creating Role. Please try again.");
		}
		return status;
	}

	@Override
	public Status updateRole(Long id, String roleName) {

		boolean success = false;
		Status status = new Status();

		Role role = roleDAO.findById(Role.class, id);
		role.setName(roleName);

		success = roleDAO.update(role);

		if(success){
			status.setHasErrors(false);
			status.setInfo("Role updated successfully.");
		}else{
			status.setHasErrors(true);
			status.setInfo("Error updating Role. Please try again.");
		}

		return status;
	}

	@Override
	public Status deleteRole(Long id) {

		boolean success = false;
		Status status = new Status();

		Role role = roleDAO.findById(Role.class, id);

		success = roleDAO.delete(role);

		if(success){
			status.setHasErrors(false);
			status.setInfo("Role deleted successfully.");
		}else{
			status.setHasErrors(true);
			status.setInfo("Error deleting Role. Please try again.");
		}

		return status;
	}

	@Override
	public Role displayRole(Long id) {

		Status status = new Status();

		Role role = roleDAO.findById(Role.class, id);

		if(!StringUtils.isEmpty(role.getId())){
			status.setHasErrors(false);
			status.setInfo("Role found with ID : "+id);
		}else{
			status.setHasErrors(true);
			status.setInfo("No Role found with ID : "+id);
		}
		
		role.setStatus(status);
		return role;
	}

	@Override
	public DisplayAllResults displayAllRoles() {
		
		
		DisplayAllResults newDTO = new DisplayAllResults();
		
		Status status = new Status();
		
		List<Role> rolesList = (List<Role>) roleDAO.findAll(Role.class);
		
		if(!CollectionUtils.isEmpty(rolesList)){
			status.setHasErrors(false);
			status.setInfo("Total no. of roles found : "+rolesList.size());
		}else{
			status.setHasErrors(false);
			status.setInfo("No roles found : "+rolesList.size());
		}
		
		newDTO.setResults(rolesList);
		newDTO.setStatus(status);
		return newDTO;
	}

}
