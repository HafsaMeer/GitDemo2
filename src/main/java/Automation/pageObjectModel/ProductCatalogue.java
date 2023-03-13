package Automation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstracrComponents.Abstract_Components;

public class ProductCatalogue extends Abstract_Components {
	
	WebDriver driver;
	public  ProductCatalogue(WebDriver driver) { 
		
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List <WebElement> products; 
	@FindBy(css=".ng-animating")
	WebElement disappeartoastmsg; 
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	By productsList=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsList);//products ayen to unko return krwa do 
		return products;
	}
	
	public WebElement getProductsName(String productName)
	{
		WebElement prod=getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductsName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(disappeartoastmsg);
	}
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); We are going to add this line as method because this option is 
	//on header which is common for all pages so we keep it in the method to reuse it.
	

	
	
	}
