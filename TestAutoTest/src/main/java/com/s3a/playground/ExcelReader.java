package com.s3a.playground;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    public static final String EXCEL_FILE_PATH = "C:\\Users\\samantha.liu\\Dev\\workspace\\SVN\\Testing\\sample-xlsx-file.xlsx";
    public static final Integer SUMMARY_PREFIX = 0;
    public static final Integer SUMMARY_HEADER = 10; // Header row for the summary page
    public static final Integer TC_HEADER = 1;
    public static Integer sheetTtl=0;
    
    public static void main(String[] args) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        sheetTtl=workbook.getNumberOfSheets();
        System.out.println("Workbook has " + sheetTtl + " Sheets : ");
        
        /* https://www.callicoder.com/java-read-excel-file-apache-poi/
        ==================================================================
        Iterating over all Sheets (Multiple ways)
        ==================================================================
	     */
        // Iterate through each sheet
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        
        
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
            /**** PARSE SHEET NAME ****/
            
            getCellValue(sheet);
            
            System.out.println();
            System.out.println();
            
        }
        
        
        /* https://www.callicoder.com/java-read-excel-file-apache-poi/
        ==================================================================
        Iterating over all the rows and columns in a Sheet (Multiple ways)
        ==================================================================
	     */
	
//	     // Getting the Sheet at index zero
//	     Sheet sheet = workbook.getSheetAt(0);
//	     getCellValue(sheet);
	
	

	    
	        

        workbook.close();
    }
    
    
    private static void getCellValue(Sheet sheet) {
	     // Create a DataFormatter to format and get each cell's value as String
	     DataFormatter dataFormatter = new DataFormatter();
	     
    	 System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
	     for (Row row: sheet) {
	         for(Cell cell: row) {
	        	 
	        	    switch (cell.getCellTypeEnum()) {
	                case BOOLEAN:
	                    System.out.print(cell.getBooleanCellValue());
	                    break;
	                case STRING:
	                    System.out.print(cell.getRichStringCellValue().getString());
	                    break;
	                case NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        System.out.print(cell.getDateCellValue());
	                        System.out.print(" | " + dataFormatter.formatCellValue(cell));
	                    } else {
	                        System.out.print(cell.getNumericCellValue());
	                    }
	                    break;
	                case FORMULA:
	                    System.out.print(cell.getCellFormula());
	                    break;
	                case BLANK:
	                    System.out.print("");
	                    break;
	                default:
	                    System.out.print("");
	            }

		             System.out.print( "\t\t");

	             //String cellValue = dataFormatter.formatCellValue(cell);
	             //System.out.print(cellValue + "\t");
	         }
	         System.out.println();
	     }
    	
    }
}