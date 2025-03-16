package com.mystore.tests;

import com.aventstack.extentreports.Status;
import com.mystore.base.BaseTest;
import com.mystore.pages.HomePage;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("MyStore Website Testing")
@Feature("Homepage Functionality")
public class HomePageTest extends BaseTest {

    @Test
    @Description("Verify the homepage title matches expected value")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Homepage Title Verification")
    public void verifyHomePageTitle() {
        Allure.step("Starting HomePage Title Test");
        
        HomePage homePage = new HomePage(driver);
        Allure.step("Navigating to homepage");
        homePage.navigateToHomePage();

        
        String expectedTitle = "My Store - Home";
        String actualTitle = driver.getTitle();
        
        Allure.step("Expected Title: " + expectedTitle);
        Allure.step("Actual Title: " + actualTitle);
        
        Assert.assertEquals(actualTitle, expectedTitle, "Homepage title verification failed");
        Allure.step("Homepage title verification passed");
    }

    @Test
    @Description("Verify Shop Now button functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Shop Now Button Verification")
    public void verifyShopNowButton() {
        Allure.step("Starting Shop Now Button Test");
        
        HomePage homePage = new HomePage(driver);
        Allure.step("Navigating to homepage");
        homePage.navigateToHomePage();

        
        Allure.step("Clicking Shop Now button");
        homePage.clickShopNow();
        
 
        
        // Verify the page title
        String expectedTitle = "My Store - Products";
        String actualTitle = homePage.getPageTitle();
        
        Allure.step("Expected Title: " + expectedTitle);
        Allure.step("Actual Title: " + actualTitle);
        
        Assert.assertEquals(actualTitle, expectedTitle, "Products page title verification failed");
        Allure.step("Products page title verification passed");
    }

    @Test
    @Description("Verify search functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search Functionality Verification")
    public void verifySearchFunctionality() {
        Allure.step("Starting Search Functionality Test");
        
        HomePage homePage = new HomePage(driver);
        Allure.step("Navigating to homepage");
        homePage.navigateToHomePage();

        Allure.step("Clicking Shop Now button");
        homePage.clickShopNow();
        
        String searchText = "phone";
        Allure.step("Entering search text: " + searchText);
        homePage.searchProduct(searchText);
        
        // Verify search results
        List<WebElement> results = homePage.getSearchResults();
        Allure.step("Number of search results: " + results.size());
        
        // Verify first result contains search text
        String firstResultText = homePage.getSearchResultText(0);
        Allure.step("First result text: " + firstResultText);
        
        Assert.assertTrue(firstResultText.toLowerCase().contains(searchText.toLowerCase()), 
            "Search result does not contain the search text");
        
        Allure.step("Search functionality verification passed");
    }
} 