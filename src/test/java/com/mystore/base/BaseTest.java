package com.mystore.base;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        Allure.step("Setting up WebDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Allure.step("Test Failed");
            Allure.step("Failure Reason: " + result.getThrowable().getMessage());
            takeScreenshot("Failure Screenshot");
        }
        
        Allure.step("Closing browser");
        if (driver != null) {
            driver.quit();
        }
    }

    protected void takeScreenshot(String name) {
        Allure.step("Taking screenshot: " + name);
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, "text/plain", "Screenshot captured");
        } catch (Exception e) {
            Allure.addAttachment(name, "text/plain", "Failed to capture screenshot: " + e.getMessage());
        }
    }

    protected void logStep(String step, String details) {
        Allure.step(step + ": " + details);
        takeScreenshot(step);
    }
} 