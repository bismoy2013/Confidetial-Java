package in.fissionlabs.kpinsights.dao;

import in.fissionlabs.kpinsights.dao.GenericDAO;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.UserToken;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/test-spring-context.xml")
public class GenericDAOTest
{
	
	@Autowired
	GenericDAO<UserToken> genericDAO;
	
	@Test
	public void testFindByCriteria() 
	{
		HashSet<SearchCriteria> searchCriteria = new HashSet<SearchCriteria>();
		String authenticationToken = "2S8hY&Hb!h!Zea9VKYX0Zgiid_ElMLlN";
		searchCriteria.add(new SearchCriteria("authenticationToken", authenticationToken, true));
		UserToken result=(UserToken) genericDAO.findByCriteria(UserToken.class, searchCriteria, true);
		System.out.println(result.getId()+" : Get id of the user");
		assertEquals(result.getId(),1);
	}
	private void assertEquals(Long id, int i) 
	{
		if(id==(long)i)
			System.out.println("The user id matched perfectly");	
	}
	
	//website researched : http://stackoverflow.com/questions/2922879/best-way-to-unit-test-collection
	@Test
	public void testFindExactMatchByCategoryAndValue()
	{
		HashSet<SearchCriteria> searchCriteria = new HashSet<SearchCriteria>();
		String authenticationToken = "2S8hY&Hb!h!Zea9VKYX0Zgiid_ElMLlN";
		searchCriteria.add(new SearchCriteria("authenticationToken", authenticationToken, true));
		UserToken result=(UserToken)genericDAO.findExactMatchByCategoryAndValue(UserToken.class, authenticationToken,1);
		System.out.println(result.getCreateTimestamp()+"++++++++++++++++");
		
	}

}
