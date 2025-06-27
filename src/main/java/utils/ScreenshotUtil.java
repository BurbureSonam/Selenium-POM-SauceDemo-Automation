package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class ScreenshotUtil {

  
    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // ✅ Save to a top-level 'screenshots/' directory instead of 'test-output/screenshots'
        String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots";
        String screenshotPath = screenshotDir + File.separator + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            dest.getParentFile().mkdirs(); // Automatically create the 'screenshots' folder
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return dest.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
