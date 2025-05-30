package selenium;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class DeleteCityTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Login first
            driver.get("http://localhost:3000/admin/sign-in");
            driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
            driver.findElement(By.id("password")).sendKeys("admin");
            driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]")).click();
            wait.until(ExpectedConditions.urlContains("/admin/dashboard"));

            // Navigate to cities page
            driver.get("http://localhost:3000/admin/cities");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Cities')]")));

            // Find and click delete button for first city
            WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//tbody/tr[1]//button[@title='Delete']")));
            deleteBtn.click();

            // Confirm deletion
            WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Confirm')]")));
            confirmBtn.click();

            // Verify
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//tbody/tr[1]")));
            System.out.println("✅ City deleted successfully");
        } catch (Exception e) {
            System.err.println("❌ City deletion failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}