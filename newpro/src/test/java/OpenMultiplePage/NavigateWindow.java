package OpenMultiplePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateWindow {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();


            driver.get("https://www.saucedemo.com/");
            System.out.println("Step 1 - Loaded via get():" + driver.getTitle());
            System.out.println("Handle after get():" + driver.getWindowHandle());
            Thread.sleep(2000);


            driver.navigate().to("https://www.google.com/");
            System.out.println("Step 2 - Navigate via navigate().to():" + driver.getTitle());
            System.out.println("Handle after navigate().to():" + driver.getWindowHandle());
            Thread.sleep(2000);

            driver.navigate().to("https://www.amazon.com/");
            System.out.println("Step 3 - Navigate via navigate().to():" + driver.getTitle());
            System.out.println("Handle after navigate().to():" + driver.getWindowHandle());
            Thread.sleep(2000);

            driver.navigate().back();
            System.out.println("Step 4 - After back():" + driver.getTitle());
            System.out.println("Handle after Navigating back():" + driver.getWindowHandle());
            Thread.sleep(2000);

            driver.navigate().back();
            System.out.println("Step 5 - After back():" + driver.getTitle());
            System.out.println("Handle after Navigating back():" + driver.getWindowHandle());
            Thread.sleep(2000);

            driver.navigate().refresh();
            System.out.println("Step 6 - After refresh():" + driver.getTitle());
            System.out.println("Handle after Navigating back():" + driver.getWindowHandle());
            Thread.sleep(2000);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            driver.quit();
        }

    }
}