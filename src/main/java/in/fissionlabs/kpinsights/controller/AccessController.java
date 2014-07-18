package in.fissionlabs.kpinsights.controller;

import in.fissionlabs.kpinsights.mail.DefaultMailService;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;
import in.fissionlabs.kpinsights.service.AccessService;
import in.fissionlabs.kpinsights.service.AuthenticationService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * AccessController has four endpoints. 
 * <ul>
 * <li> Login user
 * <li> Logout user
 * <li> Forgot password
 * <li> Reset password
 * </ul>
 * @author bismoy
 *
 */
@RestController
@RequestMapping("/")
public class AccessController extends BaseController {

	private static Logger logger = Logger.getLogger(AccessController.class);

	// Forgot Password status messages
	private static final String FORGOT_EMAIL_SUCCESS = "Password reset mail sent successfully to : ";
	private static final String FORGOT_EMAIL_ERROR = "No user found for user name : ";
	private static final String FORGOT_EMAIL_FAILURE = "Error sending password reset email. Please try again.";
	
	// Reset Password status messages
	private static final String PASSWORD_RESET_EMAIL_SUCCESS = "Confirmation mail successfully sent to : ";
	private static final String PASSWORD_RESET_EMAIL_FAILURE = "However an error occured while sending confirmation mail. ";
	private static final String PASSWORD_RESET_SUCCESS = "Password updated successfully. ";
	private static final String PASSWORD_RESET_ERROR = "Invalid or Expired Password reset request. Please try again. ";
	private static final String PASSWORD_RESET_FAILURE = "Error updating password. Please try again. ";
	private static final String PASSWORD_LOGIN_AGAIN = "Please login again using your new credentials. ";
	private static final String PASSWORD_TRY_LOGIN_AGAIN = "Please login again using your new credentials to verify system integrity. Confirmation mail will be sent soon.";
	
	private static final String LINE_BREAK = "\n";

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	AccessService accessService;
	
	@Autowired
	DefaultMailService defaultMailService;

	/**
	 * <h4>End-point to perform login service.</h4>  
	 * <p>
	 * Takes Base64 encoded combination of username & password then decodes and splits the string 
	 * and provides them as parameters by calling authenticateUser method from AuthenticateService class
	 * </p>
	 * @param userCredentials Base64 encoded form of username and password separated by +
	 * @returns DisplaySingleResult object which gives user login status
	 */
	@RequestMapping(value=URIConstants.USER_LOGIN, method=RequestMethod.POST)
	public DisplaySingleResult doLogin(@RequestParam(required=true, value="userCredentials") String userCredentials) {

		logger.debug("Inside doLogin to perform accessCredentials validation : "+userCredentials);

		String decodedCredentials = new String(Base64.decode(userCredentials.getBytes()));
		String[] credentials = decodedCredentials.split("[\\+:]");
		return authenticationService.authenticateUser(credentials[0], credentials[1]);
	}

	/**
	 * <h4>End-point to perform logout service.</h4>
	 * <p>Takes authentication token and provide it to deAuthenticateUser method from authenticationService which logs out the user</p>
	 * @param autheticationToken Generated token as user logs in
	 * @returns DisplaySingleResult which gives user logout status
	 */
	@RequestMapping(value=URIConstants.USER_LOGOUT,method=RequestMethod.POST)
	public DisplaySingleResult doLogout(@RequestParam(required=true, value="authenticationToken") String authenticationToken){
		logger.debug("Inside doLogout to perform authentication token validation : "+authenticationToken);
		return authenticationService.deAuthenticateUser(authenticationToken);
	}

	/**
	 * <h4>End-point to perform forgot password service.</h4>
	 * <p>An email containing authenticationToken is sent to the relevant user by calling forgotPassword method</p>
	 * @param emailAddress user's email id
	 * @returns DisplaySingleResult object which gives forgot password status
	 */
	@RequestMapping(value=URIConstants.USER_FORGOT_PASSWORD, method=RequestMethod.POST)
	public DisplaySingleResult forgotPassword(@RequestParam(required=true, value="emailAddress") String emailAddress){
		logger.debug("Into forgotPassword method to perform email validation : "+emailAddress);
		DisplaySingleResult result = new DisplaySingleResult();
		Status status = new Status(true, FORGOT_EMAIL_FAILURE);
		Boolean messageSent = false;
		boolean userExists = accessService.verifyUser(emailAddress);

		if(userExists){
			messageSent = accessService.sendPasswordResetMail(emailAddress);
		} else{
			status.setInfo(FORGOT_EMAIL_ERROR+emailAddress); 
		}
		if(messageSent){
			status.setHasErrors(false);
			status.setInfo(FORGOT_EMAIL_SUCCESS + emailAddress);
		}
		result.setStatus(status);
		return result;
	}
	

	/**
	 * <h4>End-point to perform password reset service. </h4>
	 * <p>Displays the status after the password reset is done successfully by calling resetPassword method</p>
	 * @param emailAddress emailAddress of the user
	 * @param passwordResetAuthenticationToken Token as received by user in email
	 * @param newPassword  Base64 encoded newPassword 
	 * @returns DisplaySingleResult object which gives reset password status
	 */
	@RequestMapping(value=URIConstants.USER_RESET_PASSWORD, method=RequestMethod.POST)
	public DisplaySingleResult resetPassword(@RequestParam(required=true, value="emailAddress") String emailAddress,
			@RequestParam(required=true, value="passwordResetAuthenticationToken") String passwordResetAuthenticationToken, 
			@RequestParam(required=true, value="newPassword") String newPassword){
        logger.debug("Inside reset password to reset the user password : "+ emailAddress + " Password authentication token :"+ passwordResetAuthenticationToken);
		DisplaySingleResult result = new DisplaySingleResult();
		Status status = new Status(true, PASSWORD_RESET_FAILURE);
		Boolean passwordUpdated = false;
		Boolean messageSent = false;

		boolean validPasswordResetAuthenticationToken = accessService.verifyPasswordResetAuthenticationToken(emailAddress, passwordResetAuthenticationToken);

		if(validPasswordResetAuthenticationToken){
			passwordUpdated = accessService.updateUserPassword(emailAddress, new String(Base64.decode(newPassword.getBytes())));
		} else{
			status.setInfo(PASSWORD_RESET_ERROR);
		}
		
		if(passwordUpdated){
			status.setInfo(PASSWORD_RESET_SUCCESS);
			messageSent = defaultMailService.sendConfirmationMail(emailAddress, PASSWORD_RESET_SUCCESS);
		}
		

		if(messageSent){
			status.setHasErrors(false);
			status.setInfo(PASSWORD_RESET_SUCCESS + emailAddress+ LINE_BREAK + PASSWORD_RESET_EMAIL_SUCCESS + LINE_BREAK + PASSWORD_LOGIN_AGAIN);
		}else{
			status.setInfo(PASSWORD_RESET_SUCCESS + LINE_BREAK + PASSWORD_RESET_EMAIL_FAILURE + LINE_BREAK + PASSWORD_TRY_LOGIN_AGAIN);
		}
		result.setStatus(status);

		return result;
	}
}