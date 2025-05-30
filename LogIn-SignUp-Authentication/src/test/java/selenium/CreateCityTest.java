package selenium;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class CreateCityTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

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

            // Click Add button
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='Add']")));
            js.executeScript("arguments[0].click();", addButton);

            // Fill city form
            String cityName = "TestCity_" + System.currentTimeMillis();
            WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@name='name']")));
            nameInput.sendKeys(cityName);

            // Save
            WebElement saveButton = driver.findElement(
                    By.xpath("//button[@title='Save']"));
            saveButton.click();

            // Verify
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//td[contains(text(),'" + cityName + "')]")));
            System.out.println("✅ City created successfully: " + cityName);
        } catch (Exception e) {
            System.err.println("❌ City creation failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}