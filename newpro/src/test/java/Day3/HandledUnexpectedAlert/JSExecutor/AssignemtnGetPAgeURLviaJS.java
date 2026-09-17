package Day3.HandledUnexpectedAlert.JSExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssignemtnGetPAgeURLviaJS {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.manage().window().maximize(); // Maximize window to see the scroll clearly
            driver.get("file:///C:/Users/ccst/Desktop/Selenium/SeleniumMaterial/hiddenElementJavascriptExecutor.html");
            Thread.sleep(2000);

            String title = (String) js.executeScript("return document.title;");
            System.out.println(title);

            WebElement scroll = driver.findElement(By.id("scrollTargetBtn"));
            js.executeScript("arguments[0].scrollIntoView(true);", scroll);

            if(scroll.isDisplayed()){
                System.out.println("Treasure button is Visible");
            }else {
                System.out.println("Treasure button is Not Visible");
            }

            Thread.sleep(3000);
            scroll.click();
            Thread.sleep(3000);
            String result = driver.findElement(By.id("result")).getText();
            System.out.println(result);

        } finally {
            driver.quit();
        }
    }
}