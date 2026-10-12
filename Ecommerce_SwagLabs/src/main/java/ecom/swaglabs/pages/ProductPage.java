package ecom.swaglabs.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    WebElement swagLabsLogo;

    @FindBy(tagName = "select")
    WebElement sortDropdown;

    @FindBy(css = "[data-test='title']")
    WebElement productsTitle;

    @FindBy(css = "[data-test='shopping-cart-link']")
    WebElement cartIcon;

    @FindBy(xpath = "//div[@data-test='inventory-item']")
    List<WebElement> products;

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    List<WebElement> productNames;

    @FindBy(xpath = "//div[@data-test='inventory-item-price']")
    List<WebElement> productPrices;

    @FindBy(css = "[data-test^='add-to-cart-']")
    List<WebElement> addToCartButtons;

    @FindBy(css = "[data-test^='inventory-item-'][data-test$='-img']")
    List<WebElement> productImages;

    @FindBy(xpath = "//div[@data-test='inventory-item-desc']")
    List<WebElement> productDescriptions;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    public boolean isLogoDisplayed() {
        return swagLabsLogo.isDisplayed();
    }

    public String getProductsTitle() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='title']")))
                .getText();
    }

    public void selectSortOption(String option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    public boolean isSortOptionAvailable(String option) {
        Select select = new Select(sortDropdown);
        for (WebElement element : select.getOptions()) {
            if (element.getText().equals(option)) {
                return true;
            }
        }
        return false;
    }

    public void clickCart() {
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(By.xpath("//div[@data-test='inventory-item']"));
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(By.xpath("//div[@data-test='inventory-item-name']"));
    }

    public List<WebElement> getProductPrices() {
        return driver.findElements(By.xpath("//div[@data-test='inventory-item-price']"));
    }

    public List<WebElement> getAddToCartButtons() {
        return driver.findElements(By.cssSelector("[data-test^='add-to-cart-']"));
    }

    public List<WebElement> getProductImages() {
        return driver.findElements(By.cssSelector("[data-test^='inventory-item-'][data-test$='-img']"));
    }

    public List<WebElement> getProductDescriptions() {
        return driver.findElements(By.xpath("//div[@data-test='inventory-item-desc']"));
    }

    public String getCartCount() {
        if (driver.findElements(By.className("shopping_cart_badge")).isEmpty()) {
            return "0";
        }
        return driver.findElement(By.className("shopping_cart_badge")).getText();
    }

    public void addProductToCart(String productName) {
        for (WebElement item : driver.findElements(By.xpath("//div[@data-test='inventory-item']"))) {
            WebElement name = item.findElement(By.cssSelector("[data-test='inventory-item-name']"));
            if (name.getText().equals(productName)) {
                item.findElement(By.cssSelector("button[data-test^='add-to-cart-']")).click();
                break;
            }
        }
    }

    public String getProductPrice(String productName) {
        List<WebElement> names = getProductNames();
        List<WebElement> prices = getProductPrices();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(productName)) {
                return prices.get(i).getText();
            }
        }
        return null;
    }

    public String getProductDescription(String productName) {
        List<WebElement> names = getProductNames();
        List<WebElement> descriptions = getProductDescriptions();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(productName)) {
                return descriptions.get(i).getText();
            }
        }
        return null;
    }

    public boolean isProductDisplayed(String productName) {
        for (WebElement product : getProductNames()) {
            if (product.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAddToCartButtonDisplayed(String productName) {
        for (WebElement item : driver.findElements(By.xpath("//div[@data-test='inventory-item']"))) {
            if (item.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText().equals(productName)) {
                return item.findElement(By.cssSelector("button[data-test^='add-to-cart-']")).isDisplayed();
            }
        }
        return false;
    }
}