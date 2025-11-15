package com.automation.redbus.base;

import com.automation.redbus.utils.ConfigReader;
import com.automation.redbus.utils.DriverFactory;
import com.automation.redbus.utils.ExtentReportManager;
import com.automation.redbus.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest
{

    /**
     * Base test class that all test classes should extend
     * Handles driver initialization and cleanup
     */
        // Changed to public for TestListener access (for screenshots)
      /*  public WebDriver driver;

        /**
         * Set up WebDriver before all tests in the class
         * @param browser Browser name from testng.xml (defaults to chrome)
         */
     /*   @Parameters("browser")
        @BeforeClass(alwaysRun = true)
        public void setUp(@Optional("chrome") String browser) {
            System.out.println("\n═══════════════════════════════════════════════════");
            System.out.println("🚀 Setting Up Test Environment");
            System.out.println("═══════════════════════════════════════════════════");

            // Initialize driver using DriverFactory
            driver = DriverFactory.initDriver(browser);
            System.out.println("✅ Browser initialized: " + browser);

            // Navigate to application URL from config
            String url = ConfigReader.getProperty("base.url");
            driver.get(url);
            System.out.println("✅ Navigated to: " + url);
            System.out.println("📄 Page Title: " + driver.getTitle());
        }

        /**
         * Clean up after all tests in the class
         */
     /*   @AfterClass(alwaysRun = true)
        public void tearDown() {
            System.out.println("\n═══════════════════════════════════════════════════");
            System.out.println("🧹 Cleaning Up Test Environment");
            System.out.println("═══════════════════════════════════════════════════");

            if (driver != null) {
                DriverFactory.quitDriver();
                System.out.println("✅ Browser closed successfully\n");
            }
        }

// Add this method to BaseTest class

    /**
     * Captures screenshot on test failure
     * Executed after each test method
     */
  /*  @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("❌ Test Failed: " + result.getName());
            System.out.println("📸 Capturing screenshot for failed test...");

            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());

            if (screenshotPath != null) {
                System.out.println("✅ Screenshot saved: " + screenshotPath);

                // Attach to Extent Report (if using ExtentReports)
                try {
                    extentTest.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    System.err.println("⚠️ Could not attach screenshot to report");
                }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("✅ Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("⏭️ Test Skipped: " + result.getName());
        }
    }  */
//-----------------------------------------------------------------------------------
    /**
     * Base test class that all test classes should extend
     * Handles driver initialization, cleanup, and screenshot capture
     */

        // Public for TestListener access (for screenshots)
        public WebDriver driver;

        /**
         * Set up WebDriver before all tests in the class
         * @param browser Browser name from testng.xml (defaults to chrome)
         */
        @Parameters("browser")
        @BeforeClass(alwaysRun = true)
        public void setUp(@Optional("chrome") String browser) {
            System.out.println("\n═══════════════════════════════════════════════════");
            System.out.println("🚀 Setting Up Test Environment");
            System.out.println("═══════════════════════════════════════════════════");

            // Initialize driver using DriverFactory
            driver = DriverFactory.initDriver(browser);
            System.out.println("✅ Browser initialized: " + browser);

            // Navigate to application URL from config
            String url = ConfigReader.getProperty("base.url");
            driver.get(url);
            System.out.println("✅ Navigated to: " + url);
            System.out.println("📄 Page Title: " + driver.getTitle());
        }

        /**
         * Captures screenshot on test failure
         * Executed after each test method
         */
        @AfterMethod
        public void captureScreenshotOnFailure(ITestResult result) {
            if (result.getStatus() == ITestResult.FAILURE) {
                System.out.println("❌ Test Failed: " + result.getName());
                System.out.println("📸 Capturing screenshot...");

                String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());

                if (screenshotPath != null) {
                    System.out.println("✅ Screenshot saved: " + screenshotPath);

                    // Attach to Extent Report (if initialized)
                    try {
                        if (ExtentReportManager.getTest() != null) {
                            ExtentReportManager.attachScreenshot(screenshotPath);
                        }
                    } catch (Exception e) {
                        System.err.println("⚠️  Could not attach screenshot to report: " + e.getMessage());
                    }
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("✅ Test Passed: " + result.getName());
            } else if (result.getStatus() == ITestResult.SKIP) {
                System.out.println("⏭️  Test Skipped: " + result.getName());
            }
        }

        /**
         * Clean up after all tests in the class
         */
        @AfterClass(alwaysRun = true)
        public void tearDown() {
            System.out.println("\n═══════════════════════════════════════════════════");
            System.out.println("🧹 Cleaning Up Test Environment");
            System.out.println("═══════════════════════════════════════════════════");

            if (driver != null) {
                DriverFactory.quitDriver();
                System.out.println("✅ Browser closed successfully\n");
            }
        }
    }



