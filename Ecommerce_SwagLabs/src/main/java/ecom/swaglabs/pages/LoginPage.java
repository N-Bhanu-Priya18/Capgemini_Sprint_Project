package ecom.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    WebElement errorMessage;

    public void enterUn(String value) {
        username.sendKeys(value);
    }

    public void enterPwd(String value) {
        password.sendKeys(value);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void loginAction(String usernameValue, String passwordValue) {
        enterUn(usernameValue);
        enterPwd(passwordValue);
        clickLogin();
    }

    public boolean isUsernameDisplayed() {
        return username.isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        return password.isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public String getUsernameValue() {
        return username.getAttribute("value");
    }

    public String getPasswordValue() {
        return password.getAttribute("value");
    }

    public String getPasswordType() {
        return password.getAttribute("type");
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}