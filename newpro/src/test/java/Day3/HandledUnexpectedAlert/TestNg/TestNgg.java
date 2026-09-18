package Day3.HandledUnexpectedAlert.TestNg;

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

public class TestNgg {
    WebDriver driver;
    WebDriverWait wait;

    //-----------Locators------
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    @Test(groups = {"smoke"}) //(priority = 1)
    public void testPositiveLoginSauceDemo() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1000);

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement password = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        Thread.sleep(1000);
        loginBtn.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login Failed. URL: " + currentUrl);

        Thread.sleep(1000);
    }

    @Test(groups = {"regression"}) //(priority = 1)
    public void testNegativeLoginSauceDemo() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1000);

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement password = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce1");

        Thread.sleep(1000);
        loginBtn.click();

        WebElement errorBox = driver.findElement(By.xpath("//*[@id=\'login_button_container\']/div/form/div[3]/h3"));
        Assert.assertEquals(errorBox.getText(), "Epic sadface: Username and password do not match any user in this service");


        Thread.sleep(1000);
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}