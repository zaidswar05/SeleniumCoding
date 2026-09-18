package Capstone_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Capstone {
    WebDriver driver;
    WebDriverWait wait;

    // ----------- Locators -----------
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");
    By pageTitle = By.className("title");

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @DataProvider(name = "validData")
    public Object[][] getValidData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidData")
    public Object[][] getInvalidData() {
        return new Object[][]{
                {"user", "secret_sauce"}
        };
    }

    private void performLogin(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userField.sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Test(priority = 1, dataProvider = "validData")

    public void testPositiveLoginSauceDemo(String username, String password) {

        driver.get("https://www.saucedemo.com/");
        WebElement usernameFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(passwordField).sendKeys(password);
        usernameFieldElement.sendKeys(username);
        driver.findElement(loginButton).click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login Failed. URL: " + driver.getCurrentUrl());

    }

    @Test(priority = 2, dataProvider = "invalidData")
    public void testNegativeLoginSauceDemo(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        WebElement usernameFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(passwordField).sendKeys(password);
        usernameFieldElement.sendKeys(username);
        driver.findElement(loginButton).click();

        WebElement errorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        Assert.assertEquals(errorBox.getText(), "Epic sadface: Username and password do not match any user in this service");

    }

    @Test(priority = 3, dataProvider = "validData")
    public void testAddProductsAndCheckout(String username, String password) {
        performLogin(username, password);
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        // Add 3 Products to the cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        Assert.assertEquals(badge.getText(), "3", "Cart count is incorrect.");
        System.out.println("3: 3 items are added to the cart.");

        // Remove 1 product
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "2"));
        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "2", "Cart count is incorrect.");
        System.out.println("3: 2 items are in the cart after 1 removal.");

        // Checkout items from cart
        driver.findElement(By.className("shopping_cart_link")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Your Cart"));
        Assert.assertEquals(driver.findElement(pageTitle).getText(), "Your Cart", "Cart Page Not loaded");
        System.out.println("3: Cart Page is loaded.");

        driver.findElement(By.id("checkout")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Checkout: Your Information"));
        Assert.assertEquals(driver.findElement(pageTitle).getText(), "Checkout: Your Information", "Information Page Not loaded");
        System.out.println("4: Checkout Information Page is loaded.");

        driver.findElement(By.id("first-name")).sendKeys("Zadyaaaaa");
        driver.findElement(By.id("last-name")).sendKeys("Bhauuuuu");
        driver.findElement(By.id("postal-code")).sendKeys("411014");
        driver.findElement(By.id("continue")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Checkout: Overview"));
        Assert.assertEquals(driver.findElement(pageTitle).getText(), "Checkout: Overview", "Overview page is Not loaded");
        System.out.println("4: Checkout Overview Page is loaded.");

        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();

        WebElement finalDiag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(finalDiag.getText(), "Thank you for your order!", "Order was not placed successfully");
        System.out.println("4: Order is placed and Thank you message is posted.");

        WebElement backhome = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        backhome.click();

        // --------------------- Price low to high ---------------------
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        WebElement sortDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container")));
        Select selectMenu = new Select(sortDropdown);
        selectMenu.selectByVisibleText("Price (low to high)");
        System.out.println("5: Products dropdown changed to Price (low to high).");

        //--------Verify the 1st item ------------------
        WebElement firstItemName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item_name")));
        WebElement firstItemPrice = driver.findElement(By.cssSelector(".inventory_item_price"));

        // Verify the first item matches the lowest price expectations
        Assert.assertEquals(firstItemName.getText(), "Sauce Labs Onesie", "Sorting failed: Incorrect first item name.");
        Assert.assertEquals(firstItemPrice.getText(), "$7.99", "Sorting failed: Incorrect lowest price.");

        System.out.println("5: The Lowest Price item is " + firstItemName.getText() + " at " + firstItemPrice.getText());

        WebElement sidebar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
        sidebar.click();

        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        logout.click();

        System.out.println("6: Logout is Completed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}