package com.automation.redbus.listeners;

import com.automation.redbus.utils.ExtentReportManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

 //public class TestListener implements ITestListener
//{
//
//    /**
//     * TestNG Listener to capture screenshots on test failure
//     * Automatically attached to tests via testng.xml
//     */
//        @Override
//        public void onTestStart(ITestResult result) {
//            System.out.println("\n▶️  Starting Test: " + result.getMethod().getMethodName());
//        }
//
//        @Override
//        public void onTestSuccess(ITestResult result) {
//            System.out.println("✅ Test Passed: " + result.getMethod().getMethodName());
//        }
//
//        @Override
//        public void onTestFailure(ITestResult result) {
//            System.out.println("❌ Test Failed: " + result.getMethod().getMethodName());
//
//            // Get WebDriver from test class
//            Object testClass = result.getInstance();
//            if (testClass instanceof BaseTest) {
//                WebDriver driver = ((BaseTest) testClass).driver;
//
//                if (driver != null) {
//                    // Capture screenshot
//                    String testName = result.getMethod().getMethodName();
//                    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
//
//                    if (screenshotPath != null) {
//                        System.out.println("📸 Screenshot captured for failed test");
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void onTestSkipped(ITestResult result) {
//            System.out.println("⏭️  Test Skipped: " + result.getMethod().getMethodName());
//        }
//
//        @Override
//        public void onStart(ITestContext context) {
//            System.out.println("\n" + "═".repeat(70));
//            System.out.println("🚀 Test Suite Started: " + context.getName());
//            System.out.println("═".repeat(70));
//        }
//
//        @Override
//        public void onFinish(ITestContext context) {
//            System.out.println("\n" + "═".repeat(70));
//            System.out.println("🏁 Test Suite Finished: " + context.getName());
//            System.out.println("Total Tests: " + context.getAllTestMethods().length);
//            System.out.println("Passed: " + context.getPassedTests().size());
//            System.out.println("Failed: " + context.getFailedTests().size());
//            System.out.println("Skipped: " + context.getSkippedTests().size());
//            System.out.println("═".repeat(70) + "\n");
//        }




/**
 * TestNG Listener for Extent Reports integration
 * Automatically creates test entries and logs results
 *
 * @author Your Name
 * @version 1.0
 * @since 2024
 */

public class ExtentTestListener implements ITestListener, ISuiteListener

{

    /**
     * Invoked before the test suite starts
     */
    @Override
    public void onStart(ISuite suite) {
        System.out.println("========================================");
        System.out.println("📊 Initializing Extent Reports");
        System.out.println("========================================");
        ExtentReportManager.initReports();
    }

    /**
     * Invoked after the test suite completes
     */
    @Override
    public void onFinish(ISuite suite) {
        System.out.println("========================================");
        System.out.println("📊 Generating Extent Report");
        System.out.println("========================================");
        ExtentReportManager.flushReports();
    }

    /**
     * Invoked when a test method starts
     */
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ExtentReportManager.createTest(testName, description);
        ExtentReportManager.logInfo("Test Started: " + testName);

        System.out.println("▶️  Starting Test: " + testName);
    }

    /**
     * Invoked when a test method succeeds
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        ExtentReportManager.logPass("✅ Test Passed: " + testName);
        ExtentReportManager.logInfo("Execution Time: " + getExecutionTime(result) + " seconds");

        System.out.println("✅ Test Passed: " + testName);
    }

    /**
     * Invoked when a test method fails
     */
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

        ExtentReportManager.logFail("❌ Test Failed: " + testName);

        if (throwable != null) {
            ExtentReportManager.logFail("Error: " + throwable.getMessage());
            ExtentReportManager.logFail("Stack Trace: " + getStackTrace(throwable));
        }

        ExtentReportManager.logInfo("Execution Time: " + getExecutionTime(result) + " seconds");

        System.out.println("❌ Test Failed: " + testName);
    }

    /**
     * Invoked when a test method is skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        ExtentReportManager.logSkip("⏭️  Test Skipped: " + testName);

        if (result.getThrowable() != null) {
            ExtentReportManager.logSkip("Reason: " + result.getThrowable().getMessage());
        }

        System.out.println("⏭️  Test Skipped: " + testName);
    }

    /**
     * Invoked when a test method fails but is within success percentage
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.logWarning("⚠️  Test Failed but within success percentage: " + testName);
    }

    /**
     * Calculate test execution time in seconds
     *
     * @param result ITestResult containing test execution details
     * @return Execution time in seconds
     */
    private String getExecutionTime(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        return String.format("%.2f", duration / 1000.0);
    }

    /**
     * Get formatted stack trace from throwable
     *
     * @param throwable Exception/Error
     * @return Formatted stack trace string
     */
    private String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
