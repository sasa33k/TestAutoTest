package com.s3a.poc.web.controller;


import org.springframework.context.MessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.s3a.poc.web.controller.IndexController;
import com.s3a.poc.web.security.CustomUser;
import com.s3a.poc.web.util.RandomNumStrUtil;


//@RestController
@Controller  
@RequestMapping
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
//	 @Autowired  
//	 GoogleSettings googleSettings;  
    @Autowired
    private MessageSource messageSource;
	 
   @RequestMapping("/")
   public String index(HttpServletRequest request) {
   	String uri = "";
   	CustomUser user = new CustomUser();
   	String lang= request.getParameter("lang");
   	user.setLang(lang);
		String utmSource = request.getParameter("utm_source");
		user.setUtmSource(utmSource);
		String utmCampaign = request.getParameter("utm_campaign");
		user.setUtmCampaign(utmCampaign);
		String utmMedium =request.getParameter("utm_medium");
		user.setUtmMedium(utmMedium);
		authenticateUserAndSetSession(request,user);
		if(lang!= null){
			uri = uri+ "?lang="+lang;
		}
		
		if(utmSource!= null){
			uri = uri+ "&utm_source="+utmSource;
		}
		
		if(utmCampaign!= null){
			uri = uri+ "&utm_campaign="+utmCampaign;
		}
		
		if(utmMedium!= null){
			uri = uri+ "&utm_medium="+utmMedium;
		}
		//?lang=en&utm_campaign=C2M&utm_source=corpsite&utm_medium=contactus_cta
		
       return "redirect:/index.html" + uri;
   }
   @RequestMapping("/hello")
   public String hello(HttpServletRequest request) {
   	String uri = "";
		
       return "forward:/templates/home.html";
   }
   
   @RequestMapping(value = "/lang", method = RequestMethod.GET)
   public String getLocalePage() {
       return "lang";
   }
   
   @RequestMapping("/login")
   @ResponseBody
   public CustomUser login(HttpServletRequest request) {
   	CustomUser user = null;
   	if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null){
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		if(auth.getPrincipal() != null){
   			if(auth.getPrincipal() instanceof String){
   				user = new CustomUser();
   	    		authenticateUserAndSetSession(request,user);
   			}else{
   				user = (CustomUser) auth.getPrincipal() ;  
   			}
   		}
   		
   	}else{
   	 	user = new CustomUser();
   		authenticateUserAndSetSession(request,user);
   	}
  
       return user;
   }
   
   @RequestMapping("/sessionId")
   @ResponseBody
   public CustomUser  getSessionId() {
   	CustomUser user = null;
   	try {
   		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null){
       		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       		user = (CustomUser) auth.getPrincipal() ;  
       	}
		} catch (Exception e) {
			
		}
   
       return user;
   }
   
	private void authenticateUserAndSetSession(HttpServletRequest request,CustomUser user) {
		logger.info("************* authenticateUserAndSetSession ");
//		System.out.println("************* authenticateUserAndSetSession 2");
		int length = RandomNumStrUtil.createRandomNum(10, 16);
		String signature = RandomNumStrUtil.createRandomAndStr(length);
		user.setUsername(signature);
		user.setPassword("password");
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		logger.info("**************************** ");
		// get the session
		SecurityContext securityContext = (SecurityContext) SecurityContextHolder.getContext();
		logger.info("the name of the session " + securityContext.getAuthentication().getName());

	}
}