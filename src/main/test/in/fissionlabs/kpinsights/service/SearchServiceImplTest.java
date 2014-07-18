package in.fissionlabs.kpinsights.service;

import static org.junit.Assert.*;
import in.fissionlabs.kpinsights.dao.UserDAO;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")
public class SearchServiceImplTest {

	@Autowired
	SearchServiceImpl searchServiceImpl;
	
	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Autowired
	UserDAO userDAO; 

	@Autowired
	GenericQueryRestrictionService restrictionService;

	private HashSet<SearchCriteria> restrictions;
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
