package Assignment4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment4 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            Thread.sleep(1000);

            WebElement obj_user = driver.findElement(By.id("user-name"));
            WebElement obj_pass = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.id("login-button"));

            obj_user.sendKeys("Parag");
            obj_pass.sendKeys("Parag");



            Thread.sleep(1000);

            loginBtn.click();

            Thread.sleep(1000);

            if (driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed()) {
                System.out.println("Error SadFace Message on the login page");
            } else {
                System.out.println("Login fail");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}