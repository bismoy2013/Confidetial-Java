package in.fissionlabs.kpinsights.model.dto;

import in.fissionlabs.kpinsights.model.AbstractModel;
import in.fissionlabs.kpinsights.model.Group;
import in.fissionlabs.kpinsights.model.Role;
import in.fissionlabs.kpinsights.model.User;

import java.util.Date;
import java.util.Set;
/**
 * User Data Object that contains user information
 * @author bismoy
 *
 */
public class UserDTO extends AbstractModel{

	private String emailAddress;
	
	private String firstName;
	
	private String lastName;
	
	private Date lastSignedInDate;
	
	private String authenticationToken;
	
	private Set<Group> groups;
	
	private Set<Role> roles;
	
	public UserDTO(){
		
	}
	
	public UserDTO(User user){
		this.emailAddress = user.getEmailAddress();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.lastSignedInDate = user.getLastSignedInDate();
		this.authenticationToken = user.getAuthenticationToken();
		this.groups = user.getGroups();
		this.roles =   user.getRoles();
	}

	/**
	 * 
	 * @returns email id of a user
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 
	 * @param emailAddress email id of a user
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @returns first name of a user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName first name of a user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @returns last name of a user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName last name of a user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @returns the last logged in date of a user
	 */
	public Date getLastSignedInDate() {
		return lastSignedInDate;
	}

	/**
	 * 
	 * @param lastSignedInDate last logged in date of a user
	 */
	public void setLastSignedInDate(Date lastSignedInDate) {
		this.lastSignedInDate = lastSignedInDate;
	}

	/**
	 * 
	 * @returns groups existing in the database
	 */
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * 
	 * @param groups groups set for users
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/**
	 * 
	 * @returns all existing roles of the users
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * 
	 * @param roles relevant existing role set for all users
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 
	 * @returns authentication token for a user
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * 
	 * @param authenticationToken token set for a user
	 */
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	
	
}
