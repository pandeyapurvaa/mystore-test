package com.mystore.tests;

import com.aventstack.extentreports.Status;
import com.mystore.base.BaseTest;
import com.mystore.pages.HomePage;
import com.mystore.pages.ProductDetailsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("MyStore Website Testing")
@Feature("Product Details Functionality")
public class ProductDetailsTest extends BaseTest {

    @Test
    @Description("Verify product details are displayed correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Details Verification")
    public void verifyProductDetails() {
        Allure.step("Starting Product Details Test");
        
        HomePage homePage = new HomePage(driver);
        Allure.step("Navigating to homepage");
        homePage.navigateToHomePage();
        
        Allure.step("Clicking Shop Now button");
        homePage.clickShopNow();
        

        
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        String productTitle = productDetailsPage.getProductTitle();
        String productPrice = productDetailsPage.getProductPrice();
        
        Allure.step("Verifying product title: " + productTitle);
        Allure.step("Verifying product price: " + productPrice);
        
        Assert.assertNotNull(productTitle, "Product title should not be null");
        Assert.assertNotNull(productPrice, "Product price should not be null");
        
        Allure.step("Product details verification passed");
    }

    @Test
    @Description("Verify Add to Cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add to Cart Verification")
    public void verifyAddToCart() {
        Allure.step("Starting Add to Cart Test");
        
        HomePage homePage = new HomePage(driver);
        Allure.step("Navigating to homepage");
        homePage.navigateToHomePage();
        
        Allure.step("Clicking Shop Now button");
        homePage.clickShopNow();
        
        
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Allure.step("Clicking Add to Cart button");
        productDetailsPage.clickAddToCart(1);
        

        
        Allure.step("Handling alert");
        String alertText = productDetailsPage.handleAddToCartAlert();
        
        Allure.step("Verifying alert text: " + alertText);
        Assert.assertTrue(alertText.contains("Product added to cart!"), "Alert message verification failed");
        
        Allure.step("Add to Cart verification passed");
    }

    @Test
    @Description("Verify See Details popup functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("See Details Popup Verification")
    public void verifySeeDetailsPopup() {
        Allure.step("Starting See Details Popup Test");
        
        HomePage homePage = new HomePage(driver);
        Allure.step("Navigating to homepage");
        homePage.navigateToHomePage();
        
        Allure.step("Clicking Shop Now button");
        homePage.clickShopNow();

        
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Allure.step("Clicking See Details button");
        productDetailsPage.clickSeeDetails(1);


        Allure.step("Verifying popup visibility");
        Assert.assertTrue(productDetailsPage.isDetailsPopupVisible(), "Details popup is not visible");
        
        Allure.step("Scrolling popup and taking screenshot");
        byte[] screenshot = productDetailsPage.scrollDetailsPopupAndTakeScreenshot();
        
        Allure.step("Verifying screenshot was captured");
        Assert.assertNotNull(screenshot, "Screenshot after scrolling should not be null");
        
    }
    
} 