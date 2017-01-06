

package ocsftester ; 



import java.io.*;

import org.apache.poi.openxml4j.exceptions.*;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;




class excel  {
	public int []  reader(){
		int n = 30 ; 
		int f = 0 ;
		
		double pelwt = 0.00004354 ;
		  double[] Weights = new double [n];
		  int[] pellets = new int [n] ; 
		  
		  
   
		        String READ = "J:/ALL_USERS/Kuiper_Jason/EXERCISE/ENERGY RESTRICTION/DAY_X.xlsx";
		        
		        
		    
		       try{
		        	 Workbook wb = WorkbookFactory.create(new FileInputStream(READ));
		        	 Sheet sheet1 = wb.getSheetAt(0);
		        	
//System.out.println("This is gonna be ugly...   " + "Opening " + READ);
//System.out.println("PAIR"+"\t"+"FOOD WEIGHT");
		        	   
		        	 
		        	 for (Row row : sheet1) {
		        	        for (Cell cell : row) {

		        	            switch (cell.getCellType()) {
		        	            
		        	                case Cell.CELL_TYPE_FORMULA:
		        	                		Weights[f] =  cell.getNumericCellValue() ;
		        	                		pellets[f] =  (int) Math.round(Weights[f]/pelwt) ;
		        	                		
		        	                		
 		        	                case Cell.CELL_TYPE_NUMERIC:
	        	                		Weights[f] =  cell.getNumericCellValue() ;
	        	                		pellets[f] = (int) Math.round(Weights[f]/pelwt) ;
	        	                		
	        	                			
		        	                break;
		        	               
		        	                default:
		        	                    	System.out.println() ;
		        	                 		} f++;
		        	            
		        	        								} 
//  PRINTS A TABLE OF DATA
//  System.out.println(i + "\t" + Weights[i]);
// i++ ; 
		        	    					}
		        	 
// PRINTS THE DATA IN THE ARRAY
// for(int p = 0;p<n;){
//   	System.out.println(Weights[p]);
//   	p++;
//    }     	 
		        }catch(InvalidFormatException ex) {
			            System.out.println(
			                "Invalid File Format '" + 
			                READ + "'"); 
		       
		        }catch(FileNotFoundException ex) {
		            System.out.println(
			                "Unable to open file '" + 
			                READ + "'");
		        }catch(IOException ex) {
		        	System.out.println(
		                "Error reading file '" 
		                + READ + "'");
		        }
			return pellets;
	}	
	
//create a method that will retrieve data from the log


	


	public void writer(String d , int[] info , int r){		        		
		
		String WRITE = "C:/BLAH/DISP_LOG.xlsx";
		

		try{
		 	 Workbook wb = WorkbookFactory.create(new FileInputStream(WRITE));
		 	 Sheet sheet = wb.getSheetAt(0);
		 	 
		 	 CreationHelper createHelper = wb.getCreationHelper();
		 	 Row row = sheet.createRow((short)0);
		 
			
			 
		 	 
		  row.createCell(0).setCellValue( createHelper.createRichTextString("DATE"));
		  for(int c = 0 ; c<info.length ; c++ ){
		  row.createCell(c+1).setCellValue( createHelper.createRichTextString("PD" + (c+1)));
		  }	
		  
		 	 
		 		 Row row1 = sheet.createRow((short)r);
		 		row1.createCell(0).setCellValue( createHelper.createRichTextString(d));
		 		
		 		 for (int c = 0; c<info.length ; c++){
		  
		 			 row1.createCell(c+1).setCellValue(info[c]) ; 
		 		 }
		 	   
		 	  
		 	    FileOutputStream fileOut = new FileOutputStream(WRITE);
		 	    wb.write(fileOut);
		 	    fileOut.close();
		 	                     
		 	
		 }catch(InvalidFormatException ex) {
		         System.out.println(
		             "Invalid File Format '" + 
		             WRITE  + "'"); 

		 }catch(FileNotFoundException ex) {
		     System.out.println(
		             "Unable to open file '" + 
		             WRITE + "'");
		 }catch(IOException ex) {
		 	System.out.println(
		         "Error reading file '" 
		         + WRITE + "'");
		 }
}	
}

		
