package in.fissionlabs.kpinsights.security;


/**
 * Service used to match plaintext and encoded password
 * @author bismoy
 *
 */
public interface PasswordService {

	
	
/**
 * Converts plain text password into hash and then matches with stored hash from DB 
 * @param plainTextPassword password of user in plain text
 * @param storedHashedPassword Bcrypt encoded form of user's password
 * @returns true if passwords are matched otherwise false
 */
	public boolean match(String plainTextPassword, String storedHashedPassword);
	
	public String encodePassword(String plaintTextPassword);
	
}
