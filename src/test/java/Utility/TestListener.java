package Utility;

import TestComponents.BaseTest;
import org.testng.*;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;


public class TestListener implements ITestListener {

    public void onStart(ITestContext context) {
        ExtentReportManager.createInstance();
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.getExtent()
                .createTest(result.getMethod().getMethodName());
        ExtentReportManager.setTest(test);
        ExtentReportManager.getTest().assignAuthor("Arka Mondal");
    }

    public void onTestSuccess(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        ExtentReportManager.getTest().pass("Test Passed")
                .addScreenCaptureFromPath(screenshotPath);
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        ExtentReportManager.getTest().fail(result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
    }

    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
    }
}
