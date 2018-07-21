package com.s3a.poc.test.model;

public enum TcDoType {


	// general elements
	FetchingPage,getText,click,sendKeys,clear,
	// dropdown menu
	selectByIndex,selectByVisibleText,selectByValue,
	// checking
	isSelected,isEnabled,isDisplayed,noThing,
	
	//to be improved
	//special handling
	imWait, alert, 
	
	// window switch
	winSwitch,
	
	// sikuli - general elements
	siWrite, siClick, siHover;
	
	

}
