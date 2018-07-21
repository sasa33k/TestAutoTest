package com.s3a.poc.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class SiteErrorController implements ErrorController {
	 private static final String ERROR_PATH = "/error"; 
	@RequestMapping(value=ERROR_PATH)  
    public String handleError(){  
        return "redirect:/page_404.html";  
    }  
	@RequestMapping(value="/403")  
    public String handle403Error(){  
        return "redirect:/page_404.html";  
    }  
	@RequestMapping(value="/500")  
    public String handle500Error(){  
        return "redirect:/page_500.html";  
    }  
   
 @Override  
 public String getErrorPath() {  
  // TODO Auto-generated method stub  
  return ERROR_PATH;  
 }  
}
