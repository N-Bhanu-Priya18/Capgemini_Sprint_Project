package ecom.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageThree {

    WebDriver driver;

    public CheckoutPageThree(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[contains(@class,'title') and contains(normalize-space(),'Checkout: Complete')]")
    WebElement checkoutCompleteTitle;

    @FindBy(xpath="//h2[text()='Thank you for your order!']")
    WebElement thankYouMessage;

    @FindBy(xpath="//div[@class='complete-text']")
    WebElement orderDispatchedMessage;

    @FindBy(id="back-to-products")
    WebElement backHomeButton;

    @FindBy(xpath="//button[contains(text(),'Generate PDF order')]")
    WebElement generatePdfOrderButton;

    public String getCheckoutCompleteTitle() {
        return checkoutCompleteTitle.getText();
    }

    public String getThankYouMessage() {
        return thankYouMessage.getText();
    }

    public String getOrderDispatchedMessage() {
        return orderDispatchedMessage.getText();
    }

    public void clickBackHome() {
        backHomeButton.click();
    }

    public void clickGeneratePdfOrder() {
        generatePdfOrderButton.click();
    }
}