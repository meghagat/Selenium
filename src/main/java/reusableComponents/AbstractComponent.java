package reusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.SeleniumFrameworkDesign.pageObjects.HeaderPage;

public class AbstractComponent {
	
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {

        this.driver = driver;
    }
    
    By orderHeader = By.cssSelector(".btn.btn-custom[routerlink='/dashboard/myorders']");
    


	public void waitForElementVisible(By locator)
    {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
    
    public void waitForUrl(String url)
    {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.urlContains(url));

    }
    public void waitForElementInVisible(By locator)
    {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }
    
    public HeaderPage clickOnOrders() 
    {
    	waitForUrl("/dashboard");
    	driver.findElement(orderHeader).click();
    	
    	return new HeaderPage(driver);
    }



}
