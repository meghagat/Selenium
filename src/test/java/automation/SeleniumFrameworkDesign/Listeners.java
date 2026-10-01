package automation.SeleniumFrameworkDesign;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners  implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> local = new ThreadLocal<ExtentTest>();// When running in
	//parallel , implement thread local so that a separate thread is created for each test

	@Override
	public void onTestStart(ITestResult result) {

	test=extent.createTest(result.getMethod().getMethodName());
	local.set(test);//parallel execution
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "Test case Passed");
//		local.get().log(Status.PASS, "Test case Passed"); for parallel execution
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
        test.fail(result.getThrowable());
  //      local.get().fail(result.getThrowable()); for parallel execution

        BaseTest baseTest =
                (BaseTest) result.getInstance();

      //  WebDriver driver = baseTest.driver;

        try {

            String screenshotPath =
                    baseTest.getScreenShot(
                            result.getMethod().getMethodName()
                    );

            test.addScreenCaptureFromPath(screenshotPath);
      //      local.get().addScreenCaptureFromPath(screenshotPath) for parallel execution

        }
        catch (IOException e) {

            e.printStackTrace();
        }
	}
	@Override
	public void onFinish(ITestContext context) {
		  
		  extent.flush();		  }

}
