package Alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Alert {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/javascriptAlerts.html");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        //________________________SHOW SIMPLE ALERT______________________________________________
        driver.findElement(By.id("alertBtn")).click();

        org.openqa.selenium.Alert obj_simpleAlert = wait.until(ExpectedConditions.alertIsPresent());

        String alertText = obj_simpleAlert.getText();
        System.out.println("Alert Text: " + alertText);

        obj_simpleAlert.accept();

        String alertResult = driver.findElement(By.id("alertResult")).getText();
        if (alertResult.equals("Alert was shown and accepted.")){
            System.out.println("Pass: Simple alert handled correctly");
        } else {
            System.out.println("Fail: Alert result mismatch");
        }

        //________________________SHOW CONFIRM ALERT OK______________________________________________
        driver.findElement(By.id("confirmBtn")).click();

        org.openqa.selenium.Alert obj_confirmAlert = wait.until(ExpectedConditions.alertIsPresent());

        String text = obj_confirmAlert.getText();
        System.out.println("Confirm Alert Text: " + text);

        obj_confirmAlert.accept();

        String Result = driver.findElement(By.id("confirmResult")).getText();
        if (Result.equals("You clicked OK.")){
            System.out.println("Pass: Confirm alert OK handled correctly");
        } else {
            System.out.println("Fail: Alert result mismatch");
        }
//________________________SHOW CONFIRM ALERT CANCEL______________________________________________
        driver.findElement(By.id("confirmBtn")).click();

        org.openqa.selenium.Alert obj_confirmAlertD = wait.until(ExpectedConditions.alertIsPresent());

        String text2 = obj_confirmAlertD.getText();
        System.out.println("Confirm Alert Text: " + text2);

        obj_confirmAlert.dismiss();

        String Result1 = driver.findElement(By.id("confirmResult")).getText();
        if (Result1.equals("You clicked Cancel.")){
            System.out.println("Pass: Confirm alert Cancel handled correctly");
        } else {
            System.out.println("Fail: Alert result mismatch");
        }

    }
}