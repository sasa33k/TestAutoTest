package com.s3a.poc.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s3a.poc.test.model.DbTestSteps;
import com.s3a.poc.test.repository.TestStepsRepository;

public class ParseTestSteps {

    public static Integer sheetTtl=0;
    
	private static final Logger logger = LoggerFactory.getLogger(ImportTestCase.class);

	public static List getTestStepsDetail(Integer version, Integer modId, String stepsNo, TestStepsRepository tsRep) {
		List<String> stepsList = new ArrayList<String>(Arrays.asList(stepsNo.split(",")));
		List<Integer> stepsNoList = new ArrayList<Integer>();
		for(String str:stepsList) {stepsNoList.add(Integer.valueOf(str));};
		Iterable<DbTestSteps> testSteps = tsRep.findByVersionAndModIdAndStepNoIn(version,modId, stepsNoList);
		return (List) testSteps;
		
	}

}
