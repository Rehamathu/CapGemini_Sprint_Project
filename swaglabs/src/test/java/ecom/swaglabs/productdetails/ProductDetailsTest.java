package ecom.swaglabs.productdetails;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass.Baseclasss;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductDetailsPage;

public class ProductDetailsTest extends Baseclasss {

    public void loginToApplication() throws Exception {

        LoginPage lp = new LoginPage(driver);
        ExcelUtility eu = new ExcelUtility();

        lp.login(
            eu.readDataFromExcel("LoginCredentials", 1, 0),
            eu.readDataFromExcel("LoginCredentials", 1, 1)
        );
    }

    // TC-DET-02
    @Test(groups = {"SmT", "Pos"})
    public void productDetailsPageTest() throws Exception {

        loginToApplication();

        driver.get("https://www.saucedemo.com/inventory-item.html?id=4");

        ProductDetailsPage pdp = new ProductDetailsPage(driver);

        Assert.assertTrue(pdp.isProductNameDisplayed());
        Assert.assertTrue(pdp.isProductPriceDisplayed());
        Assert.assertTrue(pdp.isProductDescriptionDisplayed());
        Assert.assertTrue(pdp.isProductImageDisplayed());

        Reporter.log(
            "Product Details page opened successfully with required product information",
            true
        );

        Thread.sleep(10000);
    }

    // TC-DET-01
    @Test(groups = {"FnT", "Pos"})
    public void verifyProductDetailsTest() throws Exception {

        loginToApplication();

        driver.get("https://www.saucedemo.com/inventory-item.html?id=4");

        ProductDetailsPage pdp = new ProductDetailsPage(driver);

        Assert.assertEquals(
            pdp.getProductName(),
            "Sauce Labs Backpack"
        );

        Assert.assertEquals(
            pdp.getProductPrice(),
            "$29.99"
        );

        Assert.assertTrue(
            pdp.isProductDescriptionDisplayed()
        );

        Assert.assertTrue(
            pdp.isProductImageDisplayed()
        );

        Reporter.log(
            "Product Details information is displayed correctly",
            true
        );

        Thread.sleep(10000);
    }

    // TC-INT-03
    @Test(groups = {"IntT", "Neg"})
    public void selectedProductDetailsTest() throws Exception {

        loginToApplication();

        driver.get("https://www.saucedemo.com/inventory-item.html?id=4");

        ProductDetailsPage pdp = new ProductDetailsPage(driver);

        Assert.assertNotEquals(
            pdp.getProductName(),
            "Sauce Labs Bike Light"
        );

        Assert.assertNotEquals(
            pdp.getProductPrice(),
            "$9.99"
        );

        Reporter.log(
            "Selected product does not display incorrect product details",
            true
        );

        Thread.sleep(10000);
    }

    // TC-INT-04
    @Test(groups = {"IntT", "Pos"})
    public void backToProductsTest() throws Exception {

        loginToApplication();

        driver.get("https://www.saucedemo.com/inventory-item.html?id=4");

        ProductDetailsPage pdp = new ProductDetailsPage(driver);

        pdp.clickBackToProducts();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory")
        );

        Reporter.log(
            "Back to Products successfully returns to Product Catalog",
            true
        );

        Thread.sleep(10000);
    }
}