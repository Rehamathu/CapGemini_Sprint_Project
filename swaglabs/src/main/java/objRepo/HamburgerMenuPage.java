

package objRepo;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HamburgerMenuPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;

    @FindBy(linkText = "Dynamic Catalog")
    private WebElement dynamicCatalog;

    @FindBy(id = "about_sidebar_link")
    private WebElement about;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState;

    @FindBy(linkText = "Lazy Load")
    private WebElement lazyLoad;

    @FindBy(linkText = "Spinner")
    private WebElement spinner;

    @FindBy(linkText = "Slider")
    private WebElement slider;

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void clickMenu() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(menuButton));
        js.executeScript("arguments[0].click();", menuButton);
        Thread.sleep(1000);
    }

    public boolean isAllItemsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(allItems)).isDisplayed();
    }

    public boolean isDynamicCatalogDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(dynamicCatalog)).isDisplayed();
    }

    public boolean isAboutDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(about)).isDisplayed();
    }

    public boolean isLogoutDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(logout)).isDisplayed();
    }

    public boolean isResetAppStateDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(resetAppState)).isDisplayed();
    }

    public void clickAllItems() {
        wait.until(ExpectedConditions.visibilityOf(allItems));
        js.executeScript("arguments[0].click();", allItems);
    }

    public void clickDynamicCatalog() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(dynamicCatalog));
        js.executeScript("arguments[0].click();", dynamicCatalog);
        Thread.sleep(1000);
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.visibilityOf(about));
        js.executeScript("arguments[0].click();", about);
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.visibilityOf(logout));
        js.executeScript("arguments[0].click();", logout);
    }

    public void clickResetAppState() {
        wait.until(ExpectedConditions.visibilityOf(resetAppState));
        js.executeScript("arguments[0].click();", resetAppState);
    }

    public boolean isLazyLoadDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(lazyLoad)).isDisplayed();
    }

    public boolean isSpinnerDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(spinner)).isDisplayed();
    }

    public boolean isSliderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(slider)).isDisplayed();
    }

    public void clickLazyLoad() {
        wait.until(ExpectedConditions.visibilityOf(lazyLoad));
        js.executeScript("arguments[0].click();", lazyLoad);
    }

    public void clickSpinner() {
        wait.until(ExpectedConditions.visibilityOf(spinner));
        js.executeScript("arguments[0].click();", spinner);
    }

    public void clickSlider() {
        wait.until(ExpectedConditions.visibilityOf(slider));
        js.executeScript("arguments[0].click();", slider);
    }
}