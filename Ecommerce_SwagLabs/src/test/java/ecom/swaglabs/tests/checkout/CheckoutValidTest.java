package ecom.swaglabs.tests.checkout;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class CheckoutValidTest extends LoginBaseClass {

    @Test(groups = {"smoke", "functional", "integration", "system", "regression"})
    public void TC_031_enterValidCheckoutInformation() {
        Reporter.log("TC_031: Verifying valid checkout information", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        String expectedTitle = testData.getData("CheckOutModule", 1, 5);
        String checkoutUrl = testData.getData("CheckOutModule", 1, 11);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        Assert.assertEquals(checkout1.getCheckoutInformationTitle(), expectedTitle);
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().contains(checkoutUrl));
        Reporter.log("TC_031 - Valid Checkout Information Entered Successfully", true);
    }

    @Test(groups = {"smoke", "functional", "integration", "system", "regression"})
    public void TC_032_completeOrderFromOverview() {
        Reporter.log("TC_032: Verifying order can be completed from Checkout Overview", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        String expectedOverviewTitle = testData.getData("CheckOutModule", 1, 6);
        String expectedCompleteTitle = testData.getData("CheckOutModule", 1, 7);
        String expectedThankYou = testData.getData("CheckOutModule", 1, 8);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        Assert.assertEquals(checkout2.getCheckoutOverviewTitle(), expectedOverviewTitle);
        checkout2.clickFinish();
        Assert.assertEquals(checkout3.getCheckoutCompleteTitle(), expectedCompleteTitle);
        Assert.assertEquals(checkout3.getThankYouMessage(), expectedThankYou);
        Reporter.log("TC_032 - Order Completed From Checkout Overview Successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_033_productDetailsDisplayedInCheckout() {
        Reporter.log("TC_033: Verifying product details are displayed in Checkout", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String productPrice = testData.getData("CheckOutModule", 1, 1);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        Assert.assertEquals(checkout2.getProductNames().get(0).getText(), productName);
        Assert.assertEquals(checkout2.getProductPrices().get(0).getText(), productPrice);
        Reporter.log("TC_033 - Cart Product Details Displayed In Checkout Overview", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_034_checkoutTotalCalculatedCorrectly() {
        Reporter.log("TC_034: Verifying Checkout total is calculated correctly", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        double itemTotal = Double.parseDouble(checkout2.getItemTotal().replace("Item total: $", ""));
        double tax = Double.parseDouble(checkout2.getTax().replace("Tax: $", ""));
        double total = Double.parseDouble(checkout2.getTotal().replace("Total: $", ""));
        Assert.assertEquals(total, itemTotal + tax, 0.01);
        Reporter.log("TC_034 - Checkout Total Calculated Correctly", true);
    }

    @Test(groups = {"smoke", "functional", "integration", "system", "regression"})
    public void TC_035_completeCheckoutFlow() {
        Reporter.log("TC_035: Verifying complete Checkout flow", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        String expectedOverviewTitle = testData.getData("CheckOutModule", 1, 6);
        String expectedCompleteTitle = testData.getData("CheckOutModule", 1, 7);
        String expectedThankYou = testData.getData("CheckOutModule", 1, 8);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        Assert.assertEquals(checkout2.getCheckoutOverviewTitle(), expectedOverviewTitle);
        checkout2.clickFinish();
        Assert.assertEquals(checkout3.getCheckoutCompleteTitle(), expectedCompleteTitle);
        Assert.assertEquals(checkout3.getThankYouMessage(), expectedThankYou);
        Reporter.log("TC_035 - Complete Checkout Flow Passed", true);
    }

    @Test(groups = {"functional", "integration", "regression"})
    public void TC_036_cancelCheckout() {
        Reporter.log("TC_036: Verifying Checkout can be cancelled", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        String expectedOverviewTitle = testData.getData("CheckOutModule", 1, 6);
        String inventoryUrl = testData.getData("CheckOutModule", 1, 12);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        Assert.assertEquals(checkout2.getCheckoutOverviewTitle(), expectedOverviewTitle);
        checkout2.clickCancel();
        Assert.assertTrue(driver.getCurrentUrl().contains(inventoryUrl));
        Reporter.log("TC_036 - Checkout Cancelled Successfully", true);
    }

    @Test(groups = {"smoke", "functional", "regression"})
    public void TC_037_checkoutInformationPageLoads() {
        Reporter.log("TC_037: Verifying Checkout Information page loads", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String expectedTitle = testData.getData("CheckOutModule", 1, 5);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        Assert.assertEquals(checkout1.getCheckoutInformationTitle(), expectedTitle);
        Reporter.log("TC_037 - Checkout Information Page Loaded Successfully", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_038_checkoutOverviewPageLoads() {
        Reporter.log("TC_038: Verifying Checkout Overview page loads", true);
        String productName = testData.getData("CheckOutModule", 1, 0);
        String firstName = testData.getData("CheckOutModule", 1, 2);
        String lastName = testData.getData("CheckOutModule", 1, 3);
        String postalCode = testData.getData("CheckOutModule", 1, 4);
        String expectedTitle = testData.getData("CheckOutModule", 1, 6);
        product.addProductToCart(productName);
        product.clickCart();
        cart.clickCheckout();
        checkout1.enterFirstName(firstName);
        checkout1.enterLastName(lastName);
        checkout1.enterPostalCode(postalCode);
        checkout1.clickContinue();
        Assert.assertEquals(checkout2.getCheckoutOverviewTitle(), expectedTitle);
        Reporter.log("TC_038 - Checkout Overview Page Loaded Successfully", true);
    }
}