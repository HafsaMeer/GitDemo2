package GlobalProperties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {
	
	public static ExtentReports getObjectReporter()
	{
		String path= System.getProperty("user.dir")+"\\Reports\\index.html"; //index file will be created here
  		 ExtentSparkReporter reporter=new ExtentSparkReporter(path);// this is a helper class of main ExtentReports class
  		 reporter.config().setReportName("Web Automation Results");//Giving the name to report
  		 reporter.config().setDocumentTitle("Automation Results");// Name of the document show in the tab
  		 
  		ExtentReports report =new ExtentReports(); // This is the main class which is responsible for report execution.
  		 report.attachReporter(reporter);
  		 report.setSystemInfo("Tester", "Rahul");
  		 return report;
	}

}
