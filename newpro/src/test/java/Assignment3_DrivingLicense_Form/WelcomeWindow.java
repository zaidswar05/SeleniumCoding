package Assignment3_DrivingLicense_Form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

public class WelcomeWindow {

    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();

        try {
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");

            Thread.sleep(1000);

            //Store the original parent window handle
            String parentWindow = driver.getWindowHandle();

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
            // languageSelect.selectByValue("hindi");

            //Option 3 : Select by Index (0=placeholder , 1 = Permanent, 2 = Learning)
            languageSelect.selectByIndex(1);
            Thread.sleep(1000);

            obj_submit.click();

            Thread.sleep(1000);

            //Get all windpws handles
            Set<String> allWindows = driver.getWindowHandles();

            for(String windowHandle : allWindows){
                System.out.println("Window handle desc" + windowHandle);

                if(!windowHandle.equals(parentWindow)){
                    driver.switchTo().window(windowHandle);
                    String newWindow = driver.getWindowHandle();
                    System.out.println("Window handle desc" + windowHandle);
                    break;
                }
            }


            //Verify URL
            String actualUrl = driver.getCurrentUrl();

            if(actualUrl.contains("welcome.html")){
                System.out.println("Pass: Url Verified. Actual URL: " + actualUrl);
            }else{
                System.out.println("Fail: Url Verified. Actual URL: " + actualUrl);
            }

            //Verify Title
            String actualTitle = driver.getTitle();
            String expectedTitle = "Welcome";

            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Pass: Title Verified. Actual Title: " + actualTitle);
            } else {
                System.out.println("Fail: Title Verification Failed. Expected: " + expectedTitle + ", but got: " + actualTitle);
            }



            Thread.sleep(7000);
            WebElement vi = driver.findElement(By.xpath("//input[@id='nameField' and @disabled]"));
            if(!vi.isEnabled()){
                System.out.println("Test case pass For Disable Text Box");
            }else {
                System.out.println("TestCase Failed (If Enable)For Disable Text Box");
            }

            Thread.sleep(5000);
            WebElement Enter = driver.findElement(By.id("enterNameBtn"));
            Enter.click();

            WebElement vi2 = driver.findElement(By.id("nameField"));
            if(vi2.isEnabled()){
                vi2.sendKeys("Parag");
                System.out.println("Test case pass For Enable Text Box");
            }else {
                System.out.println("TestCase Failed (If Disable)");
            }




        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            driver.quit();
        }
    }
}
