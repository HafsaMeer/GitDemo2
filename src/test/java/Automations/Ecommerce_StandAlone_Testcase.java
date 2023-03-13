package Automations;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.pageObjectModel.CartPage;
import Automation.pageObjectModel.CheckoutPage;
import Automation.pageObjectModel.ConfirmationPage;
import Automation.pageObjectModel.OrderPage;
import Automation.pageObjectModel.ProductCatalogue;
import TestComponents.BaseTests;

public class Ecommerce_StandAlone_Testcase extends BaseTests {

	String productName="ADIDAS ORIGINAL";          
	@Test(dataProvider="getData", groups="Purchase")  // Now this is a testNG test
          public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
       // we can keep the hashmap as object but loginApplication method is returning string type method. so we are changing it.
		// TODO Auto-generated method stub
        	  
		
       // hum yahan globally prduct name add kren gay ta k cart me hm check kr sken k wahin product aai h cart me jo hm ny select ki thi.
      ProductCatalogue productcatlog=landingpage.loginApplication(input.get("email"),input.get("password"));
		//ProductCatalogue productcatlog=new ProductCatalogue(driver); we are creating driver every time like for every object page so we are going to do is- the last action method of previous
		// object page we create our driver over their so that before ending up that method start driver for next action.
		List<WebElement> products=productcatlog.getProductList();
		productcatlog.addProductToCart(input.get("productName"));
		CartPage cartpage=productcatlog.goToCartPage();
		//CartPage cartpage=new CartPage(driver); same wahi jo hm ny product catalog k sath kiya h 
		Boolean match = cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match); // all assertions will remain in the test cases because we are validating here and validations don't go in the object pages.
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmedMessage=confirmationpage.verifyTYMessage();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("order completed");
		System.out.println("Best of luck");
		
		
	}
          //To verify ADIDAS ORIGINAL is displaying in the orders page 
          @Test (dependsOnMethods={"submitOrder"}) // we have dependency on above method if the order will be placed then we will see it in the orders page.
          public void OrderHistoryTest()
          {
        	    ProductCatalogue productcatlog=landingpage.loginApplication("learningID2022@gmail.com","H5T7qj@2NVPbtD");
        	   OrderPage orderpage= productcatlog.goToOrdersPage();
        	 Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
          }
 //we cannot create 100 classes for 100 tests we should intelligently keep one type of test in one class and their respective tests at one place.
          // <suite parallel="tests"  name="Suite"> this parallel property in testng file operate all the tests under suite run paralelly and invoke multiple browsers at a time.
    
         
          public String getScreenshot(String screenshot) throws IOException
          {
        	  TakesScreenshot tss=(TakesScreenshot)driver;
        	  File source=tss.getScreenshotAs(OutputType.FILE);
        	  File file=new File(System.getProperty("user.dir")+"\\Screenshots\\" + screenshot + ".png" );
        	  FileUtils.copyFile(source, file);
        	  return System.getProperty("user.dir")+"\\Screenshots\\" + screenshot + ".png" ;//return the path
          }
          
          //Parameterizing through dataprovider 
          /* We are using data provider here to get the data through jason. JSON is an acronym for JavaScript Object Notation, is an open standard format, which is lightweight and text-based,
           *  designed explicitly for human-readable data interchange.It is a language-independent data format. It supports almost every kind of language, framework, and library.
           * JASON is very famous in driving the data from external sources like Excel */
          
          @DataProvider
    public Object[][] getData() throws IOException
    {
        /*
         * //parametrizing through hashmap - This is manually created hashmap
        	  HashMap<String,String> map=new HashMap<String,String>();
        	// we can keep the hashmap as object but loginApplication method above is returning string type method. so we are changing it.
        	  map.put("email", "learningID2022@gmail.com");
        	  map.put("password","H5T7qj@2NVPbtD");
        	  map.put("productName","ADIDAS ORIGINAL");
        	  HashMap<String,String> map1=new HashMap<String,String>(); 
        	  map1.put("email", "learningID2022@gmail.com");
          	  map1.put("password","H5T7qj@2NVPbtD");
          	  map1.put("productName","ZARA COAT 3");
          	  
          	 Now we have jason file through which we will automatically get hashmap. so we are commenting this manual hashmap.
          	  */
          	//read jason from this link https://www.json.org/json-en.html
        	  
        	  List<HashMap<String,String>> data= getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\JASON_File\\PurchaseOrder.jason");
        	  return new Object[][] { {data.get(0)},{data.get(1)} };// two dimensional array with two data set in curly braces.object is parent-type data type of all data types and it accept int, string, boolean every datatype data.
    //we cannot enter 15 to 20 emails and passwords like this. for that purpose we need hashmap
    }








}
