package AbstracrComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.pageObjectModel.CartPage;
import Automation.pageObjectModel.OrderPage;

public class Abstract_Components {
	WebDriver driver;
	//Abstract Components class is th

	public Abstract_Components(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css="[routerlink*='cart']")
	WebElement viewCart;
	@FindBy (css="[routerlink*='myorders']")
	WebElement ordersHeader;

	public void waitForElementToAppear(By findBy)
	{
		
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
		
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(WebElement findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	public CartPage goToCartPage()
	{
		viewCart.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	public OrderPage goToOrdersPage()
	{
		ordersHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
		
	}
}
