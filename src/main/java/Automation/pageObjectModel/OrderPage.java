package Automation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstracrComponents.Abstract_Components;

public class OrderPage extends Abstract_Components
{
	WebDriver driver;
	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> orderedProductName;
	@FindBy(css=".totalRow button")
	 WebElement checkout;
    
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
 public Boolean verifyOrderDisplay(String productName)
 {
	 Boolean match=orderedProductName.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	 return match;
 }

		
}
