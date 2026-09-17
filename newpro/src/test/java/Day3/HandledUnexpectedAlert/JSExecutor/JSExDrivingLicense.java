package Day3.HandledUnexpectedAlert.JSExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JSExDrivingLicense {
    public static void main(String[] args) {
            WebDriver driver = new ChromeDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            try {
                driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");
                WebElement obj_heading = driver.findElement(By.xpath("//h2[text()='Driving License Application']"));
                js.executeScript("arguments[0].style.border='3px solid red'; ", obj_heading);
                js.executeScript("arguments[0].style.backgroundColor='yellow';", obj_heading);
                Thread.sleep(2000);




            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                driver.quit();
            }
    }
}
