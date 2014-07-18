package in.fissionlabs.kpinsights.service;
import static org.junit.Assert.*;
import in.fissionlabs.kpinsights.model.Group;
import in.fissionlabs.kpinsights.model.Role;
import in.fissionlabs.kpinsights.model.Status;
import in.fissionlabs.kpinsights.model.dto.DisplayAllResults;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")
public class AdminServiceImplTest 
{
	@Autowired
	AdminServiceImpl adminServiceImpl;	
	@Autowired
	RoleService roleService;
	@Autowired 
	GroupService groupService;
	@Test
	public void testCreateRole() {
		String roleName1="ADMIN-1";
		Status result1=adminServiceImpl.createRole(roleName1);
		System.out.println("result1.getInfo : "+result1.getInfo());
//		String roleName2="ADMIN-2";
//		Status result2=adminServiceImpl.createRole(roleName2);
//		System.out.println("result2.getInfo : "+result2.getInfo());
//		
//		assertEquals("Error creating Role. Please try again.",result2.getHasErrors());
	}

	@Test
	public void testDisplayRole()
	{
		Long id=(long) 1;
		Role result=adminServiceImpl.displayRole(id);
		System.out.println(result.getName());
		assertEquals("ADMIN",result.getName());
	}
	
	
	@Test
	public void testUpdateRole() {
		Long id =(long)7;
		String roleName="ADMIN-3";
		Status result=adminServiceImpl.updateRole(id, roleName);
		assertEquals(result.getInfo(),"Role updated successfully.");
	}
	
	
	@Test
	public void testDeleteRole() 
	{
		Long id =(long)7;
		Status result=adminServiceImpl.deleteRole(id);
		assertEquals(result.getInfo(),"Role deleted successfully.");
	} 
	
	@Test
	public void testDisplayAllRoles() 
	{
		DisplayAllResults result=adminServiceImpl.displayAllRoles();
		assertEquals(result.getStatus().getInfo(),"Total no. of roles found : 3");
	}

	@Test
	public void testCreateGroup() 
	{
		String groupName="USER_GROUP_6";
		Status result=adminServiceImpl.createGroup(groupName);
		assertEquals("Group created successfully.",result.getInfo());
	}

	@Test
	public void testDisplayGroup() 
	{
		Long id=(long) 5;
		Group result=adminServiceImpl.displayGroup(id);
		assertEquals(result.getStatus().getInfo(),"Group found with ID : 5");
	}

	@Test
	public void testUpdateGroup() 
	{
		Long id = (long)7;
		String groupName = "USER_GROUP_66";
		Status result=adminServiceImpl.updateGroup(id, groupName);
		assertEquals ("Group updated successfully.",result.getInfo());
	}

	@Test
	public void testDeleteGroup() 
	{
		Long id=(long) 6;
		Status result=adminServiceImpl.deleteGroup(id);
		assertEquals(result.getInfo(),"Group deleted successfully.");
	}

	@Test
	public void testDisplayAllGroups() 
	{
		DisplayAllResults result=adminServiceImpl.displayAllGroups();
		assertEquals(result.getStatus().getInfo(),"Total no. of groups found : 5");
	}

}
