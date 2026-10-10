package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String userName) {
        username.sendKeys(userName);
    }

    public void enterPassword(String passWord) {
        password.sendKeys(passWord);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void login(String userName, String passWord) {
        enterUsername(userName);
        enterPassword(passWord);
        clickLogin();
    }
}