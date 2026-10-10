package ecom.swaglabs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {

    WebDriver driver;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[text()='Your Cart']")
    WebElement yourCartTitle;

    @FindBy(xpath="//div[@class='cart_quantity']")
    List<WebElement> quantity;

    @FindBy(xpath="//div[@class='inventory_item_name']")
    List<WebElement> productNames;

    @FindBy(xpath="//div[@class='inventory_item_desc']")
    List<WebElement> productDescriptions;

    @FindBy(xpath="//div[@class='inventory_item_price']")
    List<WebElement> productPrices;

    @FindBy(xpath="//button[contains(@id,'remove-')]")
    List<WebElement> removeButtons;

    @FindBy(id="continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id="checkout")
    WebElement checkoutButton;

    @FindBy(xpath="//span[@class='shopping_cart_badge']")
    WebElement cartBadge;

    public String getCartTitle() {
        return yourCartTitle.getText();
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

    public List<WebElement> getRemoveButtons() {
        return removeButtons;
    }

    public String getCartCount() {
        return cartBadge.getText();
    }

    public void clickRemove() {
        removeButtons.get(0).click();
    }

    public void clickContinueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
    }
    public void clickCheckout() {
        checkoutButton.click();
    }

    public boolean isProductPresent(String productName) {
        for (WebElement product : productNames) {
            if (product.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}