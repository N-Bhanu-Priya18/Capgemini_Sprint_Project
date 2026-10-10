package ecom.swaglabs.tests.checkout;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class CheckoutInvalidTest extends LoginBaseClass {

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_039_firstNameEmpty() throws IOException {
        Reporter.log("TC_039: Verifying Checkout fails when First Name is empty", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        String expectedError = testData.getData("CheckOutModule", 1, 9);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        Assert.assertTrue(checkout1.getErrorMessage().contains(expectedError));
        takeScreenshot("TC_039_firstNameEmpty");
        Reporter.log("TC_039 - Checkout Failed When First Name Is Empty", true);
    }

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_040_postalCodeEmpty() throws IOException {
        Reporter.log("TC_040: Verifying Checkout fails when Postal Code is empty", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String expectedError = testData.getData("CheckOutModule", 1, 10);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.clickContinue();
        Assert.assertTrue(checkout1.getErrorMessage().contains(expectedError));
        takeScreenshot("TC_040_postalCodeEmpty");
        Reporter.log("TC_040 - Checkout Failed When Postal Code Is Empty", true);
    }
}