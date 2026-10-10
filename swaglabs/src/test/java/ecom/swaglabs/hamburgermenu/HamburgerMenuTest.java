

package ecom.swaglabs.hamburgermenu;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass.Baseclasss;
import genericUtility.ExcelUtility;
import objRepo.HamburgerMenuPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

public class HamburgerMenuTest extends Baseclasss {

    public void loginToApplication() throws Exception {

        LoginPage lp = new LoginPage(driver);
        ExcelUtility eu = new ExcelUtility();

        lp.login(
            eu.readDataFromExcel("LoginCredentials", 1, 0),
            eu.readDataFromExcel("LoginCredentials", 1, 1)
        );
    }

    // TC-CAT-07
    @Test(groups = {"FnT", "Pos"})
    public void hamburgerMenuTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        menu.clickMenu();

        Assert.assertTrue(menu.isAllItemsDisplayed());
        Assert.assertTrue(menu.isDynamicCatalogDisplayed());
        Assert.assertTrue(menu.isAboutDisplayed());
        Assert.assertTrue(menu.isLogoutDisplayed());
        Assert.assertTrue(menu.isResetAppStateDisplayed());

        Reporter.log("Hamburger menu displays all required navigation options", true);

        Thread.sleep(10000);
    }

    // TC-CAT-08
    @Test(groups = {"FnT", "Pos"})
    public void allItemsNavigationTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        menu.clickMenu();
        menu.clickAllItems();

        ProductPage pp = new ProductPage(driver);

        Assert.assertTrue(pp.isProductListDisplayed());

        Reporter.log("All Items successfully returns to Product Catalog", true);

        Thread.sleep(10000);
    }

    // TC-CAT-09
    @Test(groups = {"FnT", "Pos"})
    public void dynamicCatalogMenuTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        menu.clickMenu();
        menu.clickDynamicCatalog();

        Assert.assertTrue(menu.isLazyLoadDisplayed());
        Assert.assertTrue(menu.isSpinnerDisplayed());
        Assert.assertTrue(menu.isSliderDisplayed());

        Reporter.log(
            "Dynamic Catalog displays Lazy Load, Spinner and Slider options",
            true
        );

        Thread.sleep(10000);
    }

    // TC-CAT-10
    @Test(groups = {"FnT", "Pos"})
    public void lazyLoadTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        menu.clickMenu();
        menu.clickDynamicCatalog();
        menu.clickLazyLoad();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("dynamic-catalog-lazy-load")
        );

        Reporter.log("Dynamic Catalog Lazy Load page opened successfully", true);

        Thread.sleep(10000);
    }

    // TC-CAT-11
    @Test(groups = {"FnT", "Pos"})
    public void spinnerTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        menu.clickMenu();
        menu.clickDynamicCatalog();
        menu.clickSpinner();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("dynamic-catalog-spinner")
        );

        Reporter.log("Dynamic Catalog Spinner page opened successfully", true);

        Thread.sleep(10000);
    }

    // TC-CAT-12
    @Test(groups = {"FnT", "Pos"})
    public void sliderTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        menu.clickMenu();
        menu.clickDynamicCatalog();
        menu.clickSlider();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("dynamic-catalog-slider")
        );

        Reporter.log("Dynamic Catalog Slider page opened successfully", true);

        Thread.sleep(10000);
    }

 // TC-CAT-13
    @Test(groups = {"FnT", "Pos"})
    public void aboutNavigationTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        String parentWindow = driver.getWindowHandle();

        menu.clickMenu();
        menu.clickAbout();

        Thread.sleep(3000);

        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }

        String aboutUrl = driver.getCurrentUrl();

        Assert.assertTrue(
            aboutUrl.contains("saucelabs.com"),
            "About page did not open Sauce Labs website"
        );

        Reporter.log("About page opened successfully", true);

        Thread.sleep(10000);
    }
    // TC-CAT-14
    @Test(groups = {"FnT", "Pos"})
    public void resetAppStateTest() throws Exception {

        loginToApplication();

        HamburgerMenuPage menu = new HamburgerMenuPage(driver);

        menu.clickMenu();
        menu.clickResetAppState();

        ProductPage pp = new ProductPage(driver);

        Assert.assertTrue(pp.isProductListDisplayed());

        Reporter.log(
            "Reset App State executed successfully without application error",
            true
        );

        Thread.sleep(10000);
    }
}