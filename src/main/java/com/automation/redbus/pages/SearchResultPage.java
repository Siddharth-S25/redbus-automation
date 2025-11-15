package com.automation.redbus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Model for RedBus Search Results Page
 * Handles verification and interaction with bus search results
 *
 * @author Your Name
 * @version 1.0
 * @since 2024
 */

public class SearchResultPage {

    // Constants
    private static final int DEFAULT_TIMEOUT_SECONDS = 20;
    private static final int EXTENDED_TIMEOUT_SECONDS = 30;

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait extendedWait;

    // Locators - More specific and robust
    private final By busListContainer = By.cssSelector("div[class*='bus-items'], div[class*='busListingContainer']");
    private final By busResultItems = By.cssSelector("div[class*='bus-item'], div[class*='travels']");
    private final By resultTitle = By.cssSelector("div[class*='travels'], h2[class*='busCount']");
    private final By filterSection = By.xpath("//div[contains(@class,'filter') or contains(@class,'Filter')]");
    private final By noBusesMessage = By.xpath("//*[contains(text(),'No buses found') or contains(text(),'No bus available')]");

    /**
     * Constructor
     */
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        this.extendedWait = new WebDriverWait(driver, Duration.ofSeconds(EXTENDED_TIMEOUT_SECONDS));
    }

    /**
     * Verify search results page loaded
     */
    public void verifyResultsLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultTitle));
            logSuccess("Search results page loaded successfully");

            if (isElementPresent(filterSection)) {
                logInfo("Filter section is visible");
            }
        } catch (TimeoutException e) {
            logError("Failed to load search results page");
            throw new AssertionError("Search results page did not load", e);
        }
    }

    /**
     * Verify bus results are displayed
     */
    public boolean verifyBusResultsDisplayed() {
        try {
            extendedWait.until(ExpectedConditions.visibilityOfElementLocated(busResultItems));
            logSuccess("Bus results are displayed");
            return true;
        } catch (TimeoutException e) {
            if (isElementPresent(noBusesMessage)) {
                logWarning("No buses found for the selected route");
                return false;
            }
            logError("Failed to verify bus results");
            throw new AssertionError("Bus results verification failed", e);
        }
    }

    /**
     * Get bus count
     */
    public int getBusCount() {
        try {
            List<WebElement> buses = driver.findElements(busResultItems);
            int count = buses.size();
            logInfo("Found " + count + " buses in search results");
            return count;
        } catch (Exception e) {
            logError("Failed to get bus count");
            return 0;
        }
    }

    /**
     * Verify minimum bus count
     */
    public void verifyMinimumBusCount(int minCount) {
        int actualCount = getBusCount();
        Assert.assertTrue(actualCount >= minCount,
                "Expected at least " + minCount + " buses, but found " + actualCount);
        logSuccess("Bus count validation passed. Found " + actualCount + " buses (minimum: " + minCount + ")");
    }

    /**
     * Verify search URL
     */
    public void verifySearchUrl(String fromCity, String toCity) {
        String currentUrl = driver.getCurrentUrl().toLowerCase();

        Assert.assertTrue(currentUrl.contains(fromCity.toLowerCase()),
                "URL does not contain source city: " + fromCity);
        Assert.assertTrue(currentUrl.contains(toCity.toLowerCase()),
                "URL does not contain destination city: " + toCity);

        logSuccess("URL validation passed. Contains both cities: " + fromCity + " → " + toCity);
    }

    /**
     * Verify page title
     */
    public void verifyPageTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Bus") || title.contains("redBus"),
                "Page title does not contain expected text. Actual: " + title);
        logSuccess("Page title validation passed: " + title);
    }

    /**
     * Check if filters are available - IMPROVED VERSION
     */
    public boolean areFiltersAvailable() {
        return waitForFilterSection(10);  // Reuse existing method
    }

    /**
     * Wait for filter section - IMPROVED VERSION
     */
    public boolean waitForFilterSection(int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

            customWait.until(ExpectedConditions.visibilityOfElementLocated(filterSection));
            logInfo("Filter section is visible");
            return true;

        } catch (TimeoutException e) {
            logWarning("Filter section NOT visible within " + timeoutSeconds + " seconds");
            return false;  // Don't throw, return false
        }
    }

    /**
     * Wait for results to load completely - FIXED VERSION
     */
    public void waitForResultsToLoadCompletely(int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

            // Wait for bus container
            customWait.until(ExpectedConditions.visibilityOfElementLocated(busListContainer));

            // Wait for at least one bus
            customWait.until(ExpectedConditions.visibilityOfElementLocated(busResultItems));

            // Check filters (optional)
            if (isElementPresent(filterSection)) {
                logInfo("Filter section loaded successfully");
            }

            logSuccess("Search results page fully loaded");

        } catch (TimeoutException e) {
            logWarning("Search results did not fully load within " + timeoutSeconds + " seconds");
        } catch (Exception e) {
            logError("Error while waiting for search results: " + e.getMessage());
        }
    }

    // Utility Methods

    private boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    private void logInfo(String message) {
        System.out.println("ℹ️  " + message);
    }

    private void logSuccess(String message) {
        System.out.println("✅ " + message);
    }

    private void logWarning(String message) {
        System.out.println("⚠️  " + message);
    }

    private void logError(String message) {
        System.out.println("❌ " + message);
    }
}