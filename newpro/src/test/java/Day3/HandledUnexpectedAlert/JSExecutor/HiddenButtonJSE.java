package Day3.HandledUnexpectedAlert.JSExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HiddenButtonJSE {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/hiddenElementJavascriptExecutor.html");
            Thread.sleep(2000);

            //Part 2 Click & hidden element via
            WebElement hiddenBtn = driver.findElement(By.id("hiddenBtn"));
            Thread.sleep(1000);

            //regular click()
            try {
                hiddenBtn.click();
                System.out.println("click() succeeded on hidden element");
            } catch(Exception e) {
                System.out.println("click() failed on hidden element: " + e.getClass().getSimpleName());
            }

            Thread.sleep(1000);

            //use javascriptexecutor
            js.executeScript("arguments[0].click();", hiddenBtn);

            // FIXED: Added missing semicolon at the end of the line
            String hiddenText = (String) js.executeScript("return arguments[0].textContent;", hiddenBtn);
            System.out.println("hidden button text: " + hiddenText);

            Thread.sleep(5000);

            // FIXED: Removed extra parenthesis and changed .getClass() to .getText()
            String result2 = driver.findElement(By.id("result")).getText();

            if(result2.equals("Hey Your treasure will be at your doorstep, wait until TOMORROW")) {
                System.out.println("PASS: Hidden button clicked correctly Result: " + result2);
            } else {
                System.out.println("Fail: Result mismatched. Actual: " + result2);
            }

            Thread.sleep(1000);

        } finally {
            // FIXED: Added a finally block to properly close the outer try statement
            driver.quit();
        }
    }
}