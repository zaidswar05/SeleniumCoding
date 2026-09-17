package Day3.HandledUnexpectedAlert.JSExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class JSExecutor {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.google.com/");

        try {
            // Cast to Number first to avoid ClassCastException (Long vs Double)
            double zoomLevelBefore = ((Number) js.executeScript("return window.devicePixelRatio;")).doubleValue();
            System.out.println("Current Zoom Ratio Before: " + zoomLevelBefore);

            // Simulate CTRL + Zoom out (SUBTRACT)
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();

            Thread.sleep(1000);

            // Fetch zoom level after zooming out
            double zoomLevelAfter = ((Number) js.executeScript("return window.devicePixelRatio;")).doubleValue();

            // Fixed typo: Print zoomLevelAfter
            System.out.println("Current Zoom Ratio After: " + zoomLevelAfter);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}