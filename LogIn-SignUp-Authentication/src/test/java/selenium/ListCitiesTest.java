package selenium;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class ListCitiesTest {
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

            // Verify cities are listed
            int cityCount = driver.findElements(By.xpath("//tbody/tr")).size();
            if (cityCount > 0) {
                System.out.println("✅ " + cityCount + " cities found in the list");
            } else {
                System.out.println("⚠️ No cities found in the list");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to list cities: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}