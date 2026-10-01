package automation.SeleniumFrameworkDesign.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reusableComponents.AbstractComponent;

public class ProductsPage extends AbstractComponent {

	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By products = By.xpath("//div[@class='card-body']");
	By productItem = By.xpath("./h5/b");
	By addToCart = By.xpath(".//parent::h5/following-sibling::button[@class='btn w-10 rounded']");
	By productToast = By.cssSelector("div[aria-label='Product Added To Cart']");
	String url= "/dashboard";
	


	public WebElement matchingProducts(String productName)

	{
		waitForUrl(url);
		List<WebElement> page = driver.findElements(products);

		WebElement product = page.stream()
				.filter(row -> row.findElement(productItem).getText().contains(productName)).findFirst()
				.orElse(null);
		return product;
	}


	public void addToCart(String productName) {
		WebElement product = matchingProducts(productName);
		product.findElement(addToCart).click();

	}
	
	public CartPage clickOnCart()
	{
		waitForElementVisible(productToast);
		waitForElementInVisible(productToast);				  
		driver.findElement(By.cssSelector(
				  ".btn.btn-custom[routerlink='/dashboard/cart']")).click();
		return new CartPage(driver);
	}

}
