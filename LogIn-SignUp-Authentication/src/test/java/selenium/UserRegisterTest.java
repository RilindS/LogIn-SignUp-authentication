package selenium;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.UUID;

public class UserRegisterTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("http://localhost:3000/register");

            // Generate unique test data
            String randomEmail = "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

            // Fill registration form
            wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys("Test");
            driver.findElement(By.id("lastName")).sendKeys("User");
            driver.findElement(By.id("email")).sendKeys(randomEmail);
            driver.findElement(By.id("password")).sendKeys("TestPassword123!");

            // Submit form
            WebElement registerButton = driver.findElement(By.xpath("//button[contains(text(), 'Register')]"));
            registerButton.click();

            // Verify OTP page appears
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("otp")));
            System.out.println("✅ User registration successful! OTP verification required.");
        } catch (Exception e) {
            System.err.println("❌ User registration failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}