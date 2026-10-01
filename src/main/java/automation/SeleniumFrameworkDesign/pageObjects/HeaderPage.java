package automation.SeleniumFrameworkDesign.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reusableComponents.AbstractComponent;

public class HeaderPage extends AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	By products = By.xpath("//tbody/tr/td[2]");

	public HeaderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public boolean verifyProduct(String productName) {
		


		List<WebElement> matchingProducts = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(products));
		

		boolean anyMatch = matchingProducts.stream()
				.anyMatch(product -> product.findElement(products).getText()
						.equalsIgnoreCase(productName));
		
		return anyMatch;
	}

}
