package com.webproject.vswapp_backend.ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginUITest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;
    private String frontendUrl = "http://localhost:5173"; // React frontend

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        baseUrl = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    @DisplayName("Successful login with valid credentials")
    public void testSuccessfulLogin() {
        try {
            // Navigate to login page
            driver.get(frontendUrl + "/login");

            // Wait for page to load completely
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

            // Find login form elements
            WebElement emailInput = driver.findElement(By.id("email"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            // Enter valid credentials
            emailInput.clear();
            emailInput.sendKeys("shashi55@gmail.com");

            passwordInput.clear();
            passwordInput.sendKeys("shashi55");

            // Click login button
            loginButton.click();

            // Wait for redirect away from login page
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

            // Optionally, wait for a main container that always exists after login
            WebElement mainContainer = wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.cssSelector("#root, .app-container"))); // adjust selector
            assertTrue(mainContainer.isDisplayed(), "Main app container should be displayed after login");

            System.out.println("✅ Successful login test passed!");

        } catch (Exception e) {
            System.err.println("❌ Successful login test failed: " + e.getMessage());
            takeScreenshot("failed_login_test");
            throw e;
        }
    }


    private void takeScreenshot(String testName) {
        try {
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                System.out.println("Screenshot taken for test: " + testName);
            }
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
