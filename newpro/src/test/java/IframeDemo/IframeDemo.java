package IframeDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait; // Added missing import

import java.time.Duration;

public class IframeDemo {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/iFrameDemo.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//Frame 1
        driver.switchTo().frame(0);

        WebElement frame1Btn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("frame1Btn"))
        );
        frame1Btn.click();
        Thread.sleep(1000);

        String frame1Result = driver.findElement(By.id("frame1Result")).getText();

        if (frame1Result.equals("Frame 1 button clicked!")) {
            System.out.println("Frame 1 Pass");
        } else {
            System.out.println("Frame 1 Failed");
        }


        driver.switchTo().defaultContent();

        //  FRAME 2
        driver.switchTo().frame(1);

        WebElement frame2Input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("frame2Input"))
        );
        frame2Input.sendKeys("Selenium Student");

        String enteredValue = frame2Input.getAttribute("value");

        if (enteredValue.equals("Selenium Student")) {
            System.out.println("Frame 2 Pass");
        } else {
            System.out.println("Frame 2 Failed");
        }


        driver.switchTo().defaultContent();


// Frame 3
        WebElement frame3Element = driver.findElement(By.id("frame3"));
        driver.switchTo().frame(frame3Element);

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("frame3Dropdown"))
        );
        Select select = new Select(dropdown);
        select.selectByVisibleText("Two");

        String selectedValue = select.getFirstSelectedOption().getText();
        if(selectedValue.equals("Two")){
            System.out.println("Pass: Frame 3");
        } else {
            System.out.println("Fail: Frame 3");
        }
        Thread.sleep(2000);
        driver.switchTo().defaultContent();



        WebElement maiBut = driver.findElement(By.id("mainBtn"));

        if(maiBut.isDisplayed()){
            System.out.println(" All Frames PASS");
        }
        Thread.sleep(2000);
        driver.switchTo().defaultContent();

//frame4
        WebElement outerFrame = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("outerFrame"))
        );
        driver.switchTo().frame(outerFrame);

        WebElement innerFrame = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("innerFrame"))
        );
        driver.switchTo().frame(innerFrame);
        Thread.sleep(2000);

        WebElement innerButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("innerFrameBtn"))
        );
        innerButton.click();
        Thread.sleep(2000);

        WebElement innerResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("innerFrameResult"))
        );
        Thread.sleep(2000);
        String frame4Result = innerResult.getText();

        if (frame4Result.equals("Inner frame button clicked!")) {
            System.out.println("Frame 4 Pass");
        } else {
            System.out.println("Frame 4 Failed");
        }

        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.quit();
    }
}
