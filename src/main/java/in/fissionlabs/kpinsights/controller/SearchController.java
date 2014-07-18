package in.fissionlabs.kpinsights.controller;

import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
import in.fissionlabs.kpinsights.service.SearchService;

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
 * <li> Search Users
 * </ul>
 * @author bismoy
 *
 */
public class SearchController extends BaseController{
	
private static Logger logger = Logger.getLogger(SearchController.class);
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value=URIConstants.USER_SEARCH, method=RequestMethod.POST)
	
	
	/**
	 * <h4>Search Users</h4>
	 * <p>Displays search result of users by calling searchUsers method from searchService class</p>
	 * @param searchText text to be searched for
	 * @param searchCategory category assigned for searching purpose
	 * @returns users search results
	 */
	
	public DisplayAllResults searchUsers(@RequestParam(required=true, value="searchText") String searchText, 
						@RequestParam(required=true, value="searchCategory") String searchCategory)
	{	
		logger.debug("Inside searchUsers method with searchCategory : " + searchCategory + "and searchText : "+searchText);
		return searchService.searchUsers(searchCategory,searchText);
	}
	

}
