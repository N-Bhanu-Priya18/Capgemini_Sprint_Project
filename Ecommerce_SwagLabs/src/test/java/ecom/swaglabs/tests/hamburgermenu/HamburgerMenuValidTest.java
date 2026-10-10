package ecom.swaglabs.tests.hamburgermenu;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class HamburgerMenuValidTest extends LoginBaseClass {

    @Test(groups = {"smoke", "functional", "regression"})
    public void TC_041_menuOpensSuccessfully() {
        Reporter.log("TC_041: Verifying Hamburger Menu opens successfully", true);
        menu.clickMenu();
        Assert.assertEquals(driver.findElements(By.id("inventory_sidebar_link")).size(), 1);
        Reporter.log("TC_041 - Hamburger Menu Opened Successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_042_menuOptionsDisplayed() {
        Reporter.log("TC_042: Verifying Hamburger Menu options are displayed", true);
        menu.clickMenu();
        Assert.assertTrue(menu.isMenuOpen());
        Assert.assertEquals(driver.findElements(By.id("inventory_sidebar_link")).size(), 1);
        Assert.assertEquals(driver.findElements(By.id("dynamic_catalog_sidebar_link")).size(), 1);
        Assert.assertEquals(driver.findElements(By.id("about_sidebar_link")).size(), 1);
        Assert.assertEquals(driver.findElements(By.id("logout_sidebar_link")).size(), 1);
        Assert.assertEquals(driver.findElements(By.id("reset_sidebar_link")).size(), 1);
        Reporter.log("TC_042 - Menu Options Displayed Successfully", true);
    }

    @Test(groups = {"integration", "functional", "regression"})
    public void TC_043_allItemsNavigatesToProductCatalog() {
        Reporter.log("TC_043: Verifying All Items navigates to Product Catalog", true);
        String expectedTitle = testData.getData("HamburgerMenuModule", 1, 2);
        menu.clickMenu();
        menu.clickAllItems();
        Assert.assertEquals(product.getProductsTitle(), expectedTitle);
        Reporter.log("TC_043 - All Items Navigated To Product Catalog", true);
    }

    @Test(groups = {"integration", "functional", "regression"})
    public void TC_044_resetAppStateClearsCart() {
        Reporter.log("TC_044: Verifying Reset App State clears Cart", true);
        String productName = testData.getData("HamburgerMenuModule", 1, 0);
        String expectedQuantity = testData.getData("HamburgerMenuModule", 1, 1);
        product.addProductToCart(productName);
        Assert.assertEquals(product.getCartCount(), expectedQuantity);
        menu.clickMenu();
        menu.clickResetAppState();
        Assert.assertEquals(product.getCartCount(), "0");
        product.clickCart();
        Assert.assertEquals(driver.findElements(By.className("inventory_item_name")).size(), 0);
        Reporter.log("TC_044 - Reset App State Cleared Cart Successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_045_dynamicCatalogOptionsDisplayed() {
        Reporter.log("TC_045: Verifying Dynamic Catalog options are displayed", true);
        menu.clickMenu();
        menu.clickDynamicCatalog();
        Assert.assertTrue(menu.isDynamicCatalogExpanded());
        Reporter.log("TC_045 - Dynamic Catalog Options Displayed Successfully", true);
    }

    @Test(groups = {"smoke", "functional", "regression", "system"})
    public void TC_046_logoutFromMenu() {
        Reporter.log("TC_046: Verifying Logout from Hamburger Menu", true);
        menu.clickMenu();
        menu.clickLogout();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
        Reporter.log("TC_046 - Logout From Menu Successful", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_047_menuAvailableAfterLogin() {
        Reporter.log("TC_047: Verifying Hamburger Menu is available after login", true);
        menu.clickMenu();
        Assert.assertTrue(menu.isMenuOpen());
        Reporter.log("TC_047 - Menu Available After Login", true);
    }

    @Test(groups = {"integration", "functional", "regression"})
    public void TC_048_lazyLoadOption() {
        Reporter.log("TC_048: Verifying Lazy Load option", true);
        String lazyLoadUrl = testData.getData("HamburgerMenuModule", 1, 3);
        menu.clickMenu();
        menu.clickDynamicCatalog();
        menu.clickLazyLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains(lazyLoadUrl));
        Reporter.log("TC_048 - Lazy Load Page Opened Successfully", true);
    }
}