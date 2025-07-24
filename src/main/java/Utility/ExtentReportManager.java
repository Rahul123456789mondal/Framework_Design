package Utility;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.*;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports createInstance() {
        // Define the report path
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        // Create SparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(reportPath));
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Execution Report");
        sparkReporter.config().setReportName("Functional Test Results");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimelineEnabled(true);

        // Add filters
        sparkReporter.filter().statusFilter().as(new Status[] { Status.FAIL, Status.PASS, Status.SKIP }); // You can modify this

        // Create ExtentReports object and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System info
        extent.setSystemInfo("OS", "Windows 11");
        extent.setSystemInfo("Device", "Windows Desktop");
        extent.setSystemInfo("Browser", "Chrome / Firefox"); // You can override this later
        extent.setSystemInfo("Tester", "Arka Mondal");
        extent.setSystemInfo("Framework", "Selenium TestNG");

        return extent;
    }

    // Getters and Setters for ThreadLocal ExtentTest
    public static ExtentReports getExtent() {
        return extent;
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
