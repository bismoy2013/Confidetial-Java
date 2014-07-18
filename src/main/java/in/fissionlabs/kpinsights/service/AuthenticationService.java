package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.UserToken;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;

/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Authenticate User
 * <li> De-authenticate User
 * <li> Authenticate Token
 * <li> Find User from authentication-token
 * <li> Find User-token from authentication-token
 * <li> Manage Authentication-tokens
 * </ul>
 * @author bismoy
 *
 */

public interface AuthenticationService 

{
	
	/**
	 * <h4>Authenticate User</h4>
	 * <p>Displays status of user authentication by calling authenticateUser method from AuthenticationServiceImpl class</p>
	 * @param username user's email address 
	 * @param password user's given password
	 * @returns status of user authentication
	 */
	
	DisplaySingleResult authenticateUser(String username, String password);
	
	/**
	 * <h4>De-authenticate User</h4>
	 * <p>Displays status of user-deauthentication by calling deAuthenticateUser method from AuthenticationServiceImpl class</p>
	 * @param authenticationToken token generated as user logs in
	 * @returns status of user's de-authentication
	 */
	
	
	DisplaySingleResult deAuthenticateUser(String authenticationToken);
	
	/**
	 * <h4>Authenticate Token</h4>
	 * <p>Displays status of authentication token by calling authenticateToken method from AuthenticationServiceImpl class</p>
	 * @param emailAddress user's given email id
	 * @param authenticationToken user's token for authentication purpose
	 * @returns status of user's authentication token
	 */
	
	boolean authenticateToken(String emailAddress, String authenticationToken);
	
	/**
	 * <h4>Find User from authentication-token</h4>
	 * <p>Displays status of user after being searched by it's authentication token</p>
	 * @param authenticationToken user's token for authentication purpose
	 * @returns status of user found by the given authentication token
	 */
	
	User findUserFromAuthenticationToken(String authenticationToken);
	
	/**
	 * <h4>Find UserToken From AuthneticationToken</h4>
	 * <p>Displays status of user token by calling findUserTokenFromAuthneticationToken method from AuthenticationServiceImpl class</p>
	 * @param authenticationToken user's authentication token
	 * @returns status of user token after being searched by authentication token
	 */
	
	UserToken findUserTokenFromAuthneticationToken(String authenticationToken);
	
	/**
	 * <h4>Manage Authentication Tokens</h4>
	 * <p>Displays status of user's authentication token management by calling manageAuthenticationTokens from AuthenticationServiceImpl class</p>
	 * @param emailAddress user's email id
	 * @param tokenType type of user's token
	 * @returns status of user's authentication token management
	 */
	
	String manageAuthenticationTokens(String emailAddress, String tokenType);
}
