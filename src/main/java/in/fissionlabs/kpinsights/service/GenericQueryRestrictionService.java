package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;

import java.util.HashSet;
/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Reset Search-parameters
 * <li> Update Search-parameters
 * <li> Reset Search-parameters
 * </ul>
 * @author bismoy
 *
 */
public interface GenericQueryRestrictionService {

	// Search Criteria identifiers
	static final String EMAIL_ADDRESS = "emailAddress";
	static final String AUTHENTICATION_TOKEN = "authenticationToken";
	static final String USER = "user";
	static final String AUTHENTICATION_TOKEN_TYPE = "type";

	
	/**
	 * <h4>Reset Search-parameters</h4>
	 * <p>Reset search parameters by calling resetSearchParameters method from GenericQueryRestrictionServiceImpl class</p>
	 * @param matchCategory string used while reseting the search parameters
	 * @param matchValue an object used as a parameter to reset search parameters
	 * @param matchExact a boolean value parameter used to reset search parameters
	 * @returns Search parameters reset value in the form of HashSet Data Structure
	 */
	public HashSet<SearchCriteria> resetSearchParameters(String matchCategory, Object matchValue, Boolean matchExact);

	/**
	 * <h4>Update Search-parameters</h4>
	 * <p>Updates search parameters by calling updateSearchParameters method from GenericQueryRestrictionServiceImpl class</p>
	 * @param restrictions search criteria stored as a HashSet
	 * @param matchCategory string used while reseting the search parameters
	 * @param matchValue an object used as a parameter to reset search parameters
	 * @param matchExact a boolean value parameter used to reset search parameters
	 * @returns status of search parameters update in the form of HashSet Data Structure 
	 */
	
	public HashSet<SearchCriteria> updateSearchParameters(HashSet<SearchCriteria> restrictions, String matchCategory, Object matchValue, Boolean matchExact);

	public void resetSearchParameters();

}
