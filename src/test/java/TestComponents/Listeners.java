package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import GlobalProperties.ExtentReporterTestNG;

public class Listeners extends BaseTests implements ITestListener {
	ExtentTest test;// this holds entry in your test case.
	ExtentReports report =ExtentReporterTestNG.getObjectReporter();
	ThreadLocal<ExtentTest> report_test=new ThreadLocal<ExtentTest>(); // thread safe mechanism
	
	/* The ThreadLocal class is used to create thread local variables which can only be read and written by the same thread. 
	 * For example, if two threads are accessing code having reference to same threadLocal variable then each thread will not see any modification to threadLocal variable done by other thread
	 * we are using threadlocal becausewhen we are using parallel property in the xml file the error was showing in anyother methos but when we remove this property, the method name was different.
	 * So we are handling it through threads.*/
	//----------------------
	
	// we also have to include listeners in the testng xml file.

	public void onTestStart(ITestResult result) {  
		test=report.createTest(result.getMethod().getMethodName()); 
		report_test.set(test);// unique thread id of Err
		}  
		  
		@Override  
		public void onTestSuccess(ITestResult result) {  
			report_test.get().log(Status.PASS, "Test Pass");
		
		}  
		  
		@Override  
		public void onTestFailure(ITestResult result) {  
			//test.log(Status.FAIL, "Test Fail"); 
			//test.fail(result.getThrowable()); without thread using it
			report_test.get().fail(result.getThrowable());// with thread
		//getThrowable method is used to print error on the report.
			//get screenshot and attach with the report
			// get the info of driver 
			try {
				driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
				// we are using class here because fields are associated with classes and not methods.
			} 
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			
			//We have multiple catches here but we can keep it in general exception
			/*catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			String filePath = null;
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			report_test.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
				
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
			report.flush();
		} 
	
}
