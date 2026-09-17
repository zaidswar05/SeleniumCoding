package Day3.HandledUnexpectedAlert.TestNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavigateTestNgg {
    WebDriver driver;
    WebDriverWait wait;

    //-----Locators----

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Initialize explicit wait instead of using Thread.sleep()
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testPositiveNavigateSites() {
        // Step 1: Navigate to SauceDemo
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.titleIs("Swag Labs"));

        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch for SauceDemo");

        
        // Step 2: Navigate to Google
        driver.navigate().to("https://www.google.com/");
        wait.until(ExpectedConditions.titleIs("Google"));


        Assert.assertEquals(driver.getTitle(), "Google", "Title mismatch for Google");

        // Step 3: Navigate to Amazon
        driver.navigate().to("https://www.amazon.com/");
        wait.until(ExpectedConditions.titleContains("Amazon"));


        Assert.assertTrue(driver.getTitle().contains("Amazon"), "Title mismatch for Amazon");

        // Step 4: Navigate back to Google
        driver.navigate().back();
        wait.until(ExpectedConditions.titleIs("Google"));


        Assert.assertEquals(driver.getTitle(), "Google", "Failed to navigate back to Google");

        // Step 5: Navigate back to SauceDemo
        driver.navigate().back();
        wait.until(ExpectedConditions.titleIs("Swag Labs"));


        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Failed to navigate back to SauceDemo");

        // Step 6: Refresh SauceDemo
        driver.navigate().refresh();
        wait.until(ExpectedConditions.titleIs("Swag Labs"));


        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title changed unexpectedly after refresh");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}