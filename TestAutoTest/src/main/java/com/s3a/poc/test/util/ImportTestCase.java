package com.s3a.poc.test.util;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.s3a.poc.test.model.DbSummaryDesc;
import com.s3a.poc.test.model.DbTestCase;
import com.s3a.poc.test.model.DbTestSteps;
import com.s3a.poc.test.repository.SummaryDescRepository;
import com.s3a.poc.test.repository.TestCaseRepository;
import com.s3a.poc.test.repository.TestStepsRepository;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ImportTestCase {
    public static Integer sheetTtl=0;
    
	private static final Logger logger = LoggerFactory.getLogger(ImportTestCase.class);

	
    public static void TestCaseParser(String filepath, Integer headerRow, Date date, Integer version, SummaryDescRepository descRep, TestCaseRepository tcRep, TestStepsRepository tsRep) throws Exception {
//    	DbTestSteps tstep = new DbTestSteps();
//    	DbTestCase sdesc = new DbTestCase();
    	
    	File file=new File(filepath);                                                  /*#** ADD FUNCTION HANDLE FILE NOT FOUND EXCEPTION **#*/
     	 // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook;
		workbook = WorkbookFactory.create(file);
        
        
        String fileName = file.getName();
        String[] filenames = fileName.split("_");
        logger.info("File Name: " + fileName);
        /*** Do sth with File Name ***/
//        if(names.length >=2){
//
//            String name = names[1].substring(0,names[1].lastIndexOf("."));
//            tcase.setCaseName(name);
//            tcase.setCaseNo(names[0]);
//        }
        

        // Retrieving the number of sheets in the Workbook
        sheetTtl=workbook.getNumberOfSheets();
        System.out.println("Workbook has " + sheetTtl + " Sheets : ");
        logger.info("*Workbook has " + sheetTtl + " Sheets : ");
        
        /* https://www.callicoder.com/java-read-excel-file-apache-poi/
        ==================================================================
        Iterating over all Sheets (Multiple ways)
        ================================================================== */
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        
        
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            String sheetName = sheet.getSheetName();
            
            logger.info("=> " + sheetName);
            Integer sheetID= null;
            
            /**** PARSE SHEET NAME ****/
            String[] sheetNames = sheetName.split("_");
            if(sheetNames.length >=2){
//                String name = names[1].substring(0,names[1].lastIndexOf("."));            /*#** ADD FUNCTION TO CHECK DATA FORMAT #_abc & availability in Test Case **#*/
            	sheetID=Integer.parseInt(sheetNames[0]);
            }
            
            /*
             * If no "_" -> skip
             * If prefix with 0 -> separate read | Desc list (0 to summaryHeader) & TestCase List (from summaryHeader+1)
             * If prefix with number -> read from headerRow+1 | TestStep List  ** check header title?
             * If prefix NOT number -> logger.error
             * 
             */
            
            
            /* Skip sheet named without "_" */
            if(sheetID!=null) { 
            	
            	/* Parse Test Case */                                                    /*#** ADD FUNCTION TO CHECK HEADER **#*/
            	if(sheetID == 0) {
            		/***** Separate Reading *****/
            		System.out.println("Reading Summary Test Cases....");
            		
            		  // Create a DataFormatter to format and get each cell's value as String
	           	     DataFormatter dataFormatter = new DataFormatter();
	           	     
	               	 System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
       	        	 DbSummaryDesc summaryDesc = new DbSummaryDesc();
	           	     for (Row row: sheet) {
	           	    	 
	           	    	 /*** Summary Description List (to summaryHeader-1) [Note: index from 0] ***/
	           	    	 if (row.getRowNum() < headerRow-1 ) {
	           	        	summaryDesc = setSummaryDescValue(row, dataFormatter, summaryDesc, date);
		           	         System.out.println(summaryDesc.toString());
		           	         logger.info(summaryDesc.toString());
	           	    	 }
	           	    	 
	           	    	 
	           	    	 /*** TestCase List (from summaryHeader+1) [Note: index from 0] ***/
	           	    	 if (row.getRowNum() >= headerRow ) {
	           	        	 DbTestCase tcase = new DbTestCase();
		                 	 tcase = setTestCaseValue(row, dataFormatter, tcase);
		                 	 tcase.setVersion(version);
		           	         System.out.println(tcase.toString());
	
	 	             		 if(tcase!=null) { tcRep.save(tcase);};
	           	    	 }
	           	     }
	           	     summaryDesc.setVersion(version);
	           		
	             	 if(summaryDesc!=null) { descRep.save(summaryDesc);};
            	}
            	
            	
            	/* Parse Test Step tabs */                                                    /*#** ADD FUNCTION TO CHECK HEADER **#*/
            	if(sheetID > 0) {
            		System.out.println("Reading Test Steps....");
//            		/***** Read Test Steps *****/
            		int tsHeaderRow=1;
            		
          		  // Create a DataFormatter to format and get each cell's value as String
	           	     DataFormatter dataFormatter = new DataFormatter();
	           	     
	               	 System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
	           	     for (Row row: sheet) {
	           	    	 /*** TestSteps List (from Header+1) [Note: index from 0] ***/
	           	    	 if (row.getRowNum() >= tsHeaderRow ) { 
		                     DbTestSteps tstep = new DbTestSteps();
		                     tstep.setModId(sheetID);
		                     tstep.setModName(sheetNames[1]);
		                     
		                     tstep = setTestStepsValue(row, dataFormatter, tstep);
		           	         System.out.println(tstep.toString());
	
		           	         tstep.setVersion(version);
	 	             		 if(tstep!=null) { tsRep.save(tstep);};
	           	    	 }
	           	     }
            	}
            }
            
            System.out.println();
            System.out.println();
            
        }
        workbook.close();
		
    }
    
    
	public static DbSummaryDesc setSummaryDescValue(Row row, DataFormatter dataFormatter, DbSummaryDesc summaryDesc, Date date) {
  
		     logger.info("ROW: " + row.getRowNum());
		     
//		     String TitleCell= row.getCell(0).getStringCellValue();
		     Cell ValueCell= row.getCell(4);
		     String cellValue = null;
		     Boolean blank=true;
		     
    		 if (ValueCell != null && ValueCell.getCellType() != Cell.CELL_TYPE_BLANK && ValueCell.getStringCellValue() != "null") {
    			 blank=false;
	                 logger.info("CELL: " + ValueCell.getStringCellValue());
    			 cellValue = dataFormatter.formatCellValue(ValueCell);
    		 }
   		     
               switch (row.getRowNum()){
               	case 1:
               		summaryDesc.setApplication(cellValue);
               	case 2:
               		summaryDesc.setDescription(cellValue);
               	case 3:
               		summaryDesc.setModules(cellValue);
               	case 4:
               		summaryDesc.setDatasource(cellValue);
               	case 5:
               		summaryDesc.setUsers(cellValue);
               	case 6:
               		summaryDesc.setImpact(cellValue);
               };
        
               summaryDesc.setCreateDateTime(date);
        System.out.println();
        
        return summaryDesc;
    }



    
	private static DbTestCase setTestCaseValue(Row row, DataFormatter dataFormatter, DbTestCase tcase) {
	         for(Cell cell: row) {                                                  /*#** ADD FUNCTION TO HANDLE EXCEPTIONS **#*/
	        	
 	                 logger.info("ROW: " + row.getRowNum());

        		     String cellValue=null;
        		     Boolean blank=true;
        		     
	        		 if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && cell.getStringCellValue() != "null") {
	        			 blank=false;
	 	                 logger.info("CELL: " + cell.getStringCellValue());
	        			 cellValue = dataFormatter.formatCellValue(cell);
	        		 }
	                
	                System.out.print(cellValue + "\t");
	                logger.info(cell.getColumnIndex() + " |"+cellValue+"| ");
	                switch (cell.getColumnIndex()){
	                	case 0:
	                		tcase.setTsId(blank?null:cellValue);
	                	case 1:
	                		tcase.setCategory(cellValue);
	                	case 2:
	                		tcase.setmId(blank?null:Integer.valueOf(cellValue));
	                	case 3:
	                		tcase.setModule(cellValue);
	                	case 4:
	                		tcase.setFunction(cellValue);
	                	case 5:
	                		tcase.setTcId(cellValue);
	                	case 6:
	                		tcase.setSubId(cellValue);
	                	case 7:
	                		tcase.setPreReq(cellValue);
	                	case 8:
	                		tcase.setTcType(cellValue);
	                	case 9:
	                		tcase.setTcDesc(cellValue);
	                	case 10:
	                		tcase.setTcResultDesc(cellValue);
	                	case 11:
	                		tcase.settSteps(cellValue);
	                	case 12:
	                		tcase.setIsAcceptCritera(Boolean.valueOf(cellValue));
	                };
	                
//	        	    switch (cell.getCellTypeEnum()) {
//	        	    
//	                case BOOLEAN:
//	                    System.out.print(cell.getBooleanCellValue());
//	                    tcase.setIsAcceptCritera(cell.getBooleanCellValue());
//	                    break;
//	                case STRING:
//	                    System.out.print(cell.getRichStringCellValue().getString());
//	                    break;
//	                case NUMERIC:
//	                    if (DateUtil.isCellDateFormatted(cell)) {
//	                        System.out.print(cell.getDateCellValue());
//	                        System.out.print(" | " + dataFormatter.formatCellValue(cell));
//	                    } else {
//	                        System.out.print(cell.getNumericCellValue());
//	                    }
//	                    break;
//	                case FORMULA:
//	                    System.out.print(cell.getCellFormula());
//	                    break;
//	                case BLANK:
//	                    System.out.print("");
//	                    break;
//	                default:
//	                    System.out.print("");
//	            }
//
//		             System.out.print( "\t\t");
	         }
	         System.out.println();
	         
	         return tcase;
	     }


    
	private static DbTestSteps setTestStepsValue(Row row, DataFormatter dataFormatter, DbTestSteps tstep) {
	         for(Cell cell: row) {                                                  /*#** ADD FUNCTION TO HANDLE EXCEPTIONS **#*/
	        	
 	                 logger.info("ROW: " + row.getRowNum());

        		     String cellValue=null;
        		     Boolean blank=true;
        		     
	        		 if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
	        			 blank=false;
	        			 cellValue = dataFormatter.formatCellValue(cell);
	        		 }
	                
	                System.out.print(cellValue + "\t");
	                logger.info(cell.getColumnIndex() + " |"+cellValue+"| ");
	                
//	        	    switch (cell.getCellTypeEnum()) {
//	        	    
//	                case BOOLEAN:
//	                    System.out.print(cell.getBooleanCellValue());
//	                    tcase.setIsAcceptCritera(cell.getBooleanCellValue());
//	                    break;
//	                case STRING:
//	                    System.out.print(cell.getRichStringCellValue().getString());
//	                    break;
//	                case NUMERIC:
//	                    if (DateUtil.isCellDateFormatted(cell)) {
//	                        System.out.print(cell.getDateCellValue());
//	                        System.out.print(" | " + dataFormatter.formatCellValue(cell));
//	                    } else {
//	                        System.out.print(cell.getNumericCellValue());
//	                    }
//	                    break;
//	                case FORMULA:
//	                    System.out.print(cell.getCellFormula());
//	                    break;
//	                case BLANK:
//	                    System.out.print("");
//	                    break;
//	                default:
//	                    System.out.print("");
//	            }
	                switch (cell.getColumnIndex()){
	                
	                	case 0:
	    	        		tstep.setStepNo(blank?null:Integer.valueOf(cellValue));
	                	case 1:
	    	        		tstep.setStepName(cellValue);
	                	case 2:
	    	        		tstep.setGetValue(cellValue);
	                	case 3:
	    	        		tstep.setGetType(cellValue);
	                	case 4:
	    	        		tstep.setDoType(cellValue);
	                	case 5:
	    	        		tstep.setDoValue(cellValue);
	                	case 6:
	    	        		tstep.setIsScreenCap(blank?null:Boolean.valueOf(cellValue));
	                	case 7:
	    	        		tstep.setIsRevResult(blank?null:Boolean.valueOf(cellValue));
	                	case 8:
	    	        		tstep.setSkipStep(cellValue);
	                	case 9:
	    	        		tstep.setRemark(cellValue);
	                };
	         }
	         System.out.println();
	         
	         return tstep;
	     }

}