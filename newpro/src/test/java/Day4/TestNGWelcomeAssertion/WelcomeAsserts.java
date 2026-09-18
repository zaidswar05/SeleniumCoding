package Day4.TestNGWelcomeAssertion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WelcomeAsserts {
    WebDriver driver;
    WebDriverWait wait;

    // Locators for welcome.html
    By welcomeMessage = By.id("message");
    By enterNameButton = By.id("enterNameBtn");
    By nameInputField = By.id("nameField");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testWelcomeFormInteractions() {
        driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/welcome.html?");

        // 1
        // 1. Wait until page title matches, then assert
        wait.until(ExpectedConditions.titleIs("Welcome"));
        Assert.assertEquals(driver.getTitle(), "Welcome");
        // 2
        WebElement textField = driver.findElement(nameInputField);
        Assert.assertFalse(textField.isEnabled());

        // 3
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(enterNameButton));
        button.click();
        wait.until(ExpectedConditions.elementToBeClickable(textField));
        Assert.assertTrue(textField.isEnabled());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}