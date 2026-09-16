package ajax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Ajx {
        public static void main(String[] args) {

            WebDriver driver = new EdgeDriver();

            try {

                driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/challenge_AjaxPage.html");



                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                 // 2. Wait for Spinner to disappear Use - Invisibilityofelementlocated
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));


                WebElement loadQuoteBtn = driver.findElement(By.id("loadQuoteBtn"));
                loadQuoteBtn.click();

                // 5. Wait for Fetching Quite..." to disaooear(Ajax in progress) - invisibilityOfElementLocated
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("quoteLoading")));

                System.out.println("Pass: AJAX completed.");


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        }
    }
