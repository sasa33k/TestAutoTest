package com.s3a.poc.test.model;


import java.util.ArrayList;
import java.util.List;

public class TcTestCase {
	
	private Long caseId; //Sub-ID
	private String caseName; //TSID_Category_Functionality??
	
	private String casePreReq;
	private String caseType;
	private String caseDesc;
	private String caseExpResult;
	private String AcceptCriteria;
	private String Priofity;
	

	private List<TcTestStep> cSteps;
	private List<TcTestStep> cBeforeRun;
	private List<TcTestStep> cAfterRun;
	private List<TcTestStep> cSkip;

	private List<Long> subCaseId;
	private List<List<TcTestStep>> cListSteps;
	private List<List<TcTestStep>> cListBeforeRun;
	private List<List<TcTestStep>> cListAfterRun;
	private List<List<TcTestStep>> cListSkip;

	
	

}

