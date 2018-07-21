package com.s3a.poc.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import com.s3a.poc.web.controller.IndexController;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//GenericFilterBean
public class CustomFilter extends AbstractAuthenticationProcessingFilter {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private HttpServletRequest hrequest;
    private HttpServletResponse hresponse;
    

    private String servletPath;
    public CustomFilter(String servletPath,String failureUrl) {
        super(servletPath);
        this.servletPath=servletPath;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
		 logger.info("**redirect***");
 
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		 
		 this.hrequest = (HttpServletRequest) request;
		 this.hresponse =(HttpServletResponse) response;
		 String uri = hrequest.getRequestURI();
//		 logger.info("**URI***" + uri);
		if(uri.contains("course") || uri.contains("instructors")){
			if (SecurityContextHolder.getContext() != null) {
				logger.info("*****");
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null && auth.getPrincipal() != null) {
					UserDetails user =  (UserDetails) auth.getPrincipal() ;  
					logger.info("the name of the session : " + user.getUsername());

				}else{
					logger.info("######### Authentication is null");
					 logger.info("no auth");
					 hresponse.addHeader("error", "nologin");
		                unsuccessfulAuthentication(hrequest, hresponse, new InsufficientAuthenticationException("wrong"));
					 return;
				}
			} else {
				logger.info("######### SecurityContextHolder.getContext()  is null");
				 logger.info("no session");
				 hresponse.addHeader("error", "nologin");
	                unsuccessfulAuthentication(hrequest, hresponse, new InsufficientAuthenticationException("wrong"));
				 return;
			}
			
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
