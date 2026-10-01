package automation.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Driver will wait for 5 seconds until the element shows. Default is 0 seconds

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("mgh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Doctor@123");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.urlContains("/dashboard"));

		List<WebElement> page = driver.findElements(By.xpath("//div[@class='card-body']"));

		// System.out.println(driver.findElement(By.xpath("//div[@class='card-body']/h5/b")).getText());

		WebElement product = page.stream().filter(row -> row.findElement(By.xpath("./h5/b")).getText().contains("ZARA"))
				.findFirst().orElse(null);

		product.findElement(By.xpath(".//parent::h5/following-sibling::button[@class='btn w-10 rounded']")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[aria-label='Product Added To Cart']")));
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div[aria-label='Product Added To Cart']")));

		driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();

		List<WebElement> cartSection = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='cartSection']")));

		boolean isTrue = cartSection.stream()
				.anyMatch(row -> row.findElement(By.tagName("h3")).getText().contains("ZARA"));

		Assert.assertTrue(isTrue);

		WebElement checkoutButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", checkoutButton);

		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");

		List<WebElement> countries = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results button")));

		WebElement match = countries.stream().filter(country -> country.getText().trim().equalsIgnoreCase("India"))
				.findFirst().orElseThrow(() -> new RuntimeException("India not found"));

		match.click();

		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[aria-label='Order Placed Successfully']")));

		String orderID = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
		// driver.quit();

	}

}
