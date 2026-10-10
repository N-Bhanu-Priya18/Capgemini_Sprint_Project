package ecom.swaglabs.tests.addtocart;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class AddToCartInvalidTest extends LoginBaseClass {

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_029_verifyProductIsNotAddedWithoutClickingAddToCart() throws IOException {
        Reporter.log("TC_029: Verifying product is not added without clicking Add To Cart", true);
        product.clickCart();
        Assert.assertEquals(cart.getProductNames().size(), 0);
        takeScreenshot("TC_029_verifyProductIsNotAddedWithoutClickingAddToCart");
        Reporter.log("TC_029 - Product was not added without clicking Add to Cart", true);
    }

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_030_verifyRemovedProductIsNotDisplayedInCart() throws IOException {
        Reporter.log("TC_030: Verifying removed product is not displayed in Cart", true);
        String productName = testData.getData("AddToCartModule", 1, 0);
        product.addProductToCart(productName);
        product.clickCart();
        Assert.assertTrue(cart.isProductPresent(productName));
        cart.clickRemove();
        Assert.assertFalse(cart.isProductPresent(productName));
        takeScreenshot("TC_030_verifyRemovedProductIsNotDisplayedInCart");
        Reporter.log("TC_030 - Removed product is not displayed in Cart", true);
    }
}