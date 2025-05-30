package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class ResetPasswordTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Go to forgot password page
            driver.get("http://localhost:3000/forgot-password");

            // Request password reset
            WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
            emailInput.sendKeys("user@example.com");

            WebElement requestButton = driver.findElement(By.xpath("//button[contains(text(), 'Send Code')]"));
            requestButton.click();

            // Verify code sent message
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[contains(@class, 'message')]"),
                    "Verification code sent to your email."));

            // Go to reset password page
            driver.get("http://localhost:3000/reset-password");

            // Fill reset form
            driver.findElement(By.id("email")).sendKeys("user@example.com");
            driver.findElement(By.id("code")).sendKeys("123456"); // Replace with actual code
            driver.findElement(By.id("newPassword")).sendKeys("NewPassword123!");
            driver.findElement(By.id("confirmPassword")).sendKeys("NewPassword123!");

            // Submit
            WebElement resetButton = driver.findElement(By.xpath("//button[contains(text(), 'Reset Password')]"));
            resetButton.click();

            // Verify success
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[contains(@class, 'success')]"),
                    "Password changed successfully."));

            System.out.println("✅ Password reset successful!");
        } catch (Exception e) {
            System.err.println("❌ Password reset failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}