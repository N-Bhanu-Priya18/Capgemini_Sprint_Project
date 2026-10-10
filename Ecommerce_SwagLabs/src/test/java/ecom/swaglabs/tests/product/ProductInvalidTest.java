package ecom.swaglabs.tests.product;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class ProductInvalidTest extends LoginBaseClass {

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_019_invalidProduct() throws IOException {
        Reporter.log("TC_019: Verifying product is not added without selecting Add To Cart", true);
        String expectedCartCount = testData.getData("ProductModule", 5, 4);
        product.clickCart();
        Assert.assertEquals(cart.getProductNames().size(), Integer.parseInt(expectedCartCount));
        takeScreenshot("TC_019_invalidProduct");
        Reporter.log("TC_019 - Product Not Added Without Selecting Add To Cart Passed", true);
    }

    @Test(groups = {"functional", "regression", "system", "negative"})
    public void TC_020_productNotDuplicatedAfterRefresh() throws IOException {
        Reporter.log("TC_020: Verifying product is not duplicated after refresh", true);
        String productName = testData.getData("ProductModule", 1, 0);
        String expectedCartCount = testData.getData("ProductModule", 1, 4);
        product.addProductToCart(productName);
        Assert.assertEquals(product.getCartCount(), expectedCartCount);
        driver.navigate().refresh();
        Assert.assertEquals(product.getCartCount(), expectedCartCount);
        product.clickCart();
        Assert.assertEquals(cart.getProductNames().size(), Integer.parseInt(expectedCartCount));
        Assert.assertTrue(cart.isProductPresent(productName));
        takeScreenshot("TC_020_productNotDuplicatedAfterRefresh");
        Reporter.log("TC_020 - Product Not Duplicated After Refresh Passed", true);
    }
}