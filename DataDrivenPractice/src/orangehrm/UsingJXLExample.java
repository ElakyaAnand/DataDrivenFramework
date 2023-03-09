package orangehrm;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UsingJXLExample {
	
	
	String testvalues[][]= {};
	
	
	@DataProvider(name="datapprovider")
	public String[][] dataprovider() throws BiffException, IOException
	{
		testvalues=excelsheetdata();
		return testvalues;
		
	}
	
	@Test
	public String[][] excelsheetdata() throws BiffException, IOException
	{
		
		File filepath=new File("D:\\Excel creating\\Excel.xls");
		Workbook workbook=Workbook.getWorkbook(filepath);
		Sheet sheet= workbook.getSheet(0);
		int rowcount= sheet.getRows();
		int columncount= sheet.getColumns();
		
		
		 String testdata[][]= new String[rowcount-1][columncount];
		
		for(int i=1;i<rowcount;i++)
		{
			for(int j=0; j<columncount; j++)
			{
			
			testdata[i-1][j]=	sheet.getCell(j, i).getContents();
			}
		}return testdata;
	
		
	}
	
	
	@Test(dataProvider = "datapprovider")
	public void bothcorrectexample(String uname, String pwd) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver ob=new ChromeDriver();
		ob.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		Thread.sleep(3000);
		
		WebElement username=ob.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys(uname);
		
		WebElement password=ob.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys(pwd);
		
		WebElement loginbutton=ob.findElement(By.xpath("//button[@type='submit']"));
		loginbutton.click();
	}

}
