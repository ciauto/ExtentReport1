package extentreportdemo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC002_VerifyAddPayee {

	WebDriver driver;
	String baseurl = "http://zero.webappsecurity.com/";
	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void setup() {
		// where you want to save the report file
		report = ExtentFactory.getInstance();
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		ChromeOptions handlingSSL = new ChromeOptions();
		//Using the accept insecure cert method with true as parameter to accept the untrusted certificate
		handlingSSL.setAcceptInsecureCerts(true);
		//Creating instance of Chrome driver by passing reference of ChromeOptions object
		driver = new ChromeDriver(handlingSSL);
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void verifyAddPayee() {
		test=report.startTest("Add Payee Test");
		driver.findElement(By.id("signin_button")).click();
		test.log(LogStatus.INFO, "Clicked Signin button");
		driver.findElement(By.id("user_login")).sendKeys("username");
		test.log(LogStatus.INFO, "Entered username");
		driver.findElement(By.id("user_password")).sendKeys("password");
		test.log(LogStatus.INFO, "Entered password");
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.INFO, "Clicked Submit button");
		driver.findElement(By.xpath("//a[contains(text(),'Pay Bills')]")).click();
		test.log(LogStatus.INFO, "Clicked Pay Bills Button");
		driver.findElement(By.xpath("//a[contains(text(),'Add New Payee')]")).click();
		test.log(LogStatus.INFO, "Clicked Add Payee Button");
		driver.findElement(By.id("np_new_payee_name")).sendKeys("Telus");
		test.log(LogStatus.INFO, "Entered Payee Name");
		driver.findElement(By.id("np_new_payee_address")).sendKeys("123 holiday dr, Mississauga, L4T2Q9");
		test.log(LogStatus.INFO, "Entered Payee Address");
		driver.findElement(By.id("np_new_payee_account")).sendKeys("123455666");
		test.log(LogStatus.INFO, "Entered Payee Account Number");
		driver.findElement(By.id("np_new_payee_details")).sendKeys("Wireless");
		test.log(LogStatus.INFO, "Entered Payee Details");
		driver.findElement(By.id("add_new_payee")).click();
		test.log(LogStatus.INFO, "Clicked Add Payee Button");
		WebElement text = driver.findElement(By.id("alert_conten"));
		Assert.assertTrue(text != null);
		test.log(LogStatus.PASS, "Payee Successfully Added");
		}

	
	@AfterMethod 
	public void screenShot(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName());
			String imagepath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Test Failed", imagepath);
		}
		
	}
	@AfterClass
	public void tearDown() {
		driver.close();
		report.endTest(test);
		report.flush();

	}


	}

