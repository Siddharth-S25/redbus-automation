package com.automation.redbus.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage
{
    /**
     * Page Object Model for RedBus Home Page
     * Handles bus search functionality including city selection and date picking
     */

//private WebDriver driver;
//    private WebDriverWait wait;
//
//    // Container divs (visible elements to click) - Using more specific locators
//    private By fromContainer = By.xpath("(//div[contains(@class,'srcDestWrapper') and @role='button'])[1]");
//    private By toContainer = By.xpath("(//div[contains(@class,'srcDestWrapper') and @role='button'])[2]");
//
//    // Search suggestions
//    private By searchSuggestionWrapper = By.xpath("//div[contains(@class, 'searchSuggestionWrapper')]");
//    private By searchCategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
//    private By locationNameLocator = By.xpath(".//div[contains(@class,'listHeader')]");
//
//    // Date and search
//    private By datePicker = By.xpath("//div[@class='dateWrapper___fc9eaf']");
//    private By todayButton = By.xpath("//button[text()='Today']");
//    private By searchButton = By.xpath("//button[contains(@class,'primaryButton')]");
//
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    }
//
//    /**
//     * Wait for home page to load completely
//     */
//    public void waitForHomePageToLoad() {
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(fromContainer));
//            System.out.println("✅ Home page loaded successfully.");
//        } catch (TimeoutException e) {
//            System.out.println("❌ Home page did not load properly. 'From' input not found.");
//            throw new RuntimeException("❌ Home page did not load properly.", e);
//        }
//    }
//
//    /**
//     * IMPROVED: Enter city using the working pattern from RedBusAutomationAssignment
//     */
//    private void enterCity(By containerLocator, String cityName, String fieldType) {
//        try {
//            System.out.println("\n▶️ Starting enter" + fieldType + "City for: " + cityName);
//
//            // Step 1: Click the container and wait for suggestions wrapper
//            WebElement container = wait.until(ExpectedConditions.elementToBeClickable(containerLocator));
//            container.click();
//            System.out.println("✅ Clicked " + fieldType + " container");
//
//            // Step 2: Wait for search suggestion wrapper to appear
//            wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionWrapper));
//            System.out.println("✅ Search suggestions wrapper visible");
//
//            // Step 3: Get the active element (the input field that's focused)
//            WebElement searchTextBox = driver.switchTo().activeElement();
//            Thread.sleep(1000); // Allow time for field to be ready
//
//            // Step 4: Type the city name
//            searchTextBox.sendKeys(cityName);
//            System.out.println("✅ Typed '" + cityName + "'");
//
//            // Step 5: Wait for search results to populate
//            List<WebElement> searchList = wait.until(
//                    ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator, 0));
//            System.out.println("✅ Found " + searchList.size() + " search categories");
//
//            // Step 6: Get the first search category (usually "CITIES")
//            WebElement locationSearchResult = searchList.get(0);
//
//            // Step 7: Find all location names in this category
//            List<WebElement> locationList = locationSearchResult.findElements(locationNameLocator);
//            System.out.println("✅ Found " + locationList.size() + " locations");
//
//            // Step 8: Click on the matching location
//            boolean found = false;
//            for (WebElement location : locationList) {
//                String locationName = location.getText().trim();
//                // Check for exact match or if location starts with the city name
//                if (locationName.equals(cityName) || locationName.startsWith(cityName + ",")) {
//                    location.click();
//                    System.out.println("✅ ✅ Successfully selected: " + cityName + " (matched: " + locationName + ")");
//                    found = true;
//                    break;
//                }
//            }
//
//            if (!found) {
//                // Fallback: Click first suggestion
//                System.out.println("⚠️ Exact match not found, clicking first suggestion");
//                locationList.get(0).click();
//            }
//
//            // Wait for selection to complete
//            Thread.sleep(500);
//
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException("Thread interrupted", e);
//        } catch (Exception e) {
//            System.out.println("❌ Failed to enter " + fieldType + " City: " + cityName);
//            e.printStackTrace();
//            throw new RuntimeException("Failed to enter " + fieldType + " City", e);
//        }
//    }
//
//    /**
//     * Enter source city in From field
//     */
//    public void enterFromCity(String fromCity) {
//        enterCity(fromContainer, fromCity, "From");
//    }
//
//    /**
//     * Enter destination city in To field
//     */
//    public void enterToCity(String toCity) {
//        enterCity(toContainer, toCity, "To");
//    }
//
//    /**
//     * Select today's date from calendar
//     * Uses flexible approach - tries to open calendar if needed
//     */
//    public void selectTodayDate() {
//        try {
//            System.out.println("\n▶️ Starting date selection");
//
//            // Try to check if calendar is already visible by looking for Today button
//            List<WebElement> todayButtons = driver.findElements(todayButton);
//
//            // If Today button is not visible, click date picker to open calendar
//            if (todayButtons.isEmpty() || !todayButtons.get(0).isDisplayed()) {
//                try {
//                    System.out.println("⚠️ Calendar not visible, attempting to open it");
//                    WebElement datepick = wait.until(ExpectedConditions.elementToBeClickable(datePicker));
//                    datepick.click();
//                    System.out.println("✅ Clicked date picker");
//                    Thread.sleep(500); // Wait for calendar to open
//                } catch (Exception e) {
//                    System.out.println("ℹ️ Date picker not needed or already open");
//                }
//            }
//
//            // Now click the Today button
//            WebElement todayBtn = wait.until(ExpectedConditions.elementToBeClickable(todayButton));
//
//            // Try regular click first
//            try {
//                todayBtn.click();
//                System.out.println("✅ Selected Today's date");
//            } catch (Exception e) {
//                // Fallback to JavaScript click if regular click fails
//                System.out.println("⚠️ Regular click failed, trying JavaScript click");
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                js.executeScript("arguments[0].click();", todayBtn);
//                System.out.println("✅ Selected Today's date via JavaScript");
//            }
//
//            Thread.sleep(500); // Allow calendar to close
//
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException("Thread interrupted", e);
//        } catch (Exception e) {
//            System.out.println("❌ Failed to select today's date");
//            e.printStackTrace();
//            throw new RuntimeException("Failed to select today's date", e);
//        }
//    }
//
//    /**
//     * Click the Search Buses button
//     */
//    public void clickSearch() {
//        try {
//            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
//
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", search);
//
//            Thread.sleep(300);
//
//            js.executeScript("arguments[0].click();", search);
//            System.out.println("✅ Clicked Search Button");
//            Thread.sleep(2000);
//
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException("Thread interrupted", e);
//        } catch (Exception e) {
//            System.out.println("❌ Failed to click Search Button");
//            e.printStackTrace();
//            throw new RuntimeException("Failed to click Search Button", e);
//        }
//    }
//
//    /**
//     * Complete bus search flow - uses today's date
//     */
//    public void searchBus(String fromCity, String toCity) {
//        try {
//            waitForHomePageToLoad();
//            enterFromCity(fromCity);
//            enterToCity(toCity);
//            selectTodayDate();
//            clickSearch();
//            System.out.println("✅ ✅ ✅ Bus search completed successfully!");
//        } catch (Exception e) {
//            System.out.println("❌ Bus search failed: " + e.getMessage());
//            throw new RuntimeException("Bus search failed", e);
//        }
//    }
        // Constants for timeouts and waits
        private static final int DEFAULT_TIMEOUT_SECONDS = 30;
        private static final int SHORT_WAIT_MS = 500;
        private static final int MEDIUM_WAIT_MS = 1000;
        private static final int SCROLL_WAIT_MS = 300;
        private static final int SEARCH_COMPLETE_WAIT_MS = 2000;

        private final WebDriver driver;
        private final WebDriverWait wait;

        // Locators - Using descriptive XPath with comments
        private final By fromContainer = By.xpath("(//div[contains(@class,'srcDestWrapper') and @role='button'])[1]");
        private final By toContainer = By.xpath("(//div[contains(@class,'srcDestWrapper') and @role='button'])[2]");
        private final By searchSuggestionWrapper = By.xpath("//div[contains(@class, 'searchSuggestionWrapper')]");
        private final By searchCategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
        private final By locationNameLocator = By.xpath(".//div[contains(@class,'listHeader')]");
        private final By datePicker = By.xpath("//div[@class='dateWrapper___fc9eaf']");
        private final By todayButton = By.xpath("//button[text()='Today']");
        private final By searchButton = By.xpath("//button[contains(@class,'primaryButton')]");

        /**
         * Constructor to initialize HomePage with WebDriver
         * @param driver WebDriver instance
         */
        public HomePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        }

        /**
         * Wait for home page to load completely by checking visibility of 'From' container
         * @throws RuntimeException if home page fails to load within timeout
         */
        public void waitForHomePageToLoad() {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(fromContainer));
                logInfo("Home page loaded successfully");
            } catch (TimeoutException e) {
                logError("Home page did not load properly. 'From' input not found");
                throw new RuntimeException("Home page failed to load", e);
            }
        }

        /**
         * Generic method to enter city in From/To fields using dynamic dropdown
         * Handles AJAX-loaded suggestions and implements fallback mechanism
         *
         * @param containerLocator Locator for the input container
         * @param cityName City name to search and select
         * @param fieldType Type of field ("From" or "To") for logging purposes
         * @throws RuntimeException if city selection fails
         */
        private void enterCity(By containerLocator, String cityName, String fieldType) {
            try {
                logInfo("Starting to enter " + fieldType + " City: " + cityName);

                // Click container to activate input field
                WebElement container = wait.until(ExpectedConditions.elementToBeClickable(containerLocator));
                container.click();
                logInfo("Clicked " + fieldType + " container");

                // Wait for search suggestions to appear
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionWrapper));
                logInfo("Search suggestions wrapper visible");

                // Get active input field and type city name
                WebElement searchTextBox = driver.switchTo().activeElement();
                waitFor(MEDIUM_WAIT_MS);
                searchTextBox.sendKeys(cityName);
                logInfo("Typed '" + cityName + "'");

                // Wait for search results to populate
                List<WebElement> searchCategories = wait.until(
                        ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator, 0));
                logInfo("Found " + searchCategories.size() + " search categories");

                // Get first search category (usually "CITIES")
                WebElement firstCategory = searchCategories.get(0);
                List<WebElement> locations = firstCategory.findElements(locationNameLocator);
                logInfo("Found " + locations.size() + " locations");

                // Select matching location
                selectMatchingLocation(locations, cityName);
                waitFor(SHORT_WAIT_MS);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted during city selection", e);
            } catch (Exception e) {
                logError("Failed to enter " + fieldType + " City: " + cityName);
                throw new RuntimeException("City selection failed for " + fieldType, e);
            }
        }

    /**
     * Selects matching location from dropdown list
     * Supports exact match and partial match (city name with state)
     *
     * @param locations List of location WebElements
     * @param cityName City name to match
     */
    private void selectMatchingLocation(List<WebElement> locations, String cityName) {
        for (WebElement location : locations) {
            String locationText = location.getText().trim();

            // Debug: Print what we're matching against
            System.out.println("🔍 Checking: '" + locationText + "' against '" + cityName + "'");

            // Match exact city name or city with state (e.g., "Pune, Maharashtra")
            // Also match if location contains city name (e.g., "Pune (All Locations)")
            if (locationText.equals(cityName) ||
                    locationText.startsWith(cityName + ",") ||
                    locationText.startsWith(cityName + " ")) {

                location.click();
                logSuccess("Successfully selected: " + cityName + " (matched: " + locationText + ")");
                return;
            }
        }

        // Fallback: Select first suggestion if exact match not found
        logWarning("Exact match not found for '" + cityName + "', selecting first suggestion");
        locations.get(0).click();
    }


        public void enterFromCity(String fromCity) {
            enterCity(fromContainer, fromCity, "From");
        }

        /**
         * Enter destination city in To field
         * @param toCity Destination city name
         */
        public void enterToCity(String toCity) {
            enterCity(toContainer, toCity, "To");
        }

        /**
         * Select today's date from calendar
         * Implements flexible approach with fallback mechanisms
         * - Checks if calendar is already visible
         * - Opens calendar if needed
         * - Uses JavaScript click as fallback
         *
         * @throws RuntimeException if date selection fails
         */
        public void selectTodayDate() {
            try {
                logInfo("Starting date selection");

                // Check if Today button is already visible
                openCalendarIfNeeded();

                // Click Today button with fallback mechanism
                WebElement todayBtn = wait.until(ExpectedConditions.elementToBeClickable(todayButton));
                clickElementWithFallback(todayBtn, "Today button");

                waitFor(SHORT_WAIT_MS);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted during date selection", e);
            } catch (Exception e) {
                logError("Failed to select today's date");
                throw new RuntimeException("Date selection failed", e);
            }
        }

        /**
         * Opens calendar if Today button is not visible
         */
        private void openCalendarIfNeeded() throws InterruptedException {
            List<WebElement> todayButtons = driver.findElements(todayButton);

            if (todayButtons.isEmpty() || !todayButtons.get(0).isDisplayed()) {
                try {
                    logWarning("Calendar not visible, attempting to open");
                    WebElement datepick = wait.until(ExpectedConditions.elementToBeClickable(datePicker));
                    datepick.click();
                    logInfo("Clicked date picker");
                    waitFor(SHORT_WAIT_MS);
                } catch (Exception e) {
                    logInfo("Date picker not needed or already open");
                }
            }
        }

        /**
         * Clicks element with JavaScript fallback mechanism
         * @param element WebElement to click
         * @param elementName Name of element for logging
         */
        private void clickElementWithFallback(WebElement element, String elementName) {
            try {
                element.click();
                logSuccess("Clicked " + elementName);
            } catch (Exception e) {
                logWarning("Regular click failed on " + elementName + ", trying JavaScript click");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                logSuccess("Clicked " + elementName + " via JavaScript");
            }
        }

        /**
         * Click the Search Buses button
         * Scrolls element into view before clicking using JavaScript
         *
         * @throws RuntimeException if search button click fails
         */
        public void clickSearch() {
            try {
                WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchButton));

                // Scroll element into view
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", search);
                waitFor(SCROLL_WAIT_MS);

                // Click using JavaScript for reliability
                js.executeScript("arguments[0].click();", search);
                logSuccess("Clicked Search Button");
                waitFor(SEARCH_COMPLETE_WAIT_MS);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted during search", e);
            } catch (Exception e) {
                logError("Failed to click Search Button");
                throw new RuntimeException("Search button click failed", e);
            }
        }

        /**
         * Complete bus search flow - orchestrates all search operations
         * This is the main method that combines all individual actions
         *
         * @param fromCity Source city name
         * @param toCity Destination city name
         * @throws RuntimeException if any step in the search flow fails
         */
        public void searchBus(String fromCity, String toCity) {
            try {
                waitForHomePageToLoad();
                enterFromCity(fromCity);
                enterToCity(toCity);
                selectTodayDate();
                clickSearch();
                logSuccess("Bus search completed successfully!");
            } catch (Exception e) {
                logError("Bus search failed: " + e.getMessage());
                throw new RuntimeException("Bus search flow failed", e);
            }
        }

        // Utility Methods

        /**
         * Thread sleep wrapper with error handling
         * @param milliseconds Time to wait in milliseconds
         * @throws InterruptedException if thread is interrupted
         */
        private void waitFor(int milliseconds) throws InterruptedException {
            Thread.sleep(milliseconds);
        }

        /**
         * Log informational message
         * @param message Message to log
         */
        private void logInfo(String message) {
            System.out.println("ℹ️  " + message);
        }

        /**
         * Log success message
         * @param message Message to log
         */
        private void logSuccess(String message) {
            System.out.println("✅ " + message);
        }

        /**
         * Log warning message
         * @param message Message to log
         */
        private void logWarning(String message) {
            System.out.println("⚠️  " + message);
        }

        /**
         * Log error message
         * @param message Message to log
         */
        private void logError(String message) {
            System.out.println("❌ " + message);
        }
    }

