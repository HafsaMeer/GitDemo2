package Automation.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstracrComponents.Abstract_Components;

public class ConfirmationPage extends Abstract_Components{
 
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(tagName ="h1")
	 WebElement ThankyouMessage;
	
	public String verifyTYMessage()
	{
		return ThankyouMessage.getText();
	}
}
