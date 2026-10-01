package automation.SeleniumFrameworkDesign.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage {
	WebDriver driver;
	WebDriverWait wait;
	
	By selectCountry = By.cssSelector("input[placeholder='Select Country']");
	By results = By.cssSelector(".ta-results button");
	By submit = By.cssSelector(".btnn.action__submit.ng-star-inserted");
	By orderToast = By.cssSelector("div[aria-label='Order Placed Successfully']");
	
	
	
	public CheckOutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	






	public void enterCountry(String countryName) {
		driver.findElement(selectCountry).sendKeys(countryName);

		List<WebElement> countries = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(results));

		WebElement match = countries.stream().filter(country -> country.getText().trim().equalsIgnoreCase("India"))
				.findFirst().orElseThrow(() -> new RuntimeException("India not found"));

		match.click();

		
	}
	
	public OrderPage clickSubmit()
	{
		driver.findElement(submit).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(orderToast));
		
		return  new OrderPage(driver);

	}

	
	
	

}
