package commonFunctionsPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility_Common_Functions {
	public static void evidenceFileCreator(String filename,String requestbody,String responsebody) throws IOException
	{
		File newfile=new File("D:\\Study\\Testing\\MR\\Rest assured\\RestAs Framework\\" +filename+".txt");
		System.out.println("A new text file created to record request and response of API :" +newfile.getName());
		
		FileWriter datawrite=new FileWriter(newfile);
		datawrite.write("request body :" +requestbody+"\n\n");
		datawrite.write("response body :" +responsebody);
		datawrite.close();
		System.out.println("Request body and Response body are saved in : " +newfile.getName());
		
	}
	
	public static ArrayList<String> readDataExcel(String Sheetname,String testcasename) throws IOException
	{
		ArrayList<String> ArrayData=new ArrayList<String>();
		//step1: create the object of File input stream
		FileInputStream fis=new FileInputStream("D:\\Study\\Testing\\MR\\Rest assured\\RestAs Framework\\Post_Test_Data.xlsx");
		//step2: access the excel file 
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		//step3: access the sheet name
		int countofsheet=workbook.getNumberOfSheets();
		// System.out.println(countofsheet);
		for (int i=0;i<countofsheet;i++)
		{
			String filesheetname=workbook.getSheetName(i);
			if(filesheetname.equalsIgnoreCase(Sheetname))
			{
				//step4: access the row from where the data is suppose to fetch
				XSSFSheet sheet=workbook.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				// Row r=rows.next();
				while(rows.hasNext())
				{
					Row r2=rows.next();
					if(r2.getCell(0).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						Iterator<Cell> cellvalues=r2.cellIterator();
						while(cellvalues.hasNext()) {
							String testdata=cellvalues.next().getStringCellValue();
							ArrayData.add(testdata);
						}
					}
				}
			}
		}
		workbook.close();
		return ArrayData;
		
	}
          
}
