package ecom.swaglabs.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageTwo {

    WebDriver driver;

    public CheckoutPageTwo(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[text()='Checkout: Overview']")
    WebElement checkoutOverviewTitle;

    @FindBy(xpath="//div[@class='cart_quantity']")
    List<WebElement> quantity;

    @FindBy(xpath="//div[@class='inventory_item_name']")
    List<WebElement> productNames;

    @FindBy(xpath="//div[@class='inventory_item_desc']")
    List<WebElement> productDescriptions;

    @FindBy(xpath="//div[@class='inventory_item_price']")
    List<WebElement> productPrices;

    @FindBy(xpath="//div[@class='summary_info_label']")
    List<WebElement> summaryLabels;

    @FindBy(xpath="//div[@class='summary_value_label']")
    List<WebElement> summaryValues;

    @FindBy(xpath="//div[@class='summary_subtotal_label']")
    WebElement itemTotal;

    @FindBy(xpath="//div[@class='summary_tax_label']")
    WebElement tax;

    @FindBy(xpath="//div[@class='summary_total_label']")
    WebElement total;

    @FindBy(id="cancel")
    WebElement cancelButton;

    @FindBy(id="finish")
    WebElement finishButton;


    public String getCheckoutOverviewTitle() {
        return checkoutOverviewTitle.getText();
    }

    public List<WebElement> getQuantity() {
        return quantity;
    }

    public List<WebElement> getProductNames() {
        return productNames;
    }

    public List<WebElement> getProductDescriptions() {
        return productDescriptions;
    }

    public List<WebElement> getProductPrices() {
        return productPrices;
    }

    public List<WebElement> getSummaryLabels() {
        return summaryLabels;
    }

    public List<WebElement> getSummaryValues() {
        return summaryValues;
    }

    public String getItemTotal() {
        return itemTotal.getText();
    }

    public String getTax() {
        return tax.getText();
    }

    public String getTotal() {
        return total.getText();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public void clickFinish() {
        finishButton.click();
    }
}