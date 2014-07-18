package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Search Users
 * </ul>
 * @author bismoy
 *
 */
public interface SearchService 

{
	/**
	 * <h4>Search Users</h4>
	 * <p>Displays search results by calling searchUsers method from SearchServiceImpl class</p>
	 * @param searchCategory a filter that we need to maintain while doing search
	 * @param SearchText text to be searched for
	 * @returns users' search status
	 */
	DisplayAllResults searchUsers(String searchCategory, String SearchText);
	
}
