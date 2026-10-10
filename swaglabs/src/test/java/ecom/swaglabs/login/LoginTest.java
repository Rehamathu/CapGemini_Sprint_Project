
package ecom.swaglabs.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseclass.Baseclasss;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;

public class LoginTest extends Baseclasss {

    private ExcelUtility getExcel() {
        return new ExcelUtility();
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void loginWithExcelRow(int row) throws Exception {
        LoginPage lp = new LoginPage(driver);
        ExcelUtility eu = getExcel();

        lp.login(
            eu.readDataFromExcel("LoginCredentials", row, 0),
            eu.readDataFromExcel("LoginCredentials", row, 1)
        );
    }

    // TC-UI-01: Valid login
    @Test(groups = {"SmT", "Pos", "FnT"})
    public void validLoginTest() throws Exception {
        loginWithExcelRow(1);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.className("inventory_list")
                )
            ).isDisplayed(),
            "Product Catalog did not appear after valid login"
        );

        Reporter.log("TC-UI-01: Valid login reaches Product Catalog", true);
    }

    // TC-UI-02: Login page UI visibility
    @Test(groups = {"FnT", "SmT", "Pos"})
    public void loginPageUIVisibilityTest() {
        Assert.assertTrue(driver.findElement(By.id("user-name")).isDisplayed(),
            "Username field is not visible");
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(),
            "Password field is not visible");
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(),
            "Login button is not visible");

        Reporter.log("TC-UI-02: Login page controls are visible", true);
    }

    // TC-UI-03: Invalid credentials
    @Test(groups = {"FnT", "Neg"})
    public void invalidLoginTest() throws Exception {
        loginWithExcelRow(7);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Error was not displayed for invalid credentials"
        );

        Assert.assertFalse(
            driver.getCurrentUrl().contains("inventory"),
            "Invalid login unexpectedly opened Product Catalog"
        );

        Reporter.log("TC-UI-03: Invalid credentials are rejected", true);
    }

    // TC-UI-04: Blank credentials
    @Test(groups = {"FnT", "Neg"})
    public void blankCredentialsTest() throws Exception {
        loginWithExcelRow(8);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Error was not displayed for blank credentials"
        );

        Assert.assertFalse(
            driver.getCurrentUrl().contains("inventory"),
            "Blank credentials unexpectedly opened Product Catalog"
        );

        Reporter.log("TC-UI-04: Blank credentials are rejected", true);
    }

    // TC-UI-05: Regression - valid login still works
    @Test(groups = {"FnT", "RegT", "Pos"})
    public void loginRegressionTest() throws Exception {
        loginWithExcelRow(1);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.className("inventory_list")
                )
            ).isDisplayed(),
            "Valid login regression check failed"
        );

        Reporter.log("TC-UI-05: Valid login regression check passed", true);
    }

    // TC-UI-06: Boundary-value credentials
    // Uses the existing invalid test data as a rejection check.
    // Exact boundary lengths must be defined in the requirements first.
    @Test(groups = {"FnT", "BVT", "Neg"})
    public void loginBoundaryDataRejectionTest() throws Exception {
        loginWithExcelRow(7);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Expected invalid test data to be rejected"
        );

        Reporter.log(
            "TC-UI-06: Invalid test data was rejected; exact boundary limits still require confirmation",
            true
        );
    }

    // TC-UI-07: Logout
    @Test(groups = {"FnT", "Pos"})
    public void logoutTest() throws Exception {
        loginWithExcelRow(1);

        getWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_list")
            )
        );

        driver.findElement(By.id("react-burger-menu-btn")).click();

        getWait().until(
            ExpectedConditions.elementToBeClickable(
                By.id("logout_sidebar_link")
            )
        ).click();

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.id("login-button")
                )
            ).isDisplayed(),
            "Login button was not visible after logout"
        );

        Reporter.log("TC-UI-07: Logout returns to login page", true);
    }

    // TC-INT-01: Successful Login -> Product Catalog
    @Test(groups = {"IntT", "Pos"})
    public void loginToCatalogIntegrationTest() throws Exception {
        loginWithExcelRow(1);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.className("inventory_list")
                )
            ).isDisplayed(),
            "Product Catalog did not load after login"
        );

        Assert.assertTrue(
            driver.findElement(By.className("title")).getText()
                .equals("Products"),
            "Product Catalog title was incorrect"
        );

        Reporter.log("TC-INT-01: Login integrates correctly with Catalog", true);
    }

    // TC-INT-02: Failed login must not open Product Catalog
    @Test(groups = {"IntT", "Neg"})
    public void failedLoginCatalogIntegrationTest() throws Exception {
        loginWithExcelRow(7);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Login error was not displayed"
        );

        Assert.assertFalse(
            driver.getCurrentUrl().contains("inventory"),
            "Product Catalog opened after failed authentication"
        );

        Reporter.log(
            "TC-INT-02: Failed login did not open Product Catalog",
            true
        );
    }

    // TC-BVT-01: Minimum length input
    @Test(groups = {"FnT", "BVT", "Neg"})
    public void minimumLengthLoginTest() throws Exception {
        loginWithExcelRow(9);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Expected login rejection for minimum-length test data"
        );

        Reporter.log("TC-BVT-01: Minimum-length input handled", true);
    }

    // TC-BVT-02: Just below minimum length
    @Test(groups = {"FnT", "BVT", "Neg"})
    public void belowMinimumLengthLoginTest() throws Exception {
        loginWithExcelRow(10);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Expected login rejection for below-minimum test data"
        );

        Reporter.log("TC-BVT-02: Below-minimum input handled", true);
    }

    // TC-BVT-03: Maximum length input
    @Test(groups = {"FnT", "BVT", "Neg"})
    public void maximumLengthLoginTest() throws Exception {
        loginWithExcelRow(11);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Expected login rejection for maximum-length test data"
        );

        Reporter.log("TC-BVT-03: Maximum-length input handled", true);
    }

    // TC-BVT-04: Just above maximum length
    @Test(groups = {"FnT", "BVT", "Neg"})
    public void aboveMaximumLengthLoginTest() throws Exception {
        loginWithExcelRow(12);

        Assert.assertTrue(
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).isDisplayed(),
            "Expected login rejection for above-maximum test data"
        );

        Reporter.log("TC-BVT-04: Above-maximum input handled", true);
    }
}
