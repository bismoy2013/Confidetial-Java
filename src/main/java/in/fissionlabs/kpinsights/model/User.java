package in.fissionlabs.kpinsights.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USERS")
//public class User extends AbstractModel implements UserDetails{
public class User extends AbstractModel implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -489220319237146327L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Long id;


	@Column(name = "EMAIL_ADDRESS", unique = true, nullable = false)
	private String emailAddress;

	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Transient
	private UserStatus userStatus;

	@Transient
	private String authenticationToken; 

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="LAST_SIGNED_IN_DATE")
	private Date lastSignedInDate;

	@Column(name="PROFILE_PIC_URL")
	private String profilePicUrl;

	@Column(name="enabled")
	protected Boolean enabled;

	@Column(name="ACCOUNT_EXPIRED")
	protected Boolean accountExpired;

	@Column(name="ACCOUNT_LOCKED")
	protected Boolean accountLocked;

	@Column(name="CREDENTIALS_EXPIRED")
	protected Boolean credentialsExpired;

	@Transient
	private Set<GrantedAuthority> authorities;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLES", joinColumns = { 
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", 
			nullable = false, updatable = false) })
	@Cascade(CascadeType.ALL)
	private Set<Role> roles;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_GROUPS", joinColumns = { 
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "GROUP_ID", 
			nullable = false, updatable = false) })
	@Cascade(CascadeType.ALL)
	private Set<Group> groups = new HashSet<Group>();

	public User(){

	}
	/*public User(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastSignedInDate() {
		return lastSignedInDate;
	}

	public void setLastSignedInDate(Date lastSignedInDate) {
		this.lastSignedInDate = lastSignedInDate;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(authorities == null){
			authorities = new HashSet<GrantedAuthority>();
		}
		for (Role role : roles) {

			authorities.add(new SimpleGrantedAuthority(role.getName()));

			// TODO Make necessary changes once the permission model is in place.
			// authorities.addAll(role.getPermissions());
		}
		return authorities;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities){
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public Boolean getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public Set<Role> getRoles() {
		if(roles == null){
			roles = new HashSet<Role>();
		}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Group> getGroups() {
		if(groups==null){
			groups = new HashSet<Group>();
		}
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String getUsername() {
		return this.emailAddress;
	}



}
