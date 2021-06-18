package extentreportdemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ExtentReportDemo {
	
	
	WebDriver driver;
	ExtentReports rep;
	ExtentTest test;


@BeforeMethod
	public void setup() {
	
	rep = new ExtentReports("C:\\eclipse-workspace-201906\\ExtentReport1\\src\\test\\resources\\extentreports\\extent.html");
	
	System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
	
	ChromeOptions handlingSSL = new ChromeOptions();
	//Using the accept insecure cert method with true as parameter to accept the untrusted certificate
	handlingSSL.setAcceptInsecureCerts(true);
	//Creating instance of Chrome driver by passing reference of ChromeOptions object
	driver = new ChromeDriver(handlingSSL);
	
	driver.get("http://zero.webappsecurity.com/");

	driver.manage().window().maximize();
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}


@Test
public void verifylogin() {

// TODO Auto-generated method stub				
//setting up the chrome browser driver

	test = rep.startTest("verify Login test started");
	driver.findElement(By.id("signin_button")).click();
	test.log(LogStatus.INFO, "Clicked Sign in button");
	driver.findElement(By.id("user_login")).sendKeys("username");
	test.log(LogStatus.INFO, "Entered Username");
	driver.findElement(By.id("user_password")).sendKeys("password");
	test.log(LogStatus.INFO, "Entered password");
	driver.findElement(By.name("submit")).click();
	test.log(LogStatus.INFO, "Clicked Submit button");
	String actualTitle=driver.getTitle();

	String expectedTitle = "Zero - Account Summary";
	Assert.assertEquals(actualTitle, expectedTitle);

	
}

@AfterMethod
	public void tearDown() {
	
	driver.quit();
	rep.flush();
}

}
