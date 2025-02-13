package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import Base.BaseTest;

public class ExtentReporterNG extends BaseTest {
	public static Properties prop;


	public static ExtentReports getReportObject() {
	
		String path =".//Reports//ODWeb//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//ExtentReports reporter =new ExtentReports( ".//Reports//ODWeb//index.html",true);
		reporter.config().setReportName("Gmail Login Automation Results");
		reporter.config().setDocumentTitle("Gmail Automation Report");


		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;

	}

}
