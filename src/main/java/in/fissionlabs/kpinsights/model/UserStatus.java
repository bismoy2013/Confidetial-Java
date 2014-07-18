package in.fissionlabs.kpinsights.model;

import java.util.HashMap;
import java.util.Map;

public class UserStatus {

	private boolean isAuthenticated;

	private Map<String,String> authenticationErrors = new HashMap<String,String>();
	
	private boolean isAuthorized;
	
	private Map<String,String> authorizationErrors = new HashMap<String,String>();
	
	
	@Override
	public String toString(){
		
		return (" Is Authenticated : "+isAuthenticated 
				+ "\n Authentication Errors : " + authenticationErrors.toString()
				+ "\n Is Authorized : "+ isAuthorized
				+ "\n Authorization Errors : " + authenticationErrors.toString());
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public Map<String, String> getAuthenticationErrors() {
		return authenticationErrors;
	}

	public void setAuthenticationErrors(Map<String, String> authenticationErrors) {
		this.authenticationErrors = authenticationErrors;
	}

	public boolean isAuthorized() {
		return isAuthorized;
	}

	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public Map<String, String> getAuthorizationErrors() {
		return authorizationErrors;
	}

	public void setAuthorizationErrors(Map<String, String> authorizationErrors) {
		this.authorizationErrors = authorizationErrors;
	}
	
	
}
