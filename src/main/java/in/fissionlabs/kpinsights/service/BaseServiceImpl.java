package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService{

	@Autowired
	GenericQueryRestrictionService restrictionService;
	
	@Autowired
	UserDAO userDAO;
	
	private HashSet<SearchCriteria> restrictions;
	
	public User getUniqueUser(String emailAddress){

		restrictions = restrictionService.resetSearchParameters(GenericQueryRestrictionService.EMAIL_ADDRESS, emailAddress, true);
		return (User) userDAO.findByCriteria(User.class,restrictions,true);
	}
}
