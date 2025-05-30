package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class UpdateCityTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Login
            driver.get("http://localhost:3000/admin/sign-in");
            driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
            driver.findElement(By.id("password")).sendKeys("admin");
            driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]")).click();
            wait.until(ExpectedConditions.urlContains("/admin/dashboard"));

            // Navigate to Cities page
            driver.get("http://localhost:3000/admin/cities");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Cities')]")));

            // Click edit button on first row
            WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//tbody/tr[1]//button[@title='Edit']")));
            editBtn.click();

            // Update city name
            String updatedName = "UpdatedCity_" + System.currentTimeMillis();
            WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@name='name']")));
            nameInput.clear();
            nameInput.sendKeys(updatedName);

            // Click Save
            WebElement saveButton = driver.findElement(By.xpath("//button[@title='Save']"));
            saveButton.click();

            // Verify update
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//td[contains(text(),'" + updatedName + "')]")));
            System.out.println("✅ City updated successfully");

        } catch (Exception e) {
            System.err.println("❌ City update failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
