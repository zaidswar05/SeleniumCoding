package Assignment3_DrivingLicense_Form;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class CaptureSS {
    public static WebDriver driver;
    static String screenshotDir;

    public static void main(String[] args) {
        screenshotDir = "screenshots/";
        new File(screenshotDir).mkdir();
        try {
            driver = new ChromeDriver();
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/TestcasesClassAssignment-drivingLicenseUI.html");
            Thread.sleep(2000);

            String actualTitle = driver.getTitle();
            String expectedTitle = "Driving License Application";

            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Pass: Title Verified. Actual Title" + actualTitle);
            } else {
                System.out.println("False: Title mismatch Expected: " + expectedTitle + ", Actual: " + actualTitle);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }

        public static void captureScreenshot(WebDriver driver, String file_name){

            try {
                //take the screenshot
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                //Save it to a location on your computer
                FileUtils.copyFile(screenshot, new File(screenshotDir + "/screenshot.png"));
            }
            catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("System Saved!");
            }
        }
