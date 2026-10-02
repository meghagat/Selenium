package automation.SeleniumFrameworkDesign.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reusableComponents.AbstractComponent;

public class LoginPage  extends AbstractComponent{
	
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver)

	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	By toastMessage = By.cssSelector("[aria-label='Incorrect email or password.']");
	
	public ProductsPage login(String userEmail,String userPassword)
	{
		this.userEmail.sendKeys(userEmail);
		this.userPassword.sendKeys(userPassword);
		submit.click();

		return new ProductsPage(driver);
	}
	
	public String errorMessage() 
	
	{		
		

		waitForElementVisible(toastMessage);
		
		String errorMes= driver.findElement(toastMessage).getText();
		System.out.print(errorMes);
		
		
		return errorMes;
		
	}


}
