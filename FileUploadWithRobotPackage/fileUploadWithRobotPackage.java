package FileUploadWithRobotPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class fileUploadWithRobotPackage {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/fileUpload.html");
    }

    @Test
    public void testFileUploadWithRobot() throws AWTException, InterruptedException {
        String filePath = "C:\\Users\\ccst\\Desktop\\Selenium\\SeleniumMaterial\\loginData.csv";
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        WebElement fileInput = driver.findElement(By.id("fileInput"));
        Actions actions = new Actions(driver);
        actions.moveToElement(fileInput).click().perform();

        Thread.sleep(1500);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(2000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement uploadBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("uploadBtn")));
        uploadBtn.click();
        //WebElement uploadBtn = driver.findElement(By.id("uploadBtn"));
        Thread.sleep(1000);



        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        Assert.assertTrue(result.isDisplayed(), "Upload result should be visible.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}