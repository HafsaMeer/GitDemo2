package Automations;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import Automation.pageObjectModel.CartPage;
import Automation.pageObjectModel.CheckoutPage;
import Automation.pageObjectModel.ConfirmationPage;
import Automation.pageObjectModel.ProductCatalogue;
import TestComponents.BaseTests;
import TestComponents.Retry;


public class Errorvalidations extends BaseTests {
	ExtentReports report;

          @Test (groups= {"ErrorHandling"}, retryAnalyzer=Retry.class) // Now this is a testNG test
          public void LoginErrorvalidations() throws IOException, InterruptedException {
	
       String productName="ADIDAS ORIGINAL";// hum yahan globally prduct name add kren gay ta k cart me hm check kr sken k wahin product aai h cart me jo hm ny select ki thi.
     landingpage.loginApplication("learningID2022@gmail.com","H5T7qj@2NVPbt");//wrongpassword
      Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage());
		
	}
          @Test  // Now this is a testNG test
          public void ProductErrorValidations() throws IOException, InterruptedException {
 
		// TODO Auto-generated method stub
        	  
		
       String productName="ADIDAS ORIGINAL";// hum yahan globally prduct name add kren gay ta k cart me hm check kr sken k wahin product aai h cart me jo hm ny select ki thi.
      ProductCatalogue productcatlog=landingpage.loginApplication("learningID2022@gmail.com","H5T7qj@2NVPbtD");
		//ProductCatalogue productcatlog=new ProductCatalogue(driver); we are creating driver every time like for every object page so we are going to do is- the last action method of previous
		// object page we create our driver over their so that before ending up that method start driver for next action.
		List<WebElement> products=productcatlog.getProductList();
		productcatlog.addProductToCart(productName);
		CartPage cartpage=productcatlog.goToCartPage();
		//CartPage cartpage=new CartPage(driver); same wahi jo hm ny product catalog k sath kiya h 
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match); // all assertions will remain in the test cases because we are validating here and validations don't go in the object pages.
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmedMessage=confirmationpage.verifyTYMessage();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}

}
