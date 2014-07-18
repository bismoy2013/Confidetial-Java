package in.fissionlabs.kpinsights.model.velocity;

public class PasswordResetRequest {

	
	private String userName;
	private String passwordResetUrl;
	
	public PasswordResetRequest(String userName, String passwordResetUrl){
		this.userName = userName;
		this.passwordResetUrl = passwordResetUrl;
	}
	
	
	public String getPasswordResetUrl() {
		return passwordResetUrl;
	}
	public void setPasswordResetUrl(String passwordResetUrl) {
		this.passwordResetUrl = passwordResetUrl;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
