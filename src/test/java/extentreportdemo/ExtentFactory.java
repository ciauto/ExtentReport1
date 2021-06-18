package extentreportdemo;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
	public static ExtentReports getInstance() {
		ExtentReports extent;
		String path= "C:\\eclipse-workspace-201906\\ExtentReport1\\reports\\usingextentfactory_report.html";
		extent = new ExtentReports(path, false);
		extent.addSystemInfo("Selenium Version", "3.14");
		extent.addSystemInfo("Server Environment", "QA");
		extent.addSystemInfo("Platform", "Windows");
		return extent;
	}

}
