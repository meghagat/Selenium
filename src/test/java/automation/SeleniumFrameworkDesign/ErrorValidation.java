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
import org.testng.annotations.Test;

import automation.SeleniumFrameworkDesign.pageObjects.CartPage;
import automation.SeleniumFrameworkDesign.pageObjects.CheckOutPage;
import automation.SeleniumFrameworkDesign.pageObjects.LoginPage;
import automation.SeleniumFrameworkDesign.pageObjects.OrderPage;
import automation.SeleniumFrameworkDesign.pageObjects.ProductsPage;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"Error"},retryAnalyzer=Retry.class)
	public void productTest() {
		// TODO Auto-generated method stub

		ProductsPage productsPage = loginPage.login("email@gmail.com", "gm@123");

		String errorMessage = loginPage.errorMessage();
		
		Assert.assertEquals(errorMessage, "Incorrect email or password.");

	}

}

