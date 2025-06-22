package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extent;
   
    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String reportPath = "reports/extent-report.html";
            File reportsDir = new File("reports/screenshots");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            // Create SparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("SauceDemo Automation Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            // Create ExtentReports and attach reporter
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Set environment/system info
            extent.setSystemInfo("Tester", "Sonam Burbure");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
}
