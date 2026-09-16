package Assignment3_DrivingLicense_Form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Assignment3_DrivingLicense_Form.CaptureSS.driver;

public class ExplicitWaitt {

    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();


        try {
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/welcome.html?");

           /* WebDriverWait obj_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement message = obj_wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("message"))
            );*/


            WebDriverWait waitbtn = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement message1 = waitbtn.until(
                    ExpectedConditions.elementToBeClickable(By.id("enterNameBtn"))
            );

            //Verify presence of element on UI
            if (message1.getText().equals("Welcome!!")) {
                System.out.println("Pass: Welcome message displayed correctly");
            } else {
                System.out.println("Fail: Message text mismatched.actual: " + message1.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}


