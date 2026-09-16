package Assignment3_DrivingLicense_Form;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FluentWaittt {

    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();

        try {

            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/welcome.html?");

            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement enterBtn = explicitWait.until(
                    ExpectedConditions.elementToBeClickable(By.id("enterNameBtn"))
            );
            System.out.println("Pass: enterNameBtn is present");


            Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);

            WebElement message = fluentWait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("message"))
            );


            if (message.getText().equals("Welcome!!")) {
                System.out.println("Pass: Welcome message displayed correctly");
            } else {
                System.out.println("Fail: Message text mismatched. actual: " + message.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}