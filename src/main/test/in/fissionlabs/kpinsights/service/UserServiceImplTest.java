package in.fissionlabs.kpinsights.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.dto.UserDTO;
import in.fissionlabs.kpinsights.security.PasswordService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")

public class UserServiceImplTest 

{
	
	@InjectMocks 
	UserServiceImpl userServiceImpl;
	
	@Mock
	AuthenticationService authenticationService;

	@Mock
	UserDAO userDAO;

	@Mock
	PasswordService passwordService;
	
	@Mock
	BaseService baseService;
	
	UserDTO userDTO;
	
	User user;

	
	@Before
    public void init() {
		
		userDTO = new UserDTO();
		userDTO.setEmailAddress("bismoy.murasing@fissionlabs.in");
		userDTO.setFirstName("BISMOY");
		userDTO.setLastName("MURASING");
		
		user=new User();
		user.setId(2L);
		user.setEmailAddress("bismoy.murasing@fissionlabs.in");
		user.setFirstName("BISMOY");
		user.setLastName("MURASING");
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateUserSuccess() 
	{
		when(userDAO.create(any(User.class))).thenReturn(true);
		assertEquals("BISMOY", userServiceImpl.createUser(userDTO).getFirstName());
	}


	@Test
	public void testCreateUserFailure() 
	{
		when(userDAO.create(any(User.class))).thenReturn(false);
		assertNull(userServiceImpl.createUser(userDTO));	
	}

	@Test
	public void testShowUser() 
	{
		when(baseService.getUniqueUser("bismoy.murasing@fissionlabs.in")).thenReturn(user);
		assertEquals("Found user for e-mail : "+"bismoy.murasing@fissionlabs.in", userServiceImpl.showUser(userDTO).getStatus().getInfo());
	}
	@Test
	public void testShowUserFailure() 
	{
		user.setId(0L);
		when(baseService.getUniqueUser("mkfsdm")).thenReturn(user);
		assertEquals("No user found for id : mkfsdm",userServiceImpl.showUser(userDTO).getStatus().getInfo());
	}

	@Test
	public void testUpdateUser() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserGroups() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserRoles() {
		fail("Not yet implemented");
	}

}
