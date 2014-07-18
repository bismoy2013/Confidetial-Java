package in.fissionlabs.kpinsights.service;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.dao.UserTokenDAO;
import in.fissionlabs.kpinsights.mail.DefaultMailService;
import in.fissionlabs.kpinsights.model.Role;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.velocity.PasswordResetRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RunWith(MockitoJUnitRunner.class)  
public class AccessServiceImplTest {
	
	@InjectMocks
	AccessServiceImpl accessServiceImpl;
	
	@Mock
	BaseService baseService;
	
	@Mock
	UserDAO userDAO;
	
	@Mock
	UserTokenDAO userTokenDAO;
	
	@Mock
	DefaultMailService defaultMailService;
	
	@Mock
	GenericQueryRestrictionService restrictionService;
	
	@Mock
	AuthenticationService authenticationService;
	
	@Mock
	BCryptPasswordEncoder passwordEncoder; 
	
	User user;
	
	@Before
    public void init() {
		
		user = new User();
		user.setId(1L);
		user.setFirstName("BISMOY");
		user.setLastName("MURASING");
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testVerifyValidUser()
	{
		
		user.setEmailAddress("bismoy.murasing@fissionlabs.in");
		when(baseService.getUniqueUser("bismoy.murasing@fissionlabs.in")).thenReturn(user);
		
		String emailAddress="bismoy.murasing@fissionlabs.in";
		boolean result=accessServiceImpl.verifyUser(emailAddress);
		assertTrue(result);
	}

	@Test
	public void testVerifyInvalidUser() 
	{
		when(baseService.getUniqueUser("test@fissionlabs.in")).thenReturn(null);
	
		String emailAddress="test@fissionlabs.in";
		boolean result=accessServiceImpl.verifyUser(emailAddress);
		assertFalse(result);
	}

	
	@Test
	public void testSendPasswordResetMailSuccess() {
		
		String emailAddress="bismoy.murasing@fissionlabs.in"; 
    
		when(baseService.getUniqueUser(emailAddress)).thenReturn(user);	
		when(defaultMailService.sendPasswordResetMail(eq(emailAddress), any(PasswordResetRequest.class))).thenReturn(true);
		assertTrue(accessServiceImpl.sendPasswordResetMail(emailAddress));
	}
	
	@Test
	public void testSendPasswordResetMailFailure() {
		String emailAddress="test@fissionlabs.in";
		when(baseService.getUniqueUser(emailAddress)).thenReturn(user);	
		when(defaultMailService.sendPasswordResetMail(eq(emailAddress), any(PasswordResetRequest.class))).thenReturn(false);
		assertFalse(accessServiceImpl.sendPasswordResetMail(emailAddress));
	}

	@Test
	public void testVerifyPasswordResetAuthenticationToken() {
		String emailAddress="bismoy.murasing@fissionlabs.in";
		String passwordResetAuthenticationToken="ypN28ke6VYuB)88$2%Y1yfzWm14*8Bum";
		when(authenticationService.authenticateToken(emailAddress, passwordResetAuthenticationToken)).thenReturn(true);
		
		assertTrue(accessServiceImpl.verifyPasswordResetAuthenticationToken(emailAddress, passwordResetAuthenticationToken));
	}

	@Test
	public void testUpdateUserPassword() 
	{
		String emailAddress="bismoy.murasing@fissionlabs.in";
		String newPassword="hello123";
		when(baseService.getUniqueUser(emailAddress)).thenReturn(user);
		when(userDAO.save(user)).thenReturn(true);
		assertTrue(accessServiceImpl.updateUserPassword(emailAddress, newPassword));
	}

}
