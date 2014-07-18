package in.fissionlabs.kpinsights.mail;

import javax.mail.MessagingException;

import in.fissionlabs.kpinsights.model.velocity.PasswordResetRequest;
/**
 * <h4>Service class to perform below user services : </h4>
 * <ul>
 * <li> Send Password reset mail
 * <li> Send confirmation mail
 * </ul>
 * @author bismoy
 *
 */
public interface DefaultMailService {

	
	/**
	 * <h4>Send Password reset mail</h4>
	 * <p>Password Reset Mail being delivered !</p>
	 * @param toAddress address where the reset mail needs to be sent
	 * @param request request for password reset
	 * @returns true if password reset mail is delivered otherwise false
	 */
	Boolean sendPasswordResetMail(String toAddress, PasswordResetRequest request);
	
	
	/**
	 * 
	 * @param toAddress address where the reset mail needs to be sent
	 * @param body text body expected to be delivered along with the mail
	 * @returns true if mail gets confirmed otherwise false
	 */
	
	Boolean sendConfirmationMail(String toAddress, String body); 
	
}
