package Day3.HandledUnexpectedAlert.RightClick;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RightClick {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/rightClickContextMenuInteraction.html");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);

            WebElement targetBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("targetBox"))
            );


            actions.contextClick(targetBox).perform();


            WebElement contextMenu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("contextMenu"))
            );

            if (contextMenu.isDisplayed()) {
                System.out.println("Pass: Context menu appeared after right click");
            } else {
                System.out.println("Fail: Context menu did not appear");
            }

            // 3. Click Delete from the context menu
            WebElement deleteOption = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("deleteOption"))

            );
            Thread.sleep(1000);
//            deleteOption.click();
//
//            Thread.sleep(1000);
//            String result = driver.findElement(By.id("result")).getText();
//
//            if (result.equals("You selected: Delete")) {
//                System.out.println("Pass: Delete option Selected correctly & Result - " + result);
//            } else {
//                System.out.println("Fail: Result mismatched Actual. " + result);
//            }


            //------Assignment -- Verify that the menu is cleared when clicked elsewhere------

            WebElement sideClick = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h2")));
            sideClick.click();
            Thread.sleep(1000);
            boolean MenuInvisible = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(By.id("contextMenu"))
            );
            Thread.sleep(1000);
            if (MenuInvisible) {
                System.out.println("Pass: Context menu disappeared after clicking elsewhere.");
            } else {
                System.out.println("Fail: Context menu is still visible.");
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}