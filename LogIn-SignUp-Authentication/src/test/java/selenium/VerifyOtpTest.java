package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class VerifyOtpTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            driver.get("http://localhost:3000/login");
            driver.findElement(By.id("email")).sendKeys("user@example.com");
            driver.findElement(By.id("password")).sendKeys("password123");
            driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();


            WebElement otpInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("otp")));
            otpInput.sendKeys("123456");

            WebElement verifyButton = driver.findElement(By.xpath("//button[contains(text(), 'Verify')]"));
            verifyButton.click();

            wait.until(ExpectedConditions.urlContains("/dashboard"));
            System.out.println("✅ OTP verification successful!");
        } catch (Exception e) {
            System.err.println("❌ OTP verification failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}