package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		
		test = extent.createTest(result.getMethod().getMethodName());		//test entry created
		extentTest.set(test);												//unique thread id(ErrorValidationTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		
		extentTest.get().log(Status.PASS, "Test Passed");					//extentTest.get() will get the same test thread which was assigned above 
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		
		extentTest.get().fail(result.getThrowable());   				 //this will show error msg in the report
		
		try 
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} 
		catch (Exception e1) 
		{

			e1.printStackTrace();
		}
		
		String filePath = null;
		try 
		{
			//taking screenshot
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		//Attaching Screenshot to report
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}
	
	
	
	

}
