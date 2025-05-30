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
public class CityTests {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    @Order(1)
    void testCreateCity() {
        driver.get("http://localhost:3000/admin/city");

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Add City')]")));
        addButton.click();

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("name")));
        input.clear();
        input.sendKeys("TestCity");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//form//button[contains(text(), 'Submit') or contains(text(), 'Save')]")));
        submitButton.click();

        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(), 'TestCity')]")));
        Assertions.assertNotNull(row);
    }

    @Test
    @Order(2)
    void testUpdateCity() {
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[contains(text(), 'TestCity')]/following-sibling::td/button[contains(@class, 'edit')]")));
        editButton.click();

        WebElement editInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("name")));
        editInput.clear();
        editInput.sendKeys("UpdatedCity");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//form//button[contains(text(), 'Submit') or contains(text(), 'Save')]")));
        saveButton.click();

        WebElement updated = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(), 'UpdatedCity')]")));
        Assertions.assertNotNull(updated);
    }

    @Test
    @Order(3)
    void testDeleteCity() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[contains(text(), 'UpdatedCity')]/following-sibling::td/button[contains(@class, 'delete')]")));
        deleteButton.click();

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
        }
        boolean isDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//td[contains(text(), 'UpdatedCity')]")));
        Assertions.assertTrue(isDeleted);
    }

    @AfterAll
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}