package objRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    WebDriver driver;

    @FindBy(className = "inventory_list")
    private WebElement productList;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductListDisplayed() {
        return productList.isDisplayed();
    }

    public void sortNameAToZ() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Name (A to Z)");
    }

    public void sortNameZToA() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Name (Z to A)");
    }

    public void sortPriceLowToHigh() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price (low to high)");
    }

    public void sortPriceHighToLow() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price (high to low)");
    }

    public boolean isPriceLowToHigh() {

        List<Double> prices = new ArrayList<>();

        for (WebElement price : productPrices) {
            prices.add(Double.parseDouble(
                price.getText().replace("$", "")
            ));
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPriceHighToLow() {

        List<Double> prices = new ArrayList<>();

        for (WebElement price : productPrices) {
            prices.add(Double.parseDouble(
                price.getText().replace("$", "")
            ));
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean isNameAToZ() {

        List<String> names = new ArrayList<>();

        for (WebElement name : productNames) {
            names.add(name.getText());
        }

        for (int i = 0; i < names.size() - 1; i++) {
            if (names.get(i).compareToIgnoreCase(names.get(i + 1)) > 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isNameZToA() {

        List<String> names = new ArrayList<>();

        for (WebElement name : productNames) {
            names.add(name.getText());
        }

        for (int i = 0; i < names.size() - 1; i++) {
            if (names.get(i).compareToIgnoreCase(names.get(i + 1)) < 0) {
                return false;
            }
        }

        return true;
    }
}