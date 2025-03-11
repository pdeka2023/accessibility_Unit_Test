# Accessibility
## Accessibility Automation for Web Apps with Java and Selenium Webdriver.

### This project uses [HTML CodeSniffer](https://squizlabs.github.io/HTML_CodeSniffer/) and [Deque Axe](https://www.deque.com/)

**HTML CodeSniffer** : checks HTML source code and detects any Accessibility violations. Comes with standards that cover the three (A, AA & AAA) conformance levels of the W3C's Web Content Accessibility Guidelines (WCAG) 2.1 and the U.S. Section 508 legislation.

**Deque Axe** : World’s leading digital accessibility toolkit. Powerful and accurate accessibility toolkit can get you to 80% issue coverage, or more, during development.


### Features
1. Simple & Easy to use
2. No need of prior knowledge on Accessibility
3. Works with Java Selenium [Webdriver](https://www.selenium.dev/projects/)
4. Rich Reporting
5. Open source

For Maven based project add the below dependency

```
For non maven project download the jar from below mentioned link and add it to CLASSPATH for your project

### Getting Started
#### Using HTML CodeSniffer
Create object of `HtmlCsRunner` as below. `driver` will be your WebDriver instance.
```java
HtmlCsRunner htmlCsRunner = new HtmlCsRunner(driver);;
```

Once after you navigated to any page/popup with Selenium Webdriver execute Accessibility on that particular page/popup
```java
htmlCsRunner.execute();
```

The above `execute` will also generate `JSON Report` on accessibility issues at page/popup level

Once after all the tests executed, you can call the below method to generate consolidated `HTML Report` on accessibility issues
```java
htmlCsRunner.generateHtmlReport();
```

Below is junit example with reporting.

```java
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import axe.my.support.HtmlCsRunner;

import java.io.IOException;
import java.time.Duration;

/**
 * A sample test to demonstrate
 */
public class Example {

    private WebDriver driver;
    private static HtmlCsRunner htmlCsRunner;

    @BeforeEach
    public void beforeTest() {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().fullscreen();
        htmlCsRunner = new HtmlCsRunner(driver);

    }

    @AfterEach
    public void tearDown() throws IOException {
        htmlCsRunner.execute();
        driver.quit();
    }

    @AfterAll
    public static void generateReport() throws IOException {
        htmlCsRunner.generateHtmlReport();
    }

    @Test
    public void cmcmsTest() {
    	driver.get("https://www.medicaid.gov/");
    }
    
    @Test
    public void cmcmsChipTest() {
    	driver.get("https://www.medicaid.gov/chip/state-program-information/chip-spa/index.html/");
    }
    
    //
    @Test
    public void insureKids() {
    	driver.get("https://www.insurekidsnow.gov/");
    }

}

```

By default, it will check against `WCAG2AA` standards. However, you can configure it to standard you want to test with
```java
htmlCsRunner.setStandard(HTMLCS.WCAG2A);
```

HTML Reports will be generated under `./target/java-a11y/htmlcs` folder.

Below are the report screenshots

Consolidated Report

![Index](/readme/htmlcs_index.png)

Page Report

![Page](/readme/htmlcs_page.png)

#### Using Deque Axe
Create object of `AxeRunner` as below. `driver` will be your WebDriver instance.
```java
AxeRunner axeRunner = new AxeRunner(driver);
```

Once after you navigated to any page/popup with Selenium Webdriver execute Accessibility on that particular page/popup
```java
axeRunner.execute();
```

The above `execute` will also generate `JSON Report` on accessibility issues at page/popup level

Once after all the tests executed, you can call the below method to generate consolidated `HTML Report` on accessibility issues
```java
axeRunner.generateHtmlReport();
```

Below is junit example with reporting.

```java
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import axe.my.support.HtmlCsRunner;

import java.io.IOException;
import java.time.Duration;

/**
 * A sample test to demonstrate
 */
public class Example {

    private WebDriver driver;
    private static HtmlCsRunner htmlCsRunner;

    @BeforeEach
    public void beforeTest() {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().fullscreen();
        htmlCsRunner = new HtmlCsRunner(driver);

    }

    @AfterEach
    public void tearDown() throws IOException {
        htmlCsRunner.execute();
        driver.quit();
    }

    @AfterAll
    public static void generateReport() throws IOException {
        htmlCsRunner.generateHtmlReport();
    }

    @Test
    public void cmcmsTest() {
    	driver.get("https://www.medicaid.gov/");
    }
    
    @Test
    public void cmcmsChipTest() {
    	driver.get("https://www.medicaid.gov/chip/state-program-information/chip-spa/index.html/");
    }
    
    //
    @Test
    public void insureKids() {
    	driver.get("https://www.insurekidsnow.gov/");
    }

}

```

By default, it will check against `WCAG2AA` and `section508` tags. However, you can configure it to tag you want to test with
```java
axeRunner.setTags(AxeTag.WCAG21AA);
```
Or multiple tags using varargs syntax
```java
axeRunner.setTags(AxeTag.WCAG21A, AxeTag.BEST_PRACTICE);
```

Each [Tag](https://www.deque.com/axe/core-documentation/api-documentation/#axe-core-tags) has set of [Rules](https://github.com/dequelabs/axe-core/blob/develop/doc/rule-descriptions.md) and you can enable/disable particular rule as needed
```java
axeRunner.disableRules("link-name", "aria-allowed-role")
         .enableRules("autocomplete-valid", "region")
```

HTML Reports will be generated under `./target/java-a11y/axe` folder.

Below are the report screenshots

Consolidated Report

![Index](/readme/axe_index.png)

Page Report

![Page](/readme/axe_page.png)
