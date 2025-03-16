package com.mystore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ProductDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "h1")
    private WebElement productTitle;

    @FindBy(css = "span[class*='price']")
    private WebElement productPrice;

    @FindBy(css = ".product-card")
    private List<WebElement> productCards;

    @FindBy(css = ".modal-content")
    private WebElement detailsPopup;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void clickAddToCart(int index) {
        WebElement productCard = productCards.get(index - 1);
        WebElement addToCartButton = productCard.findElement(By.xpath(".//button[contains(text(), 'Add to Cart')]"));
        addToCartButton.click();
    }

    public String handleAddToCartAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void clickSeeDetails(int index) {
        WebElement productCard = productCards.get(index - 1);
        WebElement seeDetailsButton = productCard.findElement(By.xpath(".//button[contains(text(), 'See Details')]"));
        seeDetailsButton.click();
    }

    public boolean isDetailsPopupVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(detailsPopup));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public byte[] scrollDetailsPopupAndTakeScreenshot() {
        try {
            // Wait for the popup to be visible first
            wait.until(ExpectedConditions.visibilityOf(detailsPopup));
            
            // Use JavascriptExecutor to scroll the popup content
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", detailsPopup);
            
            // Small delay to allow scrolling animation to complete
            Thread.sleep(500);
            
            // Take screenshot after scrolling
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println("Error while scrolling: " + e.getMessage());
            return null;
        }
    }
    
} 