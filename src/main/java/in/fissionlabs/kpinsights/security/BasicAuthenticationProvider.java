package in.fissionlabs.kpinsights.security;

import in.fissionlabs.kpinsights.dao.UserTokenDAO;
import in.fissionlabs.kpinsights.model.User;
import in.fissionlabs.kpinsights.model.UserToken;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;
import in.fissionlabs.kpinsights.service.GenericQueryRestrictionService;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BasicAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserTokenDAO userTokenDAO;
	
	@Autowired
	GenericQueryRestrictionService restrictionService;
	
	private HashSet<SearchCriteria> restrictions;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{

		String authenticationToken = ((User) authentication.getPrincipal()).getAuthenticationToken();

		restrictions = restrictionService.resetSearchParameters(GenericQueryRestrictionService.AUTHENTICATION_TOKEN, authenticationToken, true);
		UserToken verifiedUserToken = (UserToken) userTokenDAO.findByCriteria(UserToken.class, restrictions, true);
		
		if(verifiedUserToken==null){
			throw new PreAuthenticatedCredentialsNotFoundException("User not logged in.");
		}

		User user = verifiedUserToken.getUser();
		PreAuthenticatedAuthenticationToken authenticatedToken = new PreAuthenticatedAuthenticationToken(user,null,user.getAuthorities());

		return authenticatedToken;

	}

	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class
				.isAssignableFrom(authentication);
	}



}
