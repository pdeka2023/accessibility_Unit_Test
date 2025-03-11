package accessibility;

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
