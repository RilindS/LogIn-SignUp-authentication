package selenium;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TwoFactorAuthTest {
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
            wait.until(ExpectedConditions.urlContains("/dashboard"));

            driver.get("http://localhost:3000/profile");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Profile')]")));


            WebElement toggle2FA = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@type='checkbox' and @name='twoFactorEnabled']")));
            if (!toggle2FA.isSelected()) {
                toggle2FA.click();
            }

            WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(), 'Save')]"));
            saveButton.click();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[contains(@class, 'success')]"),
                    "Two-Factor Authentication enabled successfully."));

            System.out.println("✅ 2FA enabled successfully");

            toggle2FA.click();
            saveButton.click();


            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[contains(@class, 'success')]"),
                    "Two-Factor Authentication disabled successfully."));

            System.out.println("✅ 2FA disabled successfully");
        } catch (Exception e) {
            System.err.println("❌ 2FA test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}