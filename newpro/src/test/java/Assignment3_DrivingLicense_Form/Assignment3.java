package Assignment3_DrivingLicense_Form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment3 {

    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();

        try {
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

            Thread.sleep(1000);

            WebElement obj_fullname = driver.findElement(By.cssSelector("input[name='fullname']"));
            WebElement obj_add = driver.findElement(By.cssSelector("input[name='address']"));
            WebElement obj_age = driver.findElement(By.cssSelector("input[name='age']"));
            WebElement obj_place = driver.findElement(By.cssSelector("input[name='placeofbirth']"));
            WebElement obj_genderM = driver.findElement(By.id("Male"));
            WebElement obj_genderF = driver.findElement(By.id("Female"));
            WebElement obj_colorY = driver.findElement(By.name("color_yes"));
            WebElement obj_colorN = driver.findElement(By.name("color_no"));
            WebElement obj_licenseType = driver.findElement(By.id("licenseType"));
            WebElement obj_language = driver.findElement(By.id("languages"));
            WebElement obj_submit = driver.findElement(By.xpath("//button[text()='Submit']"));




            Select licenseTypeSelect = new Select(obj_licenseType);
            Select languageSelect = new Select(obj_language);

            //Handling Drop Downs
            //Option 1 : Select by Visible Text
            licenseTypeSelect.selectByVisibleText("Permanent");

            //Option 2 : Select by Value Attributes
            //licenseTypeSelect.selectByValue("permanent");

            //Option 3 : Select by Index (0=placeholder , 1 = Permanent, 2 = Learning)
            //licenseTypeSelect.selectByIndex(1);


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



            Thread.sleep(1000);

            //Handling Drop Downs
            //Option 1 : Select by Visible Text
            //languageSelect.selectByVisibleText("Hindi");

            //Option 2 : Select by Value Attributes
            languageSelect.selectByValue("hindi");

            //Option 3 : Select by Index (0=placeholder , 1 = Permanent, 2 = Learning)
            //languageSelect.selectByIndex(1);
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
