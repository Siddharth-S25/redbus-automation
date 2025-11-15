package com.automation.redbus.tests;

import com.automation.redbus.base.BaseTest;
import com.automation.redbus.pages.HomePage;
import com.automation.redbus.pages.SearchResultPage;
import com.automation.redbus.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
/**
 * Test class for RedBus booking functionality
 * Contains end-to-end test scenarios for bus search and booking flow
 *
 * @author Your Name
 * @version 1.0
 * @since 2024
 */
public class RedBusBookingTest extends BaseTest {

    /**
     * Test complete bus booking flow
     * Steps:
     * 1. Wait for home page to load
     * 2. Enter source and destination cities
     * 3. Select today's date
     * 4. Click search button
     * 5. Verify search results are displayed
     * 6. Validate URL contains search parameters
     * 7. Verify minimum bus count
     *
     * @throws InterruptedException if thread sleep is interrupted
     */

    private HomePage homePage;
    private SearchResultPage resultPage;

    @BeforeClass  // ✅ Initialize once
    public void initializePages() {
        homePage = new HomePage(driver);
        resultPage = new SearchResultPage(driver);
    }

    @Test(priority = 1, description = "Verify complete bus search flow")
    public void testBusSearchFlow() throws InterruptedException {
        logTestStart("Bus Search Flow Test");

        String fromCity = getConfigProperty("from.city");
        String toCity = getConfigProperty("to.city");

        System.out.println("🔍 Searching bus from " + fromCity + " to " + toCity);

        // Perform search
        homePage.waitForHomePageToLoad();
        homePage.searchBus(fromCity, toCity);

        // Verify results
        resultPage.verifyResultsLoaded();
        resultPage.waitForResultsToLoadCompletely(20);
        resultPage.verifyPageTitle();
        resultPage.verifySearchUrl(fromCity, toCity);

        boolean busesFound = resultPage.verifyBusResultsDisplayed();
        Assert.assertTrue(busesFound, "No buses found for the route");

        resultPage.verifyMinimumBusCount(1);

        logTestSuccess("Bus search flow completed successfully");
    }

    @Test(priority = 2, description = "Verify search results page components",
            dependsOnMethods = "testBusSearchFlow")  // ✅ Keep this!
    public void testSearchResultsPageComponents() throws InterruptedException {
        logTestStart("Search Results Page Components Test");

        // ✅ NO SEARCH HERE - Continue on same page!
        // resultPage is already on the results page from Test 1

        // Just verify components
        resultPage.waitForResultsToLoadCompletely(20);

        boolean filtersPresent = resultPage.waitForFilterSection(15);
        Assert.assertTrue(filtersPresent, "Filters section not found on results page");

        int busCount = resultPage.getBusCount();
        System.out.println("📊 Total buses available: " + busCount);
        Assert.assertTrue(busCount > 0, "Bus count should be greater than 0");

        logTestSuccess("Search results page components validated");
    }

    /**
     * Data Provider for multiple city combinations
     * Provides test data for data-driven testing
     */
    @DataProvider(name = "cityRoutes")
    public Object[][] getCityRoutes() {
        return new Object[][] {
                {"Pune", "Mumbai"},
                {"Bangalore", "Chennai"},
                {"Delhi", "Jaipur"}
        };
    }

    // Utility Methods

    /**
     * Get property value from configuration file
     *
     * @param key Property key
     * @return Property value
     */

    private String getConfigProperty(String key) {
        String value = ConfigReader.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Property '" + key + "' not found in config");
        }
        return value;
    }
    /**
     * Log test start with formatting
     *
     * @param testName Name of the test
     */
    private void logTestStart(String testName) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🧪 Starting Test: " + testName);
        System.out.println("=".repeat(60));
    }

    /**
     * Log test success with formatting
     *
     * @param message Success message
     */
    private void logTestSuccess(String message) {
        System.out.println("=".repeat(60));
        System.out.println("✅ " + message);
        System.out.println("=".repeat(60) + "\n");
    }
}