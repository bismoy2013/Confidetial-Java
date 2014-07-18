package in.fissionlabs.kpinsights.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class BasicAuthenticationEntryPoint implements AuthenticationEntryPoint{

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		System.out.println("Inside Basic Authentication Entry Point.");
		
		// response.sendRedirect("/kpinsights/user/login/");
	}

}
