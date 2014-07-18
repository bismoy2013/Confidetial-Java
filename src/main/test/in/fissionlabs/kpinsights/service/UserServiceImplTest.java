package in.fissionlabs.kpinsights.service;

import static org.junit.Assert.*;

import java.util.Date;

import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;
import in.fissionlabs.kpinsights.model.dto.DisplaySingleResult;
import in.fissionlabs.kpinsights.model.dto.UserDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")

public class UserServiceImplTest 
{
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateUser() 
	{
		UserDTO userDTO=new UserDTO();
		userDTO.setEmailAddress("bismoy.murasing@fissionlabs.in");
		/*userDTO.setAuthenticationToken("2S8hY&Hb!h!Zea9VKYX0Zgiid_ElMLlN");
		userDTO.setCreateTimestamp(new Date("2014-07-16 11:45:15"));
		*/userDTO.setFirstName("BISMOY");
		userDTO.setLastName("MURASING");
		User result=userServiceImpl.createUser(userDTO);
		//System.out.println(result.getUsername());
		assertEquals("User created successfully",result.getEmailAddress(),"bismoy.murasing@fissionlabs.in");
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testShowUser() {
		UserDTO userDTO=new UserDTO();
		userDTO.setEmailAddress("bismoy.murasing@fissionlabs.in");
		userDTO.setAuthenticationToken("2S8hY&Hb!h!Zea9VKYX0Zgiid_ElMLlN");
		userDTO.setCreateTimestamp(new Date("2014-07-16 11:45:15"));
		userDTO.setFirstName("BISMOY");
		userDTO.setLastName("MURASING");
		DisplaySingleResult result=userServiceImpl.showUser(userDTO);
		System.out.println(result.getStatus().getInfo());
		//assertEquals(result.getStatus().getInfo(),"Found user for e-mail : bismoy.murasing@fissionlabs.in");
	}
	@Test
	public void testUpdateUser() {
		UserDTO userDTO=new UserDTO();
		userDTO.setEmailAddress("seshagiri@fissionlabs.in");
		userDTO.setAuthenticationToken("2S8hY&Hb!h!Zea9VKYX0Zgiid_ElMLlN");
		userDTO.setCreateTimestamp(null);
		userDTO.setFirstName("BISMOY");
		userDTO.setLastName("MURASING");
		userDTO.setUpdateTimestamp(null);
		userDTO.setRoles(null);
		userDTO.setLastSignedInDate(null);
		DisplaySingleResult result=userServiceImpl.updateUser(userDTO);
		System.out.println(result.getStatus().getInfo());
	}
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}
	@Test
	public void testShowAllUsers() {
		DisplayAllResults result=userServiceImpl.showAllUsers();
		assertEquals(result.getStatus().getInfo(),null);
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
