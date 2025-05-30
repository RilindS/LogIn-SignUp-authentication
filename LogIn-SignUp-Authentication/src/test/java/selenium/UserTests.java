package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.annotation.Order;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Order(0)
    void testCreateUser() {
        driver.get("http://localhost:3000/admin/user/create");

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        nameInput.sendKeys("Test User");

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("admin@example.com");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("admin123");

        WebElement roleSelect = driver.findElement(By.name("role"));
        roleSelect.sendKeys("ADMIN");

        WebElement createButton = driver.findElement(By.id("create-user-button"));
        createButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'admin@example.com')]")));
    }

    @Test
    @Order(1)
    void testLogin() {
        driver.get("http://localhost:3000/login");

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailInput.sendKeys("admin@example.com");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("admin123");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        wait.until(ExpectedConditions.urlContains("/admin/profile"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/admin/profile"));
    }

    @Test
    @Order(2)
    void testEditUser() {
        driver.get("http://localhost:3000/user/list");

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(), 'Test')]/following-sibling::td/button[text()='Edit']")));
        editButton.click();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']")));
        emailInput.clear();
        emailInput.sendKeys("test.updated@example.com");

        WebElement saveBtn = driver.findElement(By.xpath("//button[text()='Save']"));
        saveBtn.click();

        WebElement updatedEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'test.updated@example.com')]")));
        Assertions.assertNotNull(updatedEmail);
    }

    @Test
    @Order(3)
    void testDeleteUser() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(), 'test.updated@example.com')]/following-sibling::td/button[text()='Delete']")));
        deleteButton.click();

        boolean isDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[contains(text(), 'test.updated@example.com')]")));
        Assertions.assertTrue(isDeleted);
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }
}
