package TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.pageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver intializeDriver() throws IOException {
		
        //properties class use to read global properties
		/* Global properties- Global variables are generally static in nature, can be initialized at the start of a test, and remain available throughout the entire test run.
		 *like we declare chrome, firefox and edge properties at the start to ensure that the project will run throughout in the respective driver.
		 */
		Properties property=new Properties();
	//FileInputStream file=new FileInputStream("C:\\Users\\Abbas  computers\\eclipse-workspace\\SeleniumMavaenDesign\\src\\main\\java\\GlobalProperties\\GlobalData.properties");
		// yeh path hr system py different hota h to agr hm general tareeka dekhein gay jis me yeh path kabi error nhi dy ga.
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\GlobalProperties\\GlobalData.properties");
		property.load(file);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") :property.getProperty("browser");
	
		//if yes command executed after ? mark
		// if no command executed after : mark
		//property.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();//WebDriverManager is an open-source Java library that automatically performs the four steps
			//(find, download, setup, and maintenance) mentioned above for the drivers required for Selenium tests.We don't need to give the path of chrome or firefox drivers
			//downloaded in the system to the selenium.WebDriverManager offers cross browser testing without the hassle of installing and maintaining different browser driver binaries.
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			/*A headless browser is just like any other browser, the only difference is we cannot see anything on the screen. Here we can say that the program actually runs in the backend and nothing can be viewed on the screen. 
			 * Thus, it is known to be the one without a Head/GUI. In simple words Browser without browser.Headless browsers provide automated control of a web page in an environment similar to popular web browsers, but they are executed via a command-line interface or using network communication.
			 *  They are particularly useful for testing web pages as they are able to render and understand HTML the same way a browser would, including styling elements such as page layout, colour, font selection and execution of JavaScript and Ajax which are usually not available when using other testing methods.
			 *  */
			driver=new ChromeDriver(options);
			
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			// Firefox code same as we have for chrome
		}
		else if (browserName.contains("edge"))
		{
			EdgeOptions options=new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver=new EdgeDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod (alwaysRun=true)// this is generic for the whole project and groups property will show error here so we give it always run property.
	public LandingPage launchApplication() throws IOException// hr testcase sy pehle hmein rahul shetty login page py hona chiye to wo bi hm general declare krwane lge hn.
	{
        driver=intializeDriver();
		 landingpage=new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	@AfterMethod (alwaysRun=true)// this is generic for the whole project and groups property will show error here so we give it always run property.
	public void closeBrowser()
	{
		driver.close();
		
	}
	
	// we place this method here because in the test-class(ecommerce standalone testcase) this class is parent class so we don't need to create the object of this class in the test class

	public List<HashMap<String,String>> getJasonDataToMap(String filePath) throws IOException
	{
		//read jason to string 
		/*String jasonContent =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\JASON_File\\PurchaseOrder.jason"),
				StandardCharsets.UTF_8); we cannot hard code this path name*/ 
		String jasonContent =FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
	// convert string into hashmap
				ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data =mapper.readValue(jasonContent,new TypeReference<List<HashMap<String,String>>>(){});
			return data;
		
	}
	
	  public String getScreenshot(String screenshot, WebDriver driver) throws IOException
      {
    	  TakesScreenshot tss=(TakesScreenshot)driver;
    	  File source=tss.getScreenshotAs(OutputType.FILE);
    	  File file=new File(System.getProperty("user.dir")+"\\Screenshots\\" + screenshot + ".png" );
    	  FileUtils.copyFile(source, file);
    	  return System.getProperty("user.dir")+"\\Screenshots\\" + screenshot + ".png" ;//return the path
      }
}
