package orangehrm;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExampleForPOI {
	
	static List<String> username=new ArrayList<String>();
	static List<String> password=new ArrayList<String>();
	
	
	
	
	
	
	public void excelsheetdata() throws IOException
	{
		
		FileInputStream filepath=new FileInputStream("D:\\Excel creating\\POI.xlsx");
		try (Workbook workbook = new XSSFWorkbook(filepath)) {
			Sheet sheet= workbook.getSheetAt(0);
			
			Iterator<Row> rowiterator= sheet.iterator();
			while(rowiterator.hasNext())
			{
				Row rowcount= rowiterator.next();
				
				Iterator<Cell> celliterator= rowcount.iterator();
				int i=2;
				while(celliterator.hasNext())
				{
					
				
					if(i%2==0)
				{
						username.add(celliterator.next().getStringCellValue());
				}
					else
					{
						password.add(celliterator.next().getStringCellValue());
					}
					i++;
			}
			}
		}
		
	}
	
	public void orangehrm(String uname, String pwd) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver ob=new ChromeDriver();
		ob.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		Thread.sleep(3000);
		
		WebElement usernametextbox=ob.findElement(By.xpath("//input[@name='username']"));
		usernametextbox.sendKeys(uname);
	
		
		WebElement passwordtextbox=ob.findElement(By.xpath("//input[@name='password']"));
		passwordtextbox.sendKeys(pwd);
		
		
		
		WebElement loginbutton=ob.findElement(By.xpath("//button[@type='submit']"));
		loginbutton.click();
		}
	
	public void executelogin() throws InterruptedException
	{
		for(int i=0; i<username.size();i++)
		{
			orangehrm(username.get(i), password.get(i));
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ExampleForPOI forPOI=new ExampleForPOI();
		forPOI.excelsheetdata();
		System.out.println(username);
		System.out.println(password);
		forPOI.executelogin();
		
	}

}


