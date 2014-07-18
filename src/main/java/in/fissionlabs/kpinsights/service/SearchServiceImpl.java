package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SearchService")
public class SearchServiceImpl implements SearchService{

	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	GenericQueryRestrictionService restrictionService;

	private HashSet<SearchCriteria> restrictions;

	@SuppressWarnings("unchecked")
	@Override
	public DisplayAllResults searchUsers(String searchCategory, String searchText){
		int searchResults = 0;
		Collection<User> users = null;

		String modifiedSearchText = "%".concat(searchText).concat("%");

		DisplayAllResults dto = new DisplayAllResults();
		Status status = new Status();

		restrictions = restrictionService.resetSearchParameters(searchCategory, modifiedSearchText, false);
		users = (Collection<User>) userDAO.findByCriteria(User.class, restrictions, false);
		
		if(users!=null){
			searchResults = users.size();
		}

		if(searchResults>0){
			status.setHasErrors(false);
			status.setInfo("Total search results found for specified criteria : "+searchResults);
		}else{
			status.setHasErrors(true);
			status.setInfo("No search results found for specified criteria : " + searchText + " for category : " + searchCategory);
		}

		dto.setResults(users);
		dto.setStatus(status);

		return dto;
	}
}
