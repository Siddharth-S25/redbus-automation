# 🚌 RedBus Automation Testing Framework

> A comprehensive Selenium-based automation framework for testing RedBus bus booking functionality

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.23-green.svg)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10-red.svg)](https://testng.org/)

## 📋 Overview

This framework demonstrates automated testing of the RedBus platform using industry-standard tools and design patterns. It showcases end-to-end test automation with focus on maintainability, scalability, and reporting.

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17+ | Programming Language |
| Selenium WebDriver | 4.23 | Browser Automation |
| TestNG | 7.10 | Test Framework |
| Maven | 3.8+ | Build & Dependency Management |
| ExtentReports | 5.1 | HTML Test Reports |
| WebDriverManager | 5.9 | Automatic Driver Management |

## ✨ Key Features

### Framework Capabilities
- ✅ **Page Object Model (POM)** - Maintainable and scalable architecture
- ✅ **Automatic Screenshot Capture** - On test failure for debugging
- ✅ **Detailed HTML Reports** - ExtentReports with test execution summary
- ✅ **Explicit Waits** - Robust synchronization handling
- ✅ **Cross-Browser Support** - Chrome, Firefox, Edge
- ✅ **Externalized Configuration** - Easy test data management
- ✅ **Comprehensive Logging** - Step-by-step execution logs

### Test Coverage
1. **End-to-End Bus Search Flow**
    - Home page navigation and validation
    - Source and destination city selection
    - Date selection and search execution
    - Search results validation

2. **Search Results Page Validation**
    - Filter section verification
    - Bus count validation
    - Page component checks
    - URL parameter validation

## 📁 Project Structure

```
redbus-automation/
│
├── src/test/java/
│   └── com/automation/redbus/
│       ├── base/
│       │   └── BaseTest.java              # Base test setup & teardown
│       ├── pages/
│       │   ├── HomePage.java              # Home page object
│       │   └── SearchResultPage.java      # Search results page object
│       ├── tests/
│       │   └── RedBusBookingTest.java     # Test scenarios
│       ├── utils/
│       │   ├── ConfigReader.java          # Configuration handler
│       │   └── ScreenshotUtil.java        # Screenshot utility
│       └── listeners/
│           └── ExtentTestListener.java    # Report listener
│
├── src/test/resources/
│   ├── config.properties                  # Test configuration
│   └── testng.xml                         # TestNG suite file
│
├── test-output/
│   ├── extent-reports/                    # HTML test reports
│   └── screenshots/                       # Failure screenshots
│
├── pom.xml                                # Maven dependencies
└── README.md
```

## 🚀 Quick Start

### Prerequisites
```bash
# Required software
- Java 17 or higher
- Maven 3.8+
- Chrome browser (latest version)
```

### Installation & Execution

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/redbus-automation.git
cd redbus-automation
```

2. **Install dependencies**
```bash
mvn clean install
```

3. **Run tests**
```bash
mvn clean test
```

### Alternative Execution Methods

**From IntelliJ IDEA:**
- Right-click on `testng.xml` → Run

**Specific test class:**
```bash
mvn test -Dtest=RedBusBookingTest
```

**With specific browser:**
```bash
mvn test -Dbrowser=chrome
```

## 📊 Test Reports

### ExtentReports
After test execution, view the detailed HTML report:
- **Location:** `test-output/extent-reports/TestReport_[timestamp].html`
- **Contains:**
    - Test execution summary (Pass/Fail/Skip)
    - Detailed step-by-step logs
    - Screenshots for failed tests
    - Execution time and environment details

### Screenshots
Failed test screenshots are automatically saved:
- **Location:** `test-output/screenshots/`
- **Format:** `[TestName]_[Timestamp].png`

## ⚙️ Configuration

Edit `src/test/resources/config.properties`:

```properties
# Application URL
url=https://www.redbus.in/

# Browser configuration
browser=chrome

# Test data
from.city=Swargate
to.city=Mumbai
```

## 🧪 Test Scenarios

### Test Suite Summary

| Test | Description | Priority |
|------|-------------|----------|
| testBusSearchFlow | End-to-end bus search validation | 1 |
| testSearchResultsPageComponents | Search results page verification | 2 |

### Detailed Test Flow

**Test 1: Bus Search Flow**
1. Navigate to RedBus homepage
2. Enter source city (Swargate)
3. Enter destination city (Mumbai)
4. Select today's date
5. Click search button
6. Verify search results page loads
7. Validate URL contains search parameters
8. Verify minimum bus count

**Test 2: Search Results Components** (Depends on Test 1)
1. Wait for results to load completely
2. Verify filter section is present
3. Validate bus results are displayed
4. Check actual bus count > 0

## 🎯 Design Patterns & Best Practices

### Implemented Patterns
- **Page Object Model** - Separation of test logic and page elements
- **Singleton Pattern** - ConfigReader for centralized configuration
- **Factory Pattern** - WebDriver initialization for multiple browsers

### Best Practices
- ✅ Explicit waits over implicit waits and Thread.sleep()
- ✅ Reusable utility methods
- ✅ Meaningful method and variable names
- ✅ Comprehensive error handling
- ✅ Detailed logging at each step
- ✅ Test independence and isolation
- ✅ Configuration externalization

## 🔧 Advanced Features

### Automatic Screenshot on Failure
```java
@AfterMethod
public void captureScreenshotOnFailure(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
        ScreenshotUtil.captureScreenshot(driver, result.getName());
    }
}
```

### Dynamic Element Handling
```java
// Wait for element to be clickable
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
```

### Test Dependencies
```java
@Test(priority = 2, dependsOnMethods = "testBusSearchFlow")
public void testSearchResultsPageComponents() {
    // Continues on same page from previous test
}
```

## -- Troubleshooting --

### Common Issues & Solutions

**Issue:** Tests fail due to element not found
```
Solution: Increase wait time in BaseTest.java or update locators in page classes
```

**Issue:** Browser doesn't launch
```
Solution: Update Selenium version or check Chrome browser version compatibility
```

**Issue:** Screenshots not captured
```
Solution: Ensure test-output/screenshots/ directory has write permissions
```

## 📈 Sample Test Results

```
===============================================
RedBus Automation Test Suite
Total tests run: 2, Passes: 2, Failures: 0, Skips: 0
===============================================
```
## 🔜 Future Enhancements

- [ ] Parallel test execution support
- [ ] Data-driven testing with multiple routes
- [ ] Integration with CI/CD (Jenkins/GitHub Actions)
- [ ] API testing integration
- [ ] Cross-browser testing pipeline
- [ ] Performance testing metrics

## 🤝 Contributing

This is a portfolio project. For suggestions or improvements:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📚 Learning Resources
If you want to learn more about the technologies used:

- Selenium Documentation
- TestNG Documentation
- Page Object Model
- ExtentReports
- Maven

## 👤 Author

**[Your Name]**
- 📧 Email: [ersabalesiddharth@gmail.com](ersabalesiddharth@gmail.com) 
- 🐙 GitHub: [Siddharth-S25](https://github.com/Siddharth-S25)

## 🙏 Acknowledgments

- Selenium WebDriver Community
- TestNG Framework
- ExtentReports Library
- RedBus Platform (for testing purposes)

---

**Note:** This framework is designed for educational and demonstration purposes as part of a professional portfolio.

- Test Execution Time: ~45 seconds for 2 tests
- Success Rate: 100% (2/2 tests passing)
- Code Coverage: End-to-end bus search flow
- Feel free to explore the code and run the tests. For any questions, please reach out via the contact information above.

<div align="center">
⭐ If you find this project helpful, please consider giving it a star! ⭐
    
Last Updated: November 2025
</div>
