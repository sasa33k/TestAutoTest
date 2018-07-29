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
import com.s3a.poc.test.util.ParseTestSteps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping("/admin")
@Api(description = "admin functions on H2DB")
public class TestCaseController {
    public static final String EXCEL_FILE_PATH = "src/test/test_sample.xlsx";
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
//	    @Autowired
//	    SummaryDescRepository repo;
	    @ApiOperation(value = "Save Test Case from excel", notes = "Save Test Case from excel")
	    @RequestMapping(value = "/save", method =  RequestMethod.GET, produces = "application/json")               /*Issue with first double Entry, Checking when previous saving in progress*/
	    public String insertRecord (){
			Date CreateDateTime = new Date();
	    	
	 
	    	try {
				ImportTestCase.TestCaseParser(EXCEL_FILE_PATH, TC_HEADER, CreateDateTime, descRep.getMaxId()+1, descRep, tcRep, tsRep);
		        return "Done";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        return "FAIL";
			}
	    	
	 }
	    


	    @ApiOperation(value = "Save Test Case from excel file specified", notes = "Just input file path")
	    @RequestMapping(value = "/save2", method =  RequestMethod.POST)               /*Issue with first double Entry, Checking when previous saving in progress*/
	    @ApiImplicitParam(name = "path", value = "File Path", required = true, paramType = "body", dataType = "String")
	    public String insertRecord2 (@RequestBody String path){
			Date CreateDateTime = new Date();
	    	
	 
	    	try {
				ImportTestCase.TestCaseParser(path, TC_HEADER, CreateDateTime, descRep.getMaxId()+1, descRep, tcRep, tsRep);
		        return "Done";
		        
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        return "FAIL";
			}
	    }

	    @ApiOperation(value = "Show Maximum Version", notes = "Get Latest Version ID")
	    @RequestMapping(value = "/max", method =  RequestMethod.GET)
	    public String getMax(){

		        return String.valueOf(descRep.getMaxId());
	    }
	    
	    @ApiOperation(value = "Delete All Data", notes = "Delete All Data")
	    @RequestMapping(value = "/deleteAll", method =  RequestMethod.GET)
	    public String deleteAll(){

	    	try {

		        descRep.deleteAll();
		        tcRep.deleteAll();
		        tsRep.deleteAll();
		        
		        return "Done";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        return "FAIL";
			}
	    }
	    
	    
	    @ApiOperation(value = "List Step IDs", notes = "List all steps ID by Sub Case ID")
	    @RequestMapping(value = "/findBySubId/{ver}/{subId}", method =  RequestMethod.GET, produces = "application/json")
	    public String findBySubId(@ApiParam("Version") @PathVariable int ver, @ApiParam("Sub Case ID") @PathVariable String subId){

	    	try {

	    		return String.valueOf(tcRep.findByVersionAndSubId(ver, subId));
	    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        return "FAIL";
			}
	    }

	    @ApiOperation(value = "List all Steps Details", notes = "List all steps by Sub Case ID")
	    @RequestMapping(value = "/findStepsBySubId/{ver}/{subId}", method =  RequestMethod.GET, produces = "application/json")
	    public String findStepsBySubId (@ApiParam("Version") @PathVariable int ver, @ApiParam("Sub Case ID") @PathVariable String subId) {

	    	try {
	    		 List<DbTestCase> tc = tcRep.findByVersionAndSubId(ver, subId);
	    		
//	    		 return String.valueOf(tc);
	    		return tc.get(0)+ "  " + String.valueOf(ParseTestSteps.getTestStepsDetail(ver, tc.get(0).getmId(), tc.get(0).gettSteps(), tsRep));
	    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        return "FAIL";
			}
	    }
	    

	    @ApiOperation(value = "Test Parse Test List", notes = "Just for testing")
	    @ResponseBody
	    @RequestMapping(value = "/findTestList/{testList}", method =  RequestMethod.GET)
	    public String findTestList (@ApiParam("Test List") @PathVariable String testList) {

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
	    
	    
	    
//	    @RequestMapping(value = "/ex/foos/{fooid}/bar/{barid}", method = GET)
//	    @ResponseBody
//	    public String getFoosBySimplePathWithPathVariables
//	      (@PathVariable long fooid, @PathVariable long barid) {
//	        return "Get a specific Bar with id=" + barid + 
//	          " from a Foo with id=" + fooid;
//	    }
//	    
	    
	    
	    
	    
	    
//	    @RestController
//	    @RequestMapping("/admin/max")
//	    public class getMax {
//
//	      @Autowired
//	      private Long getMax;
//
//	        @RequestMapping(value = "/find", method =  RequestMethod.GET)
//	        public Long getMax(){
//	          Long max = SummaryDescRepository.getMaxId();;
//	        return max;
//	        }
//	    }
	       
	    @ApiOperation(value = "Find all Test Cases", notes = "find all")
	    @RequestMapping(value = "/findall", method =  RequestMethod.GET)
	    public String findAll(){
	        String result = "";
	           
	        for(DbTestCase cust : tcRep.findAll()){
	            result += cust.toString() + "</br>";
	        }
	           
	        return result;
	    }
	       
	   
	}
