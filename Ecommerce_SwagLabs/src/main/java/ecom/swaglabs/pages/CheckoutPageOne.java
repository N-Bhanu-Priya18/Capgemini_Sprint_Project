package ecom.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageOne {

    WebDriver driver;

    public CheckoutPageOne(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[text()='Checkout: Your Information']")
    WebElement checkoutInformationTitle;

    @FindBy(id="first-name")
    WebElement firstName;

    @FindBy(id="last-name")
    WebElement lastName;

    @FindBy(id="postal-code")
    WebElement postalCode;

    @FindBy(id="cancel")
    WebElement cancelButton;

    @FindBy(id="continue")
    WebElement continueButton;

    @FindBy(xpath="//div[contains(@class,'error-message-container')]")
    WebElement errorMessage;

    public String getCheckoutInformationTitle() {
        return checkoutInformationTitle.getText();
    }

    public void enterFirstName(String value) {
        firstName.sendKeys(value);
    }

    public void enterLastName(String value) {
        lastName.sendKeys(value);
    }

    public void enterPostalCode(String value) {
        postalCode.sendKeys(value);
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}