package Day3.HandledUnexpectedAlert.AssignmentActions_FileUpload;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Action_File {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///C:/Users/ccst/Desktop/challenge_MouseKeyboardActions.html");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);
            Thread.sleep(2000);
            //-------Hover over Document Menu & Click Upload Documents
            WebElement obj_menuItem = driver.findElement(By.id("documentsMenu"));
            actions.moveToElement(obj_menuItem).perform();
            Thread.sleep(2000);
            WebElement obj_submenu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("documentsSubmenu"))
            );
            if (obj_submenu.isDisplayed()) {
                System.out.println("Pass: Sub Menu '" + obj_submenu + "' appeared on hover.");
            } else {
                System.out.println("Fail: Submenu '" + obj_submenu + "'did not appear.");
            }
            actions.moveToElement(obj_menuItem).perform();
            Thread.sleep(2000);
            WebElement obj_UploadDoc = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("uploadDocLink"))
            );
            obj_UploadDoc.click();
            Thread.sleep(2000);
            String resultText = driver.findElement(By.xpath("//*[@id=\"stepLog\"]/p")).getText();
            if (resultText.equals("Step 1: 'Upload Document' clicked from hover menu.")) {
                System.out.println("Pass: Clicked Upload Document");
            } else {
                System.out.println("Fail: Result Mismatched .Actual " + resultText);
            }

            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            //-------Step 3 & step 4
            WebElement fileInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("fileInput"))
            );
            //Absolute path to a real file on your machine
            String filePath ="C:\\Users\\ccst\\Desktop\\SQL DATA base.txt";
            fileInput.sendKeys(filePath);
            Thread.sleep(1000);
            System.out.println("File Path sent to input field");

            //----Setp 2 verify the vile name appears on the page
            WebElement fileNameLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("fileName"))
            );

            if(fileNameLabel.getText().contains("SQL DATA base.txt")){
                System.out.println("Pass: File selected and displayed correctly. Text = " + fileNameLabel);
            }else {
                System.out.println("Fail:  File name not displayed as expected . Actual =" + fileNameLabel);
            }
            //------Step 4 Click upload
            WebElement uploadBtn = driver.findElement(By.id("uploadBtn"));
            uploadBtn.click();
            Thread.sleep(5000);
            //-----Step 4 Verify Success Message -------
            WebElement result = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("result"))
            );

            actions.sendKeys(Keys.END).perform();
            String result22 = driver.findElement(By.id("result")).getText();
            if (result22.equals("You greedy fellow !!")) {
                System.out.println("Pass: File Uploaded Successfully and message Printed " + result22);
            } else {
                System.out.println("Fail: Result Mismatched .Actual " + result22);
            }



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
