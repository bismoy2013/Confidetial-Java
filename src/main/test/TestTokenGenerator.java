/*package in.fissionlabs.kpinsights.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestTokenGenerator {

	private String randomToken;
	
	@Before
	public void setup(){
		randomToken = TokenGenerator.getNewToken();
	}
	
	@Test
	public void testGetNewToken() {
		assertFalse(randomToken.equals(TokenGenerator.getNewToken()));
	}
	
	@Test 
	public void testFormatToken() {
		System.out.println(randomToken.indexOf("-"));
		assertTrue(randomToken.indexOf("-")==-1);
	}

}
*/