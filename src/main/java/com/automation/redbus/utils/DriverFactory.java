package com.automation.redbus.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory
{

        // Thread-safe WebDriver instance for parallel testing
        private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

        // Initialize the driver based on browser name
        public static WebDriver initDriver(String browser) {
            if (browser == null) browser = "chrome";

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;

                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            getDriver().manage().window().maximize();
            return getDriver();
        }

        // Get driver for current thread
        public static WebDriver getDriver() {
            return driver.get();
        }

        // Quit driver
        public static void quitDriver() {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
        }
    }

