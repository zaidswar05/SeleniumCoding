package Day3.HandledUnexpectedAlert.DragDrop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class DragDrop {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/dragDrop.html");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);

            WebElement item1 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("item1"))
            );
            WebElement targetContainer = driver.findElement(By.id("targetContainer"));

            //Attempt1- drag and drop

            actions.dragAndDrop(item1, targetContainer).perform();

            String result1 = driver.findElement(By.id("result")).getText();
            System.out.println("After drag and drop() : " + result1);

            if (result1.contains("Write Manual Testcases moved to Done")) {
                System.out.println("Pass: Drag and drop worked with dragAndDrop().");
            } else {
                System.out.println("Fail: dragAndDrop() did not register this move as expected. ");
            }
            Thread.sleep(1000);

            //Attempt 2 Manual click hold move release sequence

            WebElement item2 = driver.findElement(By.id("item2"));


            actions.clickAndHold(item2)
                    .moveToElement(targetContainer)
                    .pause(Duration.ofMillis(300))
                    .release()
                    .build()
                    .perform();

            String result2 = driver.findElement(By.id("result")).getText();
            System.out.println("After manual click hold move release  : " + result2);

            if (result1.contains("Define Entry and Exit Criteria moved to Done")) {
                System.out.println("Pass: Drag and drop worked with manual aActions sequence.");
            } else {
                System.out.println("Fail: Manual Actions Sequence did not registered the move as expected ");
            }
        //-----Attempt 3 to reverse the 3 options to container
            //Attempt1- drag and drop
            WebElement item3 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("item1"))
            );
            WebElement sourceContainer = driver.findElement(By.id("sourceContainer"));


            actions.dragAndDrop(item1, sourceContainer).perform();

            String result3 = driver.findElement(By.id("result")).getText();
            System.out.println("After drag and drop() : " + result3);

            if (result3.contains("Write Manual Testcases moved to TO DO")) {
                System.out.println("Pass: Reverse Drag and drop worked with dragAndDrop().");
            } else {
                System.out.println("Fail: dragAndDrop() did not register this move as expected. ");
            }
            Thread.sleep(1000);

            //Attempt 2 Manual click hold move release sequence

            WebElement item4 = driver.findElement(By.id("item2"));


            actions.clickAndHold(item2)
                    .moveToElement(sourceContainer)
                    .pause(Duration.ofMillis(300))
                    .release()
                    .build()
                    .perform();

            String result4 = driver.findElement(By.id("result")).getText();
            System.out.println("After manual click hold move release Reverse  : " + result4);

            if (result1.contains("Define Entry and Exit Criteria moved to TO DO")) {
                System.out.println("Pass: Drag and drop worked with manual aActions sequence.");
            } else {
                System.out.println("Fail: Manual Actions Sequence did not registered the move as expected ");
            }









        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            driver.quit();
        }
    }
    }
