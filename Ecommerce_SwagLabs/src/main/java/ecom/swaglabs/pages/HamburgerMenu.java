package ecom.swaglabs.pages;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgerMenu {

    WebDriver driver;

    public HamburgerMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id="react-burger-cross-btn")
    WebElement closeMenuButton;

    @FindBy(id="inventory_sidebar_link")
    WebElement allItems;

    @FindBy(id="dynamic_catalog_sidebar_link")
    WebElement dynamicCatalog;

    @FindBy(id="dynamic_catalog_lazy_load_link")
    WebElement lazyLoad;

    @FindBy(id="dynamic_catalog_spinner_link")
    WebElement spinner;

    @FindBy(id="dynamic_catalog_slider_link")
    WebElement slider;

    @FindBy(id="about_sidebar_link")
    WebElement about;

    @FindBy(id="logout_sidebar_link")
    WebElement logout;

    @FindBy(id="reset_sidebar_link")
    WebElement resetAppState;

    @FindBy(css=".bm-menu-wrap")
    WebElement menuWrap;

    public void clickMenu() {
        menuButton.click();
    }

    public void closeMenu() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.id("react-burger-cross-btn")));
    }

    public void clickAllItems() {
        driver.findElement(By.id("inventory_sidebar_link")).click();
    }

    public void clickDynamicCatalog() {
        driver.findElement(By.id("dynamic_catalog_sidebar_link")).click();
    }

    public void clickLazyLoad() {
        WebElement element = driver.findElement(By.id("dynamic_catalog_lazy_load_link"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    public void clickSpinner() {
        driver.findElement(By.id("dynamic_catalog_spinner_link")).click();
    }

    public void clickSlider() {
        driver.findElement(By.id("dynamic_catalog_slider_link")).click();
    }

    public void clickAbout() {
        driver.findElement(By.id("about_sidebar_link")).click();
    }

    public void clickLogout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    public void clickResetAppState() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link"))).click();
    }

    public boolean isMenuOpen() {
        return menuWrap.isDisplayed() && "false".equals(menuWrap.getAttribute("aria-hidden"));
    }

    public boolean isDynamicCatalogExpanded() {
        return "true".equals(driver.findElement(By.id("dynamic_catalog_sidebar_link")).getAttribute("aria-expanded"));
    }
}