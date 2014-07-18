package in.fissionlabs.kpinsights.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")

public class ExpiryDateGeneratorTest 
{
	ExpiryDateGenerator edg=null;
	Date date = null;
	@org.junit.Before
	public void beforeTest()
	{
		edg = new ExpiryDateGenerator();
	}
	@SuppressWarnings("static-access")
	@Test
	public void testSetexpiryDate() throws ParseException
	{
		date = edg.setExpiryDate(Calendar.DATE, 2);
		//assertTrue(date.after(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-13 18:20:14")));
		assertTrue(date.after(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-10 18:20:14")));	
	}
}
