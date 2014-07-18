package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.User;
/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Get Unique user
 * </ul>
 * @author bismoy
 *
 */
public interface BaseService {
	
	/**
	 * <h4>Get Unique User</h4>
	 * <p>Displays unique user using emaill address as a username by calling getUniqueUser method from BaseServiceImpl class</p>
	 * @param emailAddress username of a user
	 * @returns unique user
	 */

	User getUniqueUser(String emailAddress);
}
