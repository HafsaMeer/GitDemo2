package Automation.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstracrComponents.Abstract_Components;

public class LandingPage extends Abstract_Components {
	/*Page Object Model, also known as POM, is a design pattern in Selenium that creates an object repository for storing all web elements. It helps reduce code duplication and improves test case maintenance.
	 We use this model because we can't provide login credentials in every test case class so we keep it in ine class and call it everytime when we need it. Moreover, if the login credentials or locators are change we just have to change it in one class not in every test case.*/
	
	WebDriver driver;// we declare this local variable to catch the value from main driver
	public  LandingPage(WebDriver driver) { // This is constructor-A constructor in Java is a special method that is used to initialize objects. The constructor is called when an object of a class is created.
		//It can be used to set initial values for object attributes.constructor name must match the class name, and it cannot have a return type (like void).Also note that the constructor is called when the object is created.
		// This method executed even before the main method.
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userID=driver.findElement(By.id("userEmail"));// The class has no idea what is driver so we call the driver here using constructor from where it is.
   
	//Page Factory - Page Factory is a class provided by Selenium WebDriver to support Page Object Design patterns. In Page Factory, testers use @FindBy annotation. The initElements method is used to initialize web elements.
	// @FindBy: An annotation used in Page Factory to locate and declare web elements using different locators
	
	@FindBy(id="userEmail")
	WebElement userID; // this two lines are same as we have above line but how this find driver for this we use initElement method in constructor method.This is throughPageFactory.
	
	@FindBy(id="userPassword")
	WebElement password;
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement wronglogin;

	
	

	public ProductCatalogue loginApplication(String email, String pass) // these are called action methods.
	{
		// TODO Auto-generated method stub
		userID.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogue productcatlog=new ProductCatalogue(driver);
		return productcatlog;
		
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(wronglogin);
		return wronglogin.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	}
