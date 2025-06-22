package utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getReportInstance();
    private static Map<Long, ExtentTest> extentTestMap = new HashMap<>();
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

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
        getTest().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getTest();
        test.log(Status.FAIL, "❌ Test Failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getThrowable());

        WebDriver driver = BaseTest.driver; // Or from ThreadLocal if using parallel
        if (driver != null) {
            try {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotPath = "reports/screenshots/" + result.getMethod().getMethodName() + "_" + timeStamp + ".png";
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destFile = new File(screenshotPath);
                destFile.getParentFile().mkdirs();
                srcFile.renameTo(destFile);
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.warning("Screenshot capture failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            getTest().log(Status.SKIP, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
