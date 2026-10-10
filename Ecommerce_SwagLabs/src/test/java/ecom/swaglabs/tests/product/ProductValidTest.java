package ecom.swaglabs.tests.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class ProductValidTest extends LoginBaseClass {

	@Test(groups = {"smoke", "functional", "regression", "system"})
	public void TC_011_allProducts() {
	    Reporter.log("TC_011: Verifying all products are displayed", true);
	    String expectedTitle = testData.getData("ProductModule", 1, 3);
	    Assert.assertTrue(product.getProducts().size() > 0);
	    Assert.assertEquals(product.getProductsTitle(), expectedTitle);
	    Assert.assertEquals(product.getProductNames().size(), product.getProducts().size());
	    Assert.assertEquals(product.getProductPrices().size(), product.getProducts().size());
	    Assert.assertEquals(product.getProductImages().size(), product.getProducts().size());

	    for (int i = 0; i < product.getProductImages().size(); i++) {
	        String src = product.getProductImages().get(i).getAttribute("src");
	        boolean displayed = product.getProductImages().get(i).isDisplayed();
	        Reporter.log("Image src: " + src + ", Displayed: " + displayed, true);
	        Assert.assertTrue(displayed, "Product image is not displayed: " + src);
	        Assert.assertTrue(src != null && !src.isEmpty(), "Product image source is empty");
	    }

	    Reporter.log("TC_011 - All Products Displayed Passed", true);
	}
	
    @Test(groups = {"functional", "regression"})
    public void TC_012_sortProductsByPrice() {
        Reporter.log("TC_012: Verifying products are sorted by price", true);
        String sortOption = testData.getData("ProductModule", 1, 2);
        product.selectSortOption(sortOption);
        List<String> actual = new ArrayList<>();
        for (WebElement price : product.getProductPrices()) {
            actual.add(price.getText());
        }
        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected, (price1, price2) -> {
            double p1 = Double.parseDouble(price1.replace("$", ""));
            double p2 = Double.parseDouble(price2.replace("$", ""));
            return Double.compare(p1, p2);
        });
        Assert.assertEquals(actual, expected);
        Reporter.log("TC_012 - Products Sorted By Price Low To High Passed", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_013_productInCart() {
        Reporter.log("TC_013: Verifying selected product is added to Cart", true);
        String productName = testData.getData("ProductModule", 1, 0);
        String expectedCartCount = testData.getData("ProductModule", 1, 4);
        product.addProductToCart(productName);
        Assert.assertEquals(product.getCartCount(), expectedCartCount);
        product.clickCart();
        Assert.assertTrue(cart.isProductPresent(productName));
        Reporter.log("TC_013 - Product Displayed In Cart Passed", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_014_priceInCart() {
        Reporter.log("TC_014: Verifying product price is retained in Cart", true);
        String productName = testData.getData("ProductModule", 1, 0);
        String productPrice = product.getProductPrice(productName);
        product.addProductToCart(productName);
        product.clickCart();
        String cartPrice = null;
        for (int i = 0; i < cart.getProductNames().size(); i++) {
            if (cart.getProductNames().get(i).getText().equals(productName)) {
                cartPrice = cart.getProductPrices().get(i).getText();
                break;
            }
        }
        Assert.assertEquals(cartPrice, productPrice);
        Reporter.log("TC_014 - Product Price Retained In Cart Passed", true);
    }

    @Test(groups = {"integration", "functional", "regression"})
    public void TC_015_multipleProducts() {
        Reporter.log("TC_015: Verifying multiple products are added to Cart", true);
        String product1 = testData.getData("ProductModule", 1, 0);
        String product2 = testData.getData("ProductModule", 2, 0);
        String expectedCartCount = testData.getData("ProductModule", 2, 4);
        product.addProductToCart(product1);
        product.addProductToCart(product2);
        Assert.assertEquals(product.getCartCount(), expectedCartCount);
        Reporter.log("TC_015 - Multiple Products Added To Cart Passed", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_016_selectedProductAddedToCart() {
        Reporter.log("TC_016: Verifying selected product is added to Cart", true);
        String productName = testData.getData("ProductModule", 1, 0);
        String expectedCartCount = testData.getData("ProductModule", 1, 4);
        Assert.assertTrue(product.isProductDisplayed(productName));
        product.addProductToCart(productName);
        Assert.assertEquals(product.getCartCount(), expectedCartCount);
        Reporter.log("TC_016 - Selected Product Added To Cart Passed", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_017_catalog() {
        Reporter.log("TC_017: Verifying Product Catalog loads successfully", true);
        String expectedTitle = testData.getData("ProductModule", 1, 3);
        Assert.assertEquals(product.getProductsTitle(), expectedTitle);
        Assert.assertTrue(product.getProducts().size() > 0);
        Reporter.log("TC_017 - Product Catalog Loads Successfully Passed", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_018_cartButtons() {
        Reporter.log("TC_018: Verifying Add To Cart buttons are available", true);
        List<WebElement> buttons = product.getAddToCartButtons();
        Assert.assertTrue(buttons.size() > 0);
        for (WebElement button : buttons) {
            Assert.assertTrue(button.isDisplayed());
            Assert.assertTrue(button.isEnabled());
        }
        Reporter.log("TC_018 - Add To Cart Buttons Available Passed", true);
    }
}