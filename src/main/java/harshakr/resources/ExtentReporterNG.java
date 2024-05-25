package harshakr.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Demo Test Report");
		report.config().setDocumentTitle("Demo Tests");

		ExtentReports ext = new ExtentReports();
		ext.attachReporter(report);
		ext.setSystemInfo("Tester", "Harsha");
		return ext;
		
	}

}
