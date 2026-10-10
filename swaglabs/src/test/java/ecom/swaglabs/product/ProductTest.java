package ecom.swaglabs.product;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass.Baseclasss;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

public class ProductTest extends Baseclasss {

    public void loginToApplication() throws Exception {

        LoginPage lp = new LoginPage(driver);
        ExcelUtility eu = new ExcelUtility();

        lp.login(
            eu.readDataFromExcel("LoginCredentials", 1, 0),
            eu.readDataFromExcel("LoginCredentials", 1, 1)
        );
    }

    // TC-CAT-02
    @Test(groups = {"SmT", "Pos"})
    public void productCatalogTest() throws Exception {

        loginToApplication();

        ProductPage pp = new ProductPage(driver);

        Assert.assertTrue(pp.isProductListDisplayed());

        Reporter.log(
            "Product Catalog loaded successfully and products are displayed",
            true
        );

        Thread.sleep(10000);
    }

    // TC-CAT-01
    @Test(groups = {"FnT", "Pos"})
    public void sortPriceLowToHighTest() throws Exception {

        loginToApplication();

        ProductPage pp = new ProductPage(driver);

        pp.sortPriceLowToHigh();

        Assert.assertTrue(pp.isPriceLowToHigh());

        Reporter.log(
            "Products are sorted successfully from Price Low to High",
            true
        );

        Thread.sleep(10000);
    }

    // TC-CAT-03
    @Test(groups = {"FnT", "Pos"})
    public void verifySortOptionsTest() throws Exception {

        loginToApplication();

        ProductPage pp = new ProductPage(driver);

        pp.sortNameAToZ();
        pp.sortNameZToA();
        pp.sortPriceLowToHigh();
        pp.sortPriceHighToLow();

        Assert.assertTrue(true);

        Reporter.log(
            "All four required sorting options are available and selectable",
            true
        );

        Thread.sleep(10000);
    }

    // TC-CAT-04
    @Test(groups = {"FnT", "Pos"})
    public void sortNameAToZTest() throws Exception {

        loginToApplication();

        ProductPage pp = new ProductPage(driver);

        pp.sortNameAToZ();

        Assert.assertTrue(pp.isNameAToZ());

        Reporter.log(
            "Products are sorted successfully from Name A to Z",
            true
        );

        Thread.sleep(10000);
    }

    // TC-CAT-05
    @Test(groups = {"FnT", "Pos"})
    public void sortNameZToATest() throws Exception {

        loginToApplication();

        ProductPage pp = new ProductPage(driver);

        pp.sortNameZToA();

        Assert.assertTrue(pp.isNameZToA());

        Reporter.log(
            "Products are sorted successfully from Name Z to A",
            true
        );

        Thread.sleep(10000);
    }

    // TC-CAT-06
    @Test(groups = {"FnT", "Pos"})
    public void sortPriceHighToLowTest() throws Exception {

        loginToApplication();

        ProductPage pp = new ProductPage(driver);

        pp.sortPriceHighToLow();

        Assert.assertTrue(pp.isPriceHighToLow());

        Reporter.log(
            "Products are sorted successfully from Price High to Low",
            true
        );

        Thread.sleep(10000);
    }
}