package com.inetBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Reporting extends TestListenerAdapter{
	
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest logger;
	
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		String reportName = "Test - Report " +timeStamp+ ".html";
		
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+reportName);
		try {
			spark.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Nikhila");
		
		spark.config().setDocumentTitle("Internet banking Test Project");
		spark.config().setReportName("Functional Test Report");
		spark.config().setTheme(Theme.DARK);
		
		
	}
	
	public void onTestSuccess(ITestResult tr) {
		
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); //send the passed information to the report
	}
	
	
	public void onTestFailure(ITestResult tr) {
		
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the passed information to the report
		
		String screenShotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenShotPath);
		
		if(f.exists()) {
			
				logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenShotPath));
			
		}
		
	}
	
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
	
	
	
	
	
	

}
