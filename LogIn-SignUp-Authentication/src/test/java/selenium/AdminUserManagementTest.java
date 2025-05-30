package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class AdminUserManagementTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Admin login
            driver.get("http://localhost:3000/admin/login");
            driver.findElement(By.id("email")).sendKeys("admin@example.com");
            driver.findElement(By.id("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
            wait.until(ExpectedConditions.urlContains("/admin/dashboard"));

            // Navigate to users management
            driver.get("http://localhost:3000/admin/users");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Users')]")));

            // Test create user
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='Add']")));
            js.executeScript("arguments[0].click();", addButton);

            // Fill user form
            String randomEmail = "newuser_" + System.currentTimeMillis() + "@example.com";
            wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys("New");
            driver.findElement(By.id("lastName")).sendKeys("User");
            driver.findElement(By.id("email")).sendKeys(randomEmail);
            driver.findElement(By.id("password")).sendKeys("Password123!");

            // Save
            WebElement saveButton = driver.findElement(By.xpath("//button[@title='Save']"));
            saveButton.click();

            // Verify creation
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//td[contains(text(),'" + randomEmail + "')]")));
            System.out.println("✅ User created successfully");

            // Test edit user
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//tbody/tr[1]//button[@title='Edit']")));
            editButton.click();

            // Update user
            String updatedName = "Updated_" + System.currentTimeMillis();
            WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
            firstNameInput.clear();
            firstNameInput.sendKeys(updatedName);

            // Save changes
            saveButton.click();

            // Verify update
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//td[contains(text(),'" + updatedName + "')]")));
            System.out.println("✅ User updated successfully");

            // Test delete user
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//tbody/tr[1]//button[@title='Delete']")));
            deleteButton.click();

            // Confirm deletion
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Confirm')]")));
            confirmButton.click();

            // Verify deletion
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//tbody/tr[1]")));
            System.out.println("✅ User deleted successfully");
        } catch (Exception e) {
            System.err.println("❌ User management test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}