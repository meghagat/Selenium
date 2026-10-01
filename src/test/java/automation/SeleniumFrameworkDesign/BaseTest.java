package automation.SeleniumFrameworkDesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automation.SeleniumFrameworkDesign.pageObjects.LoginPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver;
    public LoginPage loginPage;

	
	@BeforeMethod(alwaysRun=true)
	public  void initializeDriver() throws IOException 
	{
		//Read properties file
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//GlobalData.properties");
		prop.load(fis);
		
		//Read browser
		
		//Sending browser via maven command
		//Using JAVA ternary operator, if system level browser is sent then use that or else the one
		//given in globalData properties
		
		String browserName=	System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		
		
		//Decide which browser to launch
		
		if(browserName.contains("Chrome"))
		{
			
		    System.out.println("Entering Chrome block");

			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless"))
			{
		        System.out.println("Headless mode enabled");

			options.addArguments("--headless");	
			}
			

			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));// when running in headless , full screen the window

		    System.out.println("Driver after creation: " + this.driver);



		}
		if(browserName.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}

		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);


		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
	{
		//Read json to string
		
		String jsonContent = FileUtils.readFileToString(
			    new File(System.getProperty("user.dir") + "//src//test//java//Data//PurchaseOrder.json"),
			    StandardCharsets.UTF_8
			);	
		
		//Convert json to HashMap
		ObjectMapper mapper = new ObjectMapper();

		
		List<HashMap<String, String>> data =
			    mapper.readValue(
			        jsonContent,
			        new TypeReference<List<HashMap<String, String>>>() {}
			    );
		return data;
		}
	
	public String getScreenShot(String testCaseName) throws IOException 
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName+".png";
		
	}


	
@AfterMethod(alwaysRun=true)
	public void closeDriver() {
	driver.quit();
	}
	

}
