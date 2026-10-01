package automation.SeleniumFrameworkDesign.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	By cart = By.xpath("//div[@class='cartSection']");
	By itemDescription = By.tagName("h3");
	By checkOutButton = By.cssSelector(".totalRow button");
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	
	
	public boolean anyMatch(String productName) 
	{
		List<WebElement> cartSection = wait
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cart));
		
		boolean isTrue = cartSection.stream()
				.anyMatch(row -> row.findElement(itemDescription).getText().contains(productName));
		return isTrue;
		
	}
	
	public CheckOutPage clickCheckOut() {
		
		WebElement checkoutButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(checkOutButton));
		
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", checkoutButton);


	    wait.until(ExpectedConditions.elementToBeClickable(checkOutButton));

	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", checkoutButton);


		return new CheckOutPage(driver);
		
	}
	


}
