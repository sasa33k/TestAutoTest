package com.s3a.poc.test.model;

import com.s3a.poc.test.model.TcDoType;
import com.s3a.poc.test.model.TcGetType;
import com.s3a.poc.test.model.TcTestCase;
import com.s3a.poc.test.model.TcTestStep;

public class TcTestStep {
//implements Comparable<TcTestStep>{

	private Long stepId;
	private String stepName;
	private String getValue;
	private TcGetType getType;
	
	private TcDoType doType;	
	private String doValue;
	
	private boolean screenCap;
	private boolean resultRev;
	private boolean stepSkip;
	private boolean stepRemark;

	
	private TcTestCase testCase;


	public Long getStepId() {
		return stepId;
	}


	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}


	public String getStepName() {
		return stepName;
	}


	public void setStepName(String stepName) {
		this.stepName = stepName;
	}


	public String getGetValue() {
		return getValue;
	}


	public void setGetValue(String getValue) {
		this.getValue = getValue;
	}


	public TcGetType getGetType() {
		return getType;
	}


	public void setGetType(TcGetType getType) {
		this.getType = getType;
	}


	public TcDoType getDoType() {
		return doType;
	}


	public void setDoType(TcDoType doType) {
		this.doType = doType;
	}


	public String getDoValue() {
		return doValue;
	}


	public void setDoValue(String doValue) {
		this.doValue = doValue;
	}


	public boolean isScreenCap() {
		return screenCap;
	}


	public void setScreenCap(boolean screenCap) {
		this.screenCap = screenCap;
	}


	public boolean isResultRev() {
		return resultRev;
	}


	public void setResultRev(boolean resultRev) {
		this.resultRev = resultRev;
	}


	public boolean isStepSkip() {
		return stepSkip;
	}


	public void setStepSkip(boolean stepSkip) {
		this.stepSkip = stepSkip;
	}


	public boolean isStepRemark() {
		return stepRemark;
	}


	public void setStepRemark(boolean stepRemark) {
		this.stepRemark = stepRemark;
	}


	public TcTestCase getTestCase() {
		return testCase;
	}


	public void setTestCase(TcTestCase testCase) {
		this.testCase = testCase;
	}




//	@Override
//	public int compareTo(SeTestStep o) {
//		  if(this.sequence>o.sequence){
//			  return 1;
//		  }else{
//			  return -1;
//		  }
//	}

	


	
	
	
}
