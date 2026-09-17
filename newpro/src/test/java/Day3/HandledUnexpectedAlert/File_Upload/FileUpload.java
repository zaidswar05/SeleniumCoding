package Day3.HandledUnexpectedAlert.File_Upload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Assignment3_DrivingLicense_Form.CaptureSS.driver;


public class FileUpload {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/fileUpload.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //------Step 1 Locate the file input and ser the file path
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

        String expectedMessage = driver.findElement(By.id("result")).getText();
        Thread.sleep(1000);
        if (result.getText().equals(expectedMessage)) {
            System.out.println("Pass: Upload confirm Message " + result.getText());
        } else {
            System.out.println("Fail: Result mismatched Actual. " + result.getText());
        }

        driver.quit();
    }

}



