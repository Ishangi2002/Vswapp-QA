package com.webproject.vswapp_backend.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CreateSkillUITest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait wait;
    private String frontendUrl = "http://localhost:5173";

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    @Order(1)
    @DisplayName("Add skill")
    public void testLoginAndAddSkill() {
        try {
            // ----------- LOGIN VIA UI -----------
            System.out.println("Navigating to login page...");
            driver.get(frontendUrl + "/login");

            WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            emailInput.clear();
            emailInput.sendKeys("shashi55@gmail.com"); // userId=28 email
            passwordInput.clear();
            passwordInput.sendKeys("shashi55");       // userId=28 password
            loginButton.click();

            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
            System.out.println("Login successful! Current URL: " + driver.getCurrentUrl());

            // ----------- ADD SKILL VIA BACKEND API -----------
            System.out.println("Sending POST request to backend to create skill...");

            String backendUrl = "http://localhost:" + port + "/api/skill";

            Map<String, Object> skill = new HashMap<>();
            skill.put("title", "Selenium Testing");
            skill.put("category", "Testing");
            skill.put("level", "Intermediate");
            skill.put("about", "I am practicing automated UI testing with Selenium.");
            skill.put("userId", 28);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Optional: include session cookie if backend requires it
            // headers.add("Cookie", "JSESSIONID=" + getSessionIdFromSelenium());

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(skill, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(backendUrl, request, String.class);
            assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Skill should be saved successfully in DB");

            System.out.println("Skill created successfully in DB!");

        } catch (Exception e) {
            System.err.println("Test failed with error: " + e.getMessage());
            takeScreenshot("login_and_add_skill_db_failure");
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }

    private void takeScreenshot(String testName) {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
