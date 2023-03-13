package Automation.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstracrComponents.Abstract_Components;

public class CheckoutPage extends Abstract_Components {

WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(css="[placeholder='Select Country']")
	 WebElement country;
	@FindBy(css=".ta-item:nth-of-type(2)")
	 WebElement selectcountry;
	@FindBy(css=".action__submit")
	 WebElement submit;
	
	public void selectCountry(String CountryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
	     selectcountry.click();
	     JavascriptExecutor js=(JavascriptExecutor)driver; // this is java script executor to do scrolling
			//we use this window.scrollBy(0,500)script in console of any web page in the inspect mode to check how much the page will e scroll.
			js.executeScript("window.scrollBy(0,500)");
	}
	public ConfirmationPage submitOrder() throws InterruptedException
	{
		Thread.sleep(2000);
		submit.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		 return confirmationpage;
	}


}
