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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s3a.poc.test.model.DbTestCase;
import com.s3a.poc.test.model.DbTestSteps;
import com.s3a.poc.test.repository.TestCaseRepository;
import com.s3a.poc.test.repository.TestStepsRepository;
import com.s3a.poc.test.repository.SummaryDescRepository;
import com.s3a.poc.test.util.ImportTestCase;
import com.s3a.poc.test.util.ParseTestSteps;



@RestController
@RequestMapping("/admin")
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
	    
	    @RequestMapping("/save")               /*Issue with first double Entry, Checking when previous saving in progress*/
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
	    	
	    	
	    	
	    	
//	        repository.save(new DbTestCase("TSID", "TSDesc",
//	    			"MID","Module", "Function","TCID", "SubID", 
//	    			"TcType","TcDesc", "TcResultDesc",true, 1, 
//	    			"PreReq","TSteps", "TBeforeRun","TAfterRun", "TSkip") );
//	        repository.save(new DbTestCase("TSID2", "TSDesc2",
//	    			"MID2","Module2", "Function2","TCID2", "SubID2", 
//	    			"TcType2","TcDesc2", "TcResultDesc2",true, 2, 
//	    			"PreReq2","TSteps2", "TBeforeRun2","TAfterRun2", "TSkip2") );
	    }
	    


	    
	    @RequestMapping("/max")
	    public String getMax(){

		        return String.valueOf(descRep.getMaxId());
	    }
	    
	    
	    @RequestMapping("/deleteAll")
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
	    
	    

	    @RequestMapping("/findBySubId/{ver}/{subId}")
	    public String findBySubId(@PathVariable int ver, @PathVariable String subId){

	    	try {

	    		return String.valueOf(tcRep.findByVersionAndSubId(ver, subId));
	    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        return "FAIL";
			}
	    }


	    @RequestMapping("/findStepsBySubId/{ver}/{subId}")
	    public String findStepsBySubId (@PathVariable int ver, @PathVariable String subId) {

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
	    
	    
	    @RequestMapping("/findTestList/{testList}")
	    public String findTestList (@PathVariable String testList) {

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
	       
	       
	    @RequestMapping("/findall")
	    public String findAll(){
	        String result = "";
	           
	        for(DbTestCase cust : tcRep.findAll()){
	            result += cust.toString() + "</br>";
	        }
	           
	        return result;
	    }
	       
	   
	}
