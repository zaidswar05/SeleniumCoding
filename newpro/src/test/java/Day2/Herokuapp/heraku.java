package Day2.Herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class heraku {

    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            //Dynamic Loding
            driver.get(BASE_URL + "/dynamic_loading/1");

            WebElement finishBeforeClick = driver.findElement(By.id("finish"));
            System.out.println("Hidden before click " + !finishBeforeClick.isDisplayed());

            driver.findElement(By.cssSelector("#start button")).click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
            WebElement finish1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));

            System.out.println("Example 1 result " + finish1.getText());

            //Dynamic Loding
            driver.get(BASE_URL + "/dynamic_loading/2");

            boolean existsBeforeClick = driver.findElements(By.id("finish")).size() > 0;
            System.out.println("Exists before click " + existsBeforeClick);

            driver.findElement(By.cssSelector("#start button")).click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
            WebElement finish2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));

            System.out.println("Example 2 result " + finish2.getText());

            //Addingremoving Element
            driver.get(BASE_URL + "/add_remove_elements/");

            By addButton = By.xpath("//button[text()='Add Element']");
            By deleteButtons = By.cssSelector("#elements button.added-manually");

            int howMany = 5;
            for (int i = 0; i < howMany; i++) {
                driver.findElement(addButton).click();
            }

            List<WebElement> deletes = driver.findElements(deleteButtons);
            System.out.println("Initial Delete buttons added " + deletes.size());


            driver.findElements(deleteButtons).get(0).click();
            driver.findElements(deleteButtons).get(0).click();

            System.out.println("Buttons after removing 2 " + driver.findElements(deleteButtons).size());


            while (!driver.findElements(deleteButtons).isEmpty()) {
                driver.findElements(deleteButtons).get(0).click();
            }

            System.out.println("Remaining Delete buttons " + driver.findElements(deleteButtons).size());

        } catch (Exception e) {
            System.err.println("Test Failed " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}