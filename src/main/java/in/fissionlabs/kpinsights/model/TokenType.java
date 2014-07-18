package in.fissionlabs.kpinsights.model;

public enum TokenType {
	
	SESSION,PW_RESET;
	
	private String tokenType;

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	
}
