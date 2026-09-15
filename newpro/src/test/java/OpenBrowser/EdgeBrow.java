package OpenBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeBrow {

    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            Thread.sleep(2000);

            System.out.println(driver.getTitle());

            driver.get("https://www.google.com/");

            Thread.sleep(2000);

            System.out.println(driver.getTitle());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            driver.quit();
        }

    }
}