package Day3.HandledUnexpectedAlert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class HandledUnexpectedAlert {
    public static void main(String[] args) {
       ChromeOptions options = new ChromeOptions();
       options.setCapability("unhandledPromptBehavior","accept");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        try{
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/javascriptAlerts.html");
            Thread.sleep(1000);
            driver.findElement(By.id("alertBtn")).click();

            Thread.sleep(1000);
            System.out.println("Page Title: "+ driver.getTitle());

            Thread.sleep(1000);
            //________________________________Confirm Box __________________________

            driver.findElement(By.id("confirmBtn")).click();

            Thread.sleep(1000);
            System.out.println("Page Title: "+ driver.getTitle());

            Thread.sleep(2000);

        //___________________Prompt Box___________________
            WebElement prompt = driver.findElement(By.id("promptBtn"));
            prompt.click();

            Alert promptAlert = driver.switchTo().alert();
            String inputName = "Zadyaaaaa";

            promptAlert.sendKeys(inputName);
            promptAlert.accept();

            Thread.sleep(1000);
            System.out.println("Page Title: "+ driver.getTitle());

            Thread.sleep(5000);





        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            driver.quit();
        }
    }
}
