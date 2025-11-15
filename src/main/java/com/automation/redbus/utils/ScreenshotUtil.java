package com.automation.redbus.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots
 * Used for test failure reporting and debugging
 */

/* public class ScreenshotUtil
{
    private static final String SCREENSHOT_FOLDER = "test-output/screenshots/";

    /**
     * Capture screenshot and save to file
     *
     * @param driver WebDriver instance
     * @param testName Name of the test (used in filename)
     * @return Path to saved screenshot file
     */
    /* public static String captureScreenshot(WebDriver driver, String testName) {
        // Create screenshots folder if it doesn't exist
        File folder = new File(SCREENSHOT_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Generate filename with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_FOLDER + fileName;

        try {
            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);

            // Copy to destination
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("📸 Screenshot saved: " + filePath);
            return filePath;

        } catch (IOException e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture screenshot with default naming
     *
     * @param driver WebDriver instance
     * @return Path to saved screenshot file
     */
    /*public static String captureScreenshot(WebDriver driver) {
        return captureScreenshot(driver, "screenshot");
    }  */

//--------------------------------------------------------

/**
 * Utility class for capturing screenshots
 * Used for test failure debugging and reporting
 */
public class ScreenshotUtil {

    /**
     * Captures screenshot and saves to specified location
     *
     * @param driver WebDriver instance
     * @param testName Name of the test case
     * @return Screenshot file path
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        // Create screenshots directory if not exists
        File screenshotDir = new File("test-output/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        // Generate filename with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = "test-output/screenshots/" + fileName;

        try {
            // Capture screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);

            // Copy to destination
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("📸 Screenshot captured: " + filePath);
            return filePath;

        } catch (IOException e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Captures screenshot with custom message
     */
    public static String captureScreenshot(WebDriver driver, String testName, String scenario) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + scenario + "_" + timestamp + ".png";
        String filePath = "test-output/screenshots/" + fileName;

        File screenshotDir = new File("test-output/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("📸 Screenshot captured: " + filePath);
            return filePath;

        } catch (IOException e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
