package utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {

    private static final ExtentReports extent = ExtentReportManager.getReportInstance();
    private static final Map<Long, ExtentTest> extentTestMap = new HashMap<>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTestMap.put(Thread.currentThread().getId(), test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().log(Status.PASS, "✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getTest();
        test.log(Status.FAIL, "❌ Test Failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getThrowable());

       
        WebDriver driver = null;
        try {
            Object currentClass = result.getInstance();
            driver = ((BaseTest) currentClass).getDriver();
        } catch (Exception e) {
            test.warning("⚠️ Could not retrieve WebDriver instance: " + e.getMessage());
        }

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
            if (screenshotPath != null) {
                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    test.warning("⚠️ Failed to attach screenshot: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = getTest();
        test.log(Status.SKIP, "⚠️ Test Skipped: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }
}
