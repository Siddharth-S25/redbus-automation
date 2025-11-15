package com.automation.redbus.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager
{


    /**
     * Extent Report Manager for generating test execution reports
     * Handles report initialization, test logging, and report finalization
     *
     * @author Your Name
     * @version 1.0
     * @since 2024
     */


        private static ExtentReports extent;
        private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
        private static String reportPath;

        /**
         * Initialize Extent Reports
         * Creates a new report file with timestamp
         */
        public static void initReports() {
            if (extent == null) {
                // Create reports directory
                String reportsDir = "test-output/extent-reports/";
                new File(reportsDir).mkdirs();

                // Generate report filename with timestamp
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                reportPath = reportsDir + "TestReport_" + timestamp + ".html";

                // Initialize Spark Reporter
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

                // Configure report
                sparkReporter.config().setDocumentTitle("RedBus Automation Test Report");
                sparkReporter.config().setReportName("RedBus Test Execution Report");
                sparkReporter.config().setTheme(Theme.STANDARD);
                sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

                // Initialize Extent Reports
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);

                // Add system information
                extent.setSystemInfo("Application", "RedBus");
                extent.setSystemInfo("Environment", "QA");
                extent.setSystemInfo("User", System.getProperty("user.name"));
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                extent.setSystemInfo("Java Version", System.getProperty("java.version"));

                System.out.println("📊 Extent Report initialized: " + reportPath);
            }
        }

        /**
         * Create a new test in the report
         *
         * @param testName Name of the test
         * @param description Description of the test
         */
        public static void createTest(String testName, String description) {
            ExtentTest test = extent.createTest(testName, description);
            extentTest.set(test);
        }

        /**
         * Get the current test instance
         *
         * @return Current ExtentTest instance
         */
        public static ExtentTest getTest() {
            return extentTest.get();
        }

        /**
         * Log an info message to the report
         *
         * @param message Message to log
         */
        public static void logInfo(String message) {
            getTest().log(Status.INFO, message);
        }

        /**
         * Log a pass message to the report
         *
         * @param message Message to log
         */
        public static void logPass(String message) {
            getTest().log(Status.PASS, message);
        }

        /**
         * Log a fail message to the report
         *
         * @param message Message to log
         */
        public static void logFail(String message) {
            getTest().log(Status.FAIL, message);
        }

        /**
         * Log a warning message to the report
         *
         * @param message Message to log
         */
        public static void logWarning(String message) {
            getTest().log(Status.WARNING, message);
        }

        /**
         * Log a skip message to the report
         *
         * @param message Message to log
         */
        public static void logSkip(String message) {
            getTest().log(Status.SKIP, message);
        }

        /**
         * Attach screenshot to the report
         *
         * @param screenshotPath Path to the screenshot file
         */
        public static void attachScreenshot(String screenshotPath) {
            try {
                getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                System.out.println("⚠️  Failed to attach screenshot to report: " + e.getMessage());
            }
        }

        /**
         * Flush the report
         * Must be called at the end of test execution
         */
        public static void flushReports() {
            if (extent != null) {
                extent.flush();
                System.out.println("📊 Extent Report saved: " + reportPath);
            }
        }

        /**
         * Get the report file path
         *
         * @return Path to the generated report
         */
        public static String getReportPath() {
            return reportPath;
        }
}
