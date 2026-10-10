package ecom.swaglabs.tests.addtocart;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class AddToCartValidTest extends LoginBaseClass {

    @Test(groups = {"smoke", "functional", "regression", "system"})
    public void TC_021_verifyUserCanAddSingleProductToCart() {
        Reporter.log("TC_021: Verifying user can add single product to Cart", true);
        String productName = testData.getData("AddToCartModule", 1, 0);
        String expectedCount = testData.getData("AddToCartModule", 1, 3);
        product.addProductToCart(productName);
        Assert.assertEquals(product.getCartCount(), expectedCount);
        Reporter.log("TC_021 - Single product added to Cart successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_022_verifyUserCanRemoveProductFromCart() {
        Reporter.log("TC_022: Verifying user can remove product from Cart", true);
        String productName = testData.getData("AddToCartModule", 1, 0);
        product.addProductToCart(productName);
        product.clickCart();
        Assert.assertTrue(cart.isProductPresent(productName));
        cart.clickRemove();
        Assert.assertEquals(cart.getProductNames().size(), 0);
        Reporter.log("TC_022 - Product removed from Cart successfully", true);
    }

    @Test(groups = {"integration", "functional", "regression"})
    public void TC_023_verifyMultipleProductsAreDisplayedInCart() {
        Reporter.log("TC_023: Verifying multiple products are displayed in Cart", true);
        String product1 = testData.getData("AddToCartModule", 1, 0);
        String product2 = testData.getData("AddToCartModule", 2, 0);
        String expectedCount = testData.getData("AddToCartModule", 2, 3);
        product.addProductToCart(product1);
        product.addProductToCart(product2);
        Assert.assertEquals(product.getCartCount(), expectedCount);
        product.clickCart();
        Assert.assertTrue(cart.isProductPresent(product1));
        Assert.assertTrue(cart.isProductPresent(product2));
        Reporter.log("TC_023 - Multiple products displayed in Cart successfully", true);
    }

    @Test(groups = {"integration", "functional", "regression", "system"})
    public void TC_024_verifyProductCanProceedToCheckout() {
        Reporter.log("TC_024: Verifying product can proceed to Checkout", true);
        String productName = testData.getData("AddToCartModule", 1, 0);
        String checkoutUrl = testData.getData("AddToCartModule", 1, 5);
        product.addProductToCart(productName);
        product.clickCart();
        Assert.assertTrue(cart.isProductPresent(productName));
        cart.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains(checkoutUrl));
        Reporter.log("TC_024 - Product proceeded to Checkout successfully", true);
    }

    @Test(groups = {"integration", "functional", "regression"})
    public void TC_025_verifyCartMaintainsProductWhenNavigatingBack() {
        Reporter.log("TC_025: Verifying Cart maintains product when navigating back", true);
        String productName = testData.getData("AddToCartModule", 1, 0);
        String inventoryUrl = testData.getData("AddToCartModule", 1, 4);
        product.addProductToCart(productName);
        product.clickCart();
        Assert.assertTrue(cart.isProductPresent(productName));
        cart.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains(inventoryUrl));
        product.clickCart();
        Assert.assertTrue(cart.isProductPresent(productName));
        Reporter.log("TC_025 - Cart maintained product successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_026_verifyResetAppStateClearsCart() {
        Reporter.log("TC_026: Verifying Reset App State clears Cart", true);
        String productName = testData.getData("AddToCartModule", 1, 0);
        product.addProductToCart(productName);
        Assert.assertEquals(product.getCartCount(), testData.getData("AddToCartModule", 1, 3));
        menu.clickMenu();
        menu.clickResetAppState();
        product.clickCart();
        Assert.assertEquals(cart.getProductNames().size(), 0);
        Reporter.log("TC_026 - Reset App State cleared Cart successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_027_verifyCartBadgeDisplaysCorrectProductCount() {
        Reporter.log("TC_027: Verifying Cart badge displays correct product count", true);
        String product1 = testData.getData("AddToCartModule", 1, 0);
        String product2 = testData.getData("AddToCartModule", 2, 0);
        String expectedCount1 = testData.getData("AddToCartModule", 1, 3);
        String expectedCount2 = testData.getData("AddToCartModule", 2, 3);
        product.addProductToCart(product1);
        Assert.assertEquals(product.getCartCount(), expectedCount1);
        product.addProductToCart(product2);
        Assert.assertEquals(product.getCartCount(), expectedCount2);
        Reporter.log("TC_027 - Cart badge count verified successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_028_verifyCartPageOpensAfterAddingProduct() {
        Reporter.log("TC_028: Verifying Cart page opens after adding product", true);
        String productName = testData.getData("AddToCartModule", 1, 0);
        String cartTitle = testData.getData("AddToCartModule", 1, 2);
        product.addProductToCart(productName);
        product.clickCart();
        Assert.assertEquals(cart.getCartTitle(), cartTitle);
        Assert.assertTrue(cart.isProductPresent(productName));
        Reporter.log("TC_028 - Cart page opened successfully", true);
    }
}