package in.fissionlabs.kpinsights.service;

import static org.junit.Assert.*;
import in.fissionlabs.kpinsights.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")

public class BaseServiceImplTest {
	
	@Autowired
	BaseServiceImpl baseServiceImpl;

	@Test
	public void testGetUniqueUser() {
		String emailAddress="bismoy.murasing@fissionlabs.in";
		User result=baseServiceImpl.getUniqueUser(emailAddress);
		assertEquals(result.getFirstName(),"BISMOY"); 
	}

}
