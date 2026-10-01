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
import automation.SeleniumFrameworkDesign.pageObjects.HeaderPage;
import automation.SeleniumFrameworkDesign.pageObjects.LoginPage;
import automation.SeleniumFrameworkDesign.pageObjects.OrderPage;
import automation.SeleniumFrameworkDesign.pageObjects.ProductsPage;

public class SeleniumTest2 extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test
	public void productTest() {
		// TODO Auto-generated method stub

		ProductsPage productsPage = loginPage.login("mgh@gmail.com", "Doctor@123");

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

	// Verify Product Name is present in Order History Page

	@Test(dependsOnMethods= {"productTest"})
	public void verifyProductPresentInOrderHistory() {

		ProductsPage productsPage = loginPage.login("mgh@gmail.com", "Doctor@123");

		HeaderPage headerPage = productsPage.clickOnOrders();
		
	boolean isProductPresent=	
			headerPage.verifyProduct(productName);
	
	Assert.assertTrue(isProductPresent);

	}

}

