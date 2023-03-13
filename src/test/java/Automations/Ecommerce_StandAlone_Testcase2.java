package Automations;
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

import Automation.pageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Ecommerce_StandAlone_Testcase2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName="ADIDAS ORIGINAL";// hum yahan globally prduct name add kren gay ta k cart me hm check kr sken k wahin product aai h cart me jo hm ny select ki thi.
		
		WebDriverManager.chromedriver().setup();//WebDriverManager is an open-source Java library that automatically performs the four steps
		//(find, download, setup, and maintenance) mentioned above for the drivers required for Selenium tests.We don't need to give the path of chrome or firefox drivers
		//downloaded in the system to the selenium.WebDriverManager offers cross browser testing without the hassle of installing and maintaining different browser driver binaries.
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("learningID2022@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("H5T7qj@2NVPbtD");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));//login hone k bad kuch time to lgay ga products load hone me
		List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		//using stream instead of loop to checkout that particular product which we are finding in the list.
		WebElement prod=products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();//hm us product tk pohnchgai hn ab hmein us ka add to cart wala button click krna h to hm parent to child wali css use ki h 
        // Explicit wait to get the toast message
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//invisible ho jaye ga toast message kuch time bas to us k liye hm wait lgaein gay
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));hmein yahan zra ziada time lga h to hm driver sy start krte hn 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// try to write locator in console like this $("[routerlink*='cart']")
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();//ab hm cart k button py click kren gay
		List<WebElement> cartprod=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartprod.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".btnn")).click();
		String confirmedMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
