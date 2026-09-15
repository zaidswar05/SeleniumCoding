package Assignment3_DrivingLicense_Form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Assignment3 {

    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();

        try {
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

            Thread.sleep(1000);

            WebElement obj_fullname = driver.findElement(By.id("fullname"));
            WebElement obj_add = driver.findElement(By.id("address"));
            WebElement obj_age = driver.findElement(By.id("age"));
            WebElement obj_place = driver.findElement(By.id("placeofbirth"));
            WebElement obj_genderM = driver.findElement(By.id("Male"));
            WebElement obj_genderF = driver.findElement(By.id("Female"));
            WebElement obj_colorY = driver.findElement(By.name("color_yes"));
            WebElement obj_colorN = driver.findElement(By.name("color_no"));
            WebElement obj_submit = driver.findElement(By.xpath("//button[text()='Submit']"));

            Thread.sleep(1000);

            obj_fullname.sendKeys("Paryaaaaa");
            obj_add.sendKeys("Sutarwadi");
            obj_age.sendKeys("27");
            obj_place.sendKeys("Thane");

            Thread.sleep(1000);
            obj_genderM.click();
            Thread.sleep(1000);
            obj_genderF.click();
            Thread.sleep(1000);
            obj_colorY.click();
            Thread.sleep(1000);
            obj_colorN.click();
            Thread.sleep(1000);
            obj_submit.click();

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            driver.quit();
        }
    }
}
