package com.mystore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".product-card")
    private WebElement firstProduct;

    @FindBy(xpath = "//a[text()='Shop Now']")
    private WebElement shopNowButton;

    @FindBy(xpath = "//input[@id='searchInput']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@id='productsContainer']")
    private List<WebElement> searchResults;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://pandeyapurvaa.github.io/myshop/");
        wait.until(ExpectedConditions.visibilityOf(shopNowButton));
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public void clickShopNow() {
        wait.until(ExpectedConditions.elementToBeClickable(shopNowButton)).click();
    }

    public void searchProduct(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(searchText);
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public String getSearchResultText(int index) {
        return searchResults.get(index).getText();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
} 