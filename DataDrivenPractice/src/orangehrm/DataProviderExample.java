package orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

public class DataProviderExample {
	
	
	
	String testvalues[][]= {
			{"Admin", "admin123"},
			{"Admin1", "admin1231"},
			{"Admin1", "admin123"},
			{"Admin", "admin1231"}
			
	};
	
	@DataProvider(name="datapprovider")
	public String[][] dataprovider()
	{
		return testvalues;
		
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
