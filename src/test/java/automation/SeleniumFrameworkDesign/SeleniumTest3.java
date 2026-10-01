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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.SeleniumFrameworkDesign.pageObjects.CartPage;
import automation.SeleniumFrameworkDesign.pageObjects.CheckOutPage;
import automation.SeleniumFrameworkDesign.pageObjects.HeaderPage;
import automation.SeleniumFrameworkDesign.pageObjects.LoginPage;
import automation.SeleniumFrameworkDesign.pageObjects.OrderPage;
import automation.SeleniumFrameworkDesign.pageObjects.ProductsPage;

public class SeleniumTest3 extends BaseTest {
	//String productName = "ZARA COAT 3";

	@Test(dataProvider="getData")
	public void productTest(String username,String password,String productName) {
		// TODO Auto-generated method stub

		ProductsPage productsPage = loginPage.login(username,password);

		productsPage.addToCart(productName);

		CartPage cartPage = productsPage.clickOnCart();

		boolean isPresent = cartPage.anyMatch(productName);

		Assert.assertTrue(isPresent);

		CheckOutPage checkOutPage = cartPage.clickCheckOut();

		checkOutPage.enterCountry("ind");

		OrderPage orderPage = checkOutPage.clickSubmit();

		String message = orderPage.success();

		Assert.assertTrue(message.contains("THANKYOU"));

	}

	// DataProvider - Running with multiple data sets
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"mgh@gmail.com","Doctor@123","ZARA COAT 3"}
		,{"selen@gmail.com","Selenium@123","ADIDAS ORIGINAL"}};
		
	}


}
