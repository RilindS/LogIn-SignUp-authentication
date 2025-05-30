package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class UserLoginTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("http://localhost:3000/login");

            // Fill login form
            WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
            WebElement passwordInput = driver.findElement(By.id("password"));
            emailInput.sendKeys("user@example.com");
            passwordInput.sendKeys("password123");

            // Submit form
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();

            // Verify successful login
            wait.until(ExpectedConditions.urlContains("/dashboard"));
            System.out.println("✅ User login successful!");
        } catch (Exception e) {
            System.err.println("❌ User login failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}