package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

  private static final String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            spark = new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "SauceDemo");
            extent.setSystemInfo("Tester", "Sonam Burbure");
        }
        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println("📄 Extent Report generated at: " + reportPath);
        }
    }

    public static String getReportPath() {
        return reportPath;
    }
}
