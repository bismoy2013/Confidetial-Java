package in.fissionlabs.kpinsights.security;


public interface PasswordService {

	
	
/**
 * Converts plain text password into hash and then matches with stored hash from DB 
 *   
 * @param plainTextPassword
 * @param storedHashedPassword
 * @return
 * <code> true </code>
 */
	public boolean match(String plainTextPassword, String storedHashedPassword);
	
	public String encodePassword(String plaintTextPassword);
	
}
