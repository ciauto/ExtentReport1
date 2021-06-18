package extentreportdemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport1 {

	WebDriver driver;
	String baseurl = "http://zero.webappsecurity.com/";
	ExtentReports report; //to generate the HTML report
	ExtentTest test; // to log the test steps

	@BeforeClass
	public void setup() {
		
		// where you want to save the report file
		
		report = new ExtentReports("C:\\eclipse-workspace-201906\\ExtentReport1\\reports\\report1.html");
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		ChromeOptions handlingSSL = new ChromeOptions();
		//Using the accept insecure cert method with true as parameter to accept the untrusted certificate
		handlingSSL.setAcceptInsecureCerts(true);
		//Creating instance of Chrome driver by passing reference of ChromeOptions object
		driver = new ChromeDriver(handlingSSL);
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void verifyLogin() {
		// start the test
		test = report.startTest("VERIFY LOGIN");
		driver.findElement(By.id("signin_button")).click();
		
		test.log(LogStatus.INFO, "Clicked Signin button");
		
		driver.findElement(By.id("user_login")).sendKeys("username");
		
		test.log(LogStatus.INFO, "Entered username");
		
		driver.findElement(By.id("user_password")).sendKeys("password");
		test.log(LogStatus.INFO, "Entered password");
		
		driver.findElement(By.name("submit")).click();
		
		test.log(LogStatus.INFO, "Clicked Submit button");
		WebElement text = driver.findElement(By.xpath("//a[contains(text(),'Account Activity')]"));
		Assert.assertTrue(text != null);
		
		}

	
	@AfterClass
	public void tearDown() {
		driver.close();
		report.endTest(test);
		report.flush();
	}

}
