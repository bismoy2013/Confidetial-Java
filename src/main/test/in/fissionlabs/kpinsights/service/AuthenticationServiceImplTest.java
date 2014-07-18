package in.fissionlabs.kpinsights.service;

import static org.junit.Assert.*;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.UserToken;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")
public class AuthenticationServiceImplTest 

{
	@Autowired
	AuthenticationServiceImpl authenticationServiceImpl;
	@Test
	public void testAuthenticateUser()
	{
		String emailAddress="bismoy.murasing@fissionlabs.in";
		String password="123456";
		DisplaySingleResult result=authenticationServiceImpl.authenticateUser(emailAddress, password);
		//System.out.println(result);
		assertNotNull(result);
	}
	
	@Test
	public void testDeauthenticateUser()
	{
		String authenticationToken = "CkDRHJahfh&%)M9*l_sd!Bw(9lIMIrUC";
		DisplaySingleResult result=authenticationServiceImpl.deAuthenticateUser(authenticationToken);
		assertNotNull(result);
	}
	
	@Test
	public void testAuthenticateToken() 
	{
		String emailAddress="bismoy.murasing@fissionlabs.in";
		String authenticationToken = "tn&o+!LU7MG=30=56OZpJO-Z5!JWB(dL";
		boolean result = authenticationServiceImpl.authenticateToken(emailAddress,authenticationToken);
		System.out.println(result);
		//assertEquals(false, result);
	}
	
	//Do I need to initialize object ?
	@Test
	public void testFindUserFromAuthenticationToken()
	{ 
		String authenticationToken = "vz=Rggw(Ut5Vbes7Vpyr%-dS(5^X&eEG";
		
		User result=authenticationServiceImpl.findUserTokenFromAuthneticationToken(authenticationToken).getUser();

		System.out.println(result.getUserStatus());
		
		User user = new User();
		user.setId(4L);
		user.setEmailAddress("bismoy.murasing@fissionlabs.in");
		user.setFirstName("BISMOY");
		user.setLastName("MURASING");
		
		assertEquals(user, result);
		
	}
	/*@Test
	public void testFindUserTokenFromAuthneticationToken()
	{
		String authenticationToken = "B!v+%7e!cRQzWN%uOS$yu6B=rQ!ynv@@";
		UserToken result=authenticationServiceImpl.findUserTokenFromAuthneticationToken(authenticationToken).
		System.out.println(result);
		assertNotNull(result);
		assertEquals(null, result);
	}
	
*/	
	/*@Test
	public void testIsValidPassword()
	{
		String authenticationToken = "y88@suGzBiE-+XPtI52Ndk+Jl@tjzt5w";
		User result=authenticationServiceImpl.findUserTokenFromAuthneticationToken(authenticationToken).getUser();
		String password="123456";
//		boolean result = authenticationServiceImpl.
	}
	*/
	@Test
	public void testmanageAuthenticationTokens()
	{
		String emailAddress="bismoy.murasing@fissionlabs.in";
		String tokenType="SESSION";
		String result=authenticationServiceImpl.manageAuthenticationTokens(emailAddress, tokenType);
		assertEquals("H3^ObZQrDeG+LP#b+#JDv5yad6vajea", result);
		//System.out.println(result);
	}

}
