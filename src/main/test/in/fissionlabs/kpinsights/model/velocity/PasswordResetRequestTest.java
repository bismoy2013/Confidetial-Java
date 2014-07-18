package in.fissionlabs.kpinsights.model.velocity;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")

public class PasswordResetRequestTest 
{
	@Autowired
	PasswordResetRequest request = new PasswordResetRequest("bismoy.murasing@fissionlabs.in","http://www.ret.com");
	@Test
	public void test() {
		String getPasswordResetUrl=request.getPasswordResetUrl();
		System.out.println(getPasswordResetUrl);
	}

}
