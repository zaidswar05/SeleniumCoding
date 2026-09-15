package LoginSauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginsaucedemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            Thread.sleep(10000);

            WebElement obj_user = driver.findElement(By.id("user-name"));
            WebElement obj_pass = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.id("login-button"));

            obj_user.sendKeys("standard_user");
            obj_pass.sendKeys("secret_sauce");

            Thread.sleep(1000);

            loginBtn.click();

            Thread.sleep(1000);

            if(driver.getCurrentUrl().contains("inventory.html")){
                System.out.println("Login Success");
            }else
                System.out.println("Login fail");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            driver.quit();
        }
    }
}
