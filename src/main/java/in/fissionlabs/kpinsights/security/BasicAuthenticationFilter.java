package in.fissionlabs.kpinsights.security;

import in.fissionlabs.kpinsights.model.User;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

public class BasicAuthenticationFilter extends GenericFilterBean {

	private static Logger logger = Logger.getLogger(BasicAuthenticationFilter.class);

	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> ads = new WebAuthenticationDetailsSource();
	private AuthenticationManager authenticationManager;
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {


		logger.info("Inside Basic Authentication Filter : ");

		// Check for authentication token in the servlet request
		String authenticationToken = ((HttpServletRequest) request).getParameter("authenticationToken");

		try {
			if (authenticationToken == null) {

				// Request doesn't contain authentication token. User will be re-directed to login page with Authentication Filter in the catch block.

				throw new AuthenticationException("No AccessToken found.") {
					private static final long serialVersionUID = 1L;
				};
			}

			User user = new User();
			user.setAuthenticationToken(authenticationToken);
			
			logger.debug("Temporary User created with authentication token information from request. "+ user);

			// Checking if the existing authentication token is still valid :
			// Creating a pre-authenticated token object to populate the user details available from the request.

			PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(user, null);
			token.setDetails(ads.buildDetails((HttpServletRequest) request));

			// Checking with authentication manager to see if the current authentication is still valid :

			Authentication authentication = authenticationManager.authenticate(token);

			// Update the security context holder with authentication information for availability through the current thread.
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (AuthenticationException e) {
			// Authentication information was rejected by the authentication manager due to expired authentication token.
			logger.debug("got authentication exception", e);
			failureHandler.onAuthenticationFailure((HttpServletRequest) request, (HttpServletResponse) response, e);
			return;
		}


		chain.doFilter(request, response);

	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
		this.failureHandler = failureHandler;
	}

}
