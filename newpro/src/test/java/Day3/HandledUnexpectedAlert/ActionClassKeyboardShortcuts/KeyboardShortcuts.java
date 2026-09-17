package Day3.HandledUnexpectedAlert.ActionClassKeyboardShortcuts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KeyboardShortcuts {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/keyboardShortcuts.html");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);

            WebElement sourceText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("sourceText"))
            );
            WebElement targetText = driver.findElement(By.id("targetText"));

            //Step 1 click in to sorce container
            sourceText.click();

            //Step 2 Select all ctrl + a
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .perform();

            System.out.println("Print all Performed on source container ");
            Thread.sleep(1000);

            //Step 3 Copy control + c
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("c")
                    .keyUp(Keys.CONTROL)
                    .perform();
            System.out.println("Copied all text ");

            //Step 4 Paste all text ctrl + v
            targetText.click();
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("v")
                    .keyUp(Keys.CONTROL)
                    .perform();
            System.out.println("All the text is pasted ");
            Thread.sleep(1000);
            String result = driver.findElement(By.id("result")).getText();

            if (result.equals("Text copied successfully to Target !")) {
                System.out.println("Pass: The Text is copied and the result is displayed - " + result);
            } else {
                System.out.println("Fail: Result mismatched Actual. " + result);
            }
            //--Assignement Select all text from target box and clear it and validate the message at the bottom
            targetText.click();
            actions.keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .perform();

            System.out.println("Print all Performed on source container ");
            Thread.sleep(1000);
            actions.keyDown(Keys.BACK_SPACE)
                    .sendKeys()
                    .keyUp(Keys.BACK_SPACE)
                    .perform();//Text in Target does not match Source yet.
            String result3 = driver.findElement(By.id("result")).getText();
            Thread.sleep(1000);
            if (result3.equals("Text in Target does not match Source yet.")) {
                System.out.println("Pass: Text is deleted from the target box - " + result3);
            } else {
                System.out.println("Fail: Result mismatched Actual. " + result3);
            }
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            driver.quit();
        }

    }
}
