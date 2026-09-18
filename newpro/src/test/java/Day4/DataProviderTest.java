package Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTest {
    WebDriver driver;
    WebDriverWait wait;

    // -----------Locators------
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    // -----------------------------------DATA PROVIDER ------------------------
    @DataProvider(name = "loginDataset")
    public Object[][] getLoginData() throws IOException {
        String csvFilePath = "C:\\Users\\ccst\\Desktop\\Selenium\\SeleniumMaterial\\loginData.csv";
        List<Object[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] values = line.split(",");
                String username = values[0].trim();
                String password = values[1].trim();
                records.add(new Object[]{username, password});
            }
        }
        return records.toArray(new Object[0][0]);
    }

    // ----------Test case using Data provider -------------------
    @Test(dataProvider = "loginDataset")
    public void testPositiveLoginSauceDemo(String username, String password) {
        driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement passwordInput = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);


        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login Failed. URL: " + currentUrl);
    }

    /*@Test(groups = {"regression"})
    public void testNegativeLoginSauceDemo() {
        driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement passwordInput = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        usernameInput.sendKeys("invalid_user");
        passwordInput.sendKeys("wrong_pass");
        loginBtn.click();

        WebElement errorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        Assert.assertEquals(errorBox.getText(), "Epic sadface: Username and password do not match any user in this service");
    }*/

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}