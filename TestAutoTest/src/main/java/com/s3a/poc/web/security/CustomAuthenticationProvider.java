package com.s3a.poc.web.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final Log logger = LogFactory.getLog(CustomAuthenticationProvider.class);

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("start CustomAuthenticationProvider..........");
		UsernamePasswordAuthenticationToken signedToken =     
	  	      (UsernamePasswordAuthenticationToken) authentication;  
	return signedToken;
		
	}
	
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
