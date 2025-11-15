package com.automation.redbus.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

    /**
     * Utility class to read configuration from config.properties file
     */

    public class ConfigReader
    {
       private static Properties properties;
        private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

        static {
            loadProperties();
        }

        /**
         * Load properties from config file
         */
        public static void loadProperties() {
            properties = new Properties();
            try {
                FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
                properties.load(fis);
                fis.close();
                System.out.println("✅ Configuration loaded successfully");
            } catch (IOException e) {
                System.err.println("❌ Failed to load config.properties: " + e.getMessage());
                throw new RuntimeException("Configuration file not found at: " + CONFIG_FILE_PATH, e);
            }
        }

        /**
         * Get property value by key
         */
        public static String getProperty(String key) {
            String value = properties.getProperty(key);
            if (value == null) {
                throw new RuntimeException("Property '" + key + "' not found in config.properties");
            }
            return value.trim();
        }

        // Convenience methods for common properties
        public static String getBaseUrl() {
            return getProperty("base.url");
        }

        public static String getFromCity() {
            return getProperty("from.city");
        }

        public static String getToCity() {
            return getProperty("to.city");
        }

        public static String getTravelDate() {
            return getProperty("travel.date");
        }

        public static String getBrowser() {
            return getProperty("browser");
        }

        public static int getAdults() {
            return Integer.parseInt(getProperty("adults"));
        }

        public static int getChildren() {
            return Integer.parseInt(getProperty("children"));
        }

        public static String getEmail() {
            return getProperty("email");
        }

        public static String getPhone() {
            return getProperty("phone");
        }

}
