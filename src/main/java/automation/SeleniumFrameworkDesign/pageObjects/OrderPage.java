package automation.SeleniumFrameworkDesign.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
	WebDriver driver;
	WebDriverWait wait;
	
By success = By.cssSelector(".hero-primary");
	
	
	
	
	public OrderPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	

	public String success() {
		
		String successMessage = driver.findElement(success).getText();
		return successMessage;
		
	}


}
