package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.dao.GroupDAO;
import in.fissionlabs.kpinsights.model.Group;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDAO groupDAO;

	@Override
	public Status createGroup(String groupName) {
		boolean success = false;
		Status status = new Status();
		Group group = new Group();
		group.setName(groupName);

		success = groupDAO.create(group);

		if(success){
			status.setHasErrors(false);
			status.setInfo("Group created successfully.");
		}else{
			status.setHasErrors(true);
			status.setInfo("Error creating Group. Please try again.");
		}
		return status;
	}

	@Override
	public Status updateGroup(Long id, String groupName) {

		boolean success = false;
		Status status = new Status();

		Group group = groupDAO.findById(Group.class, id);
		group.setName(groupName);

		success = groupDAO.update(group);

		if(success){
			status.setHasErrors(false);
			status.setInfo("Group updated successfully.");
		}else{
			status.setHasErrors(true);
			status.setInfo("Error updating Group. Please try again.");
		}

		return status;
	}

	@Override
	public Status deleteGroup(Long id) {

		boolean success = false;
		Status status = new Status();

		Group group = groupDAO.findById(Group.class, id);

		success = groupDAO.delete(group);

		if(success){
			status.setHasErrors(false);
			status.setInfo("Group deleted successfully.");
		}else{
			status.setHasErrors(true);
			status.setInfo("Error deleting Group. Please try again.");
		}

		return status;
	}

	@Override
	public Group displayGroup(Long id) {

		Status status = new Status();

		Group group = groupDAO.findById(Group.class, id);

		if(!StringUtils.isEmpty(group.getId())){
			status.setHasErrors(false);
			status.setInfo("Group found with ID : "+id);
		}else{
			status.setHasErrors(true);
			status.setInfo("No Group found with ID : "+id);
		}
		
		group.setStatus(status);
		return group;
	}

	@Override
	public DisplayAllResults displayAllGroups() {
		
		
		DisplayAllResults newDTO = new DisplayAllResults();
		
		Status status = new Status();
		
		List<Group> groupsList = (List<Group>) groupDAO.findAll(Group.class);
		
		if(!CollectionUtils.isEmpty(groupsList)){
			status.setHasErrors(false);
			status.setInfo("Total no. of groups found : "+groupsList.size());
		}else{
			status.setHasErrors(false);
			status.setInfo("No groups found : "+groupsList.size());
		}
		
		newDTO.setResults(groupsList);
		newDTO.setStatus(status);
		return newDTO;
	}

}
