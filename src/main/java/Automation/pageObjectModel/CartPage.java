package Automation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstracrComponents.Abstract_Components;

public class CartPage extends Abstract_Components
{
	WebDriver driver;
	@FindBy(css=".cartSection h3")
	List <WebElement> cartproducts;
	@FindBy(css=".totalRow button")
	 WebElement checkout;
    
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
 public Boolean verifyProductDisplay(String productName)
 {
	 Boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	 return match;
 }
 public CheckoutPage goToCheckout()
 {
	 checkout.click();
	 CheckoutPage checkoutpage=new CheckoutPage(driver);
	 return checkoutpage;
 }
		
}
