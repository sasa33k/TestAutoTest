package com.s3a.poc.test.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.s3a.poc.test.model.DbTestCase;
import com.s3a.poc.test.model.DbTestSteps;
import com.s3a.poc.test.repository.TestCaseRepository;
import com.s3a.poc.test.repository.TestStepsRepository;
import com.s3a.poc.test.repository.SummaryDescRepository;
import com.s3a.poc.test.util.ImportTestCase;
import com.s3a.poc.test.util.ImportTestData;
import com.s3a.poc.test.util.ParseTestSteps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping("/admin")
@Api(description = "admin functions on H2DB")
public class TestDataController {
	    public static final String EXCEL_FILE_PATH = "src/test/data_sample.xlsx";
	    public static final Integer SUMMARY_PREFIX = 0;
	    public static final Integer TC_HEADER = 10; // Header row for the summary page
	    public static final Integer TS_HEADER = 1;

		    @Autowired
		    SummaryDescRepository descRep;
		    
		    @Autowired
		    TestCaseRepository tcRep;
		    
		    @Autowired
		    TestStepsRepository tsRep;
		    
		    
	//
//		    @Autowired
//		    SummaryDescRepository repo;
		    @ApiOperation(value = "check Test Data from excel", notes = "Save Test Case from excel")
		    @RequestMapping(value = "/dataCheck", method =  RequestMethod.GET, produces = "application/json")               /*Issue with first double Entry, Checking when previous saving in progress*/
		    public String insertRecord (){
				Date CreateDateTime = new Date();
		    	
		 
		    	try { //, descRep, tcRep, tsRep
					ImportTestData.TestDataParser(EXCEL_FILE_PATH, TC_HEADER, CreateDateTime, descRep.getMaxId()+1);
			        return "Done";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			        return "FAIL";
				}
		    	
		 }
		    
		    

	    @ApiOperation(value = "Test Parse Test List", notes = "Just for testing")
	    @RequestMapping(value = "/findTestList", method =  RequestMethod.POST)
	    @ApiImplicitParam(name = "testList", value = "File Path", required = true, paramType = "body", dataType = "String")
	    public String findTestList (@RequestBody String testList) {

	    	try {
//	    		List<String> testListData = new ArrayList<String>(Arrays.asList(testList.split(",")));
//	    		List<Integer> testListNoData = new ArrayList<Integer>();
//	    		for(String str:testListData) {testListNoData.add(Integer.valueOf(str));};
	    		
//	    		+ testListData

	    		return testList + "  ||  "   +  testList.indexOf("[");
	    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        return "FAIL";
			}
	    }
	    
	   
	       
	   
	}
