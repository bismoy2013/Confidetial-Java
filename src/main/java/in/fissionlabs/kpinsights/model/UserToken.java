package in.fissionlabs.kpinsights.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name="USER_TOKENS")
public class UserToken extends AbstractModel{


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_TOKEN_ID", unique=true, nullable=false)
	private Long id;

	@Column(name = "AUTHENTICATION_TOKEN", unique = true, nullable = false)
	private String authenticationToken;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
	private User user;

	@Column(name="TOKEN_TYPE")
	private String type;

	@Column(name="EXPIRY_DATE")
	private Date expiryDate;
	
	public UserToken(){
		
	}
	
	public UserToken(String authenticationToken, User user, String type, Date expiryDate){
		this.authenticationToken = authenticationToken;
		this.user = user;
		this.type = type;
		this.expiryDate = expiryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean isEmpty() {
		if(StringUtils.isEmpty(this.getAuthenticationToken())){
			return true;
		}else{
			return false;
		}
	}

	public Boolean isExpired() {
		if(!this.isEmpty() && this.expiryDate.after(new Date())){
			return false;
		}
		return true;
	}


}
