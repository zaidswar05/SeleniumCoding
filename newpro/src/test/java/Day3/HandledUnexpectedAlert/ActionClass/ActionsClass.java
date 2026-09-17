package Day3.HandledUnexpectedAlert.ActionClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class ActionsClass {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/actionClass_Menu.html#");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);

            //-------- Product Menu------

            WebElement obj_menuItem = driver.findElement(By.id("productsMenu"));
            actions.moveToElement(obj_menuItem).perform();

            WebElement obj_submenu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("productsSubmenu"))
            );
            if (obj_submenu.isDisplayed()) {
                System.out.println("Pass: Sub Menu '" + obj_submenu + "' appeared on hover.");
            } else {
                System.out.println("Fail: Submenu '" + obj_submenu + "'did not appear.");
            }
            actions.moveToElement(obj_menuItem).perform();

            WebElement obj_Laptopslink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("laptopsLink"))
            );
            obj_Laptopslink.click();

            String resultText = driver.findElement(By.id("result")).getText();
            if (resultText.equals("You clicked: Laptops")) {
                System.out.println("Pass: Clicked Laptops");
            } else {
                System.out.println("Fail: Result Mismatched .Actual " + resultText);
            }
            //-----------Services ----------

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
