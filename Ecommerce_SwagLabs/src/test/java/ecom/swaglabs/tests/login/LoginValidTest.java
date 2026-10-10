package ecom.swaglabs.tests.login;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.BaseClass;

public class LoginValidTest extends BaseClass {

    @Test(groups = {"smoke", "functional", "regression", "system"})
    public void TC_001_validLogin() {
        Reporter.log("TC_001: Verifying login with valid username and password", true);
        String username = testData.getData("LoginModule", 1, 0);
        String password = testData.getData("LoginModule", 1, 1);
        String expectedTitle = testData.getData("LoginModule", 1, 2);
        login.loginAction(username, password);
        Assert.assertEquals(product.getProductsTitle(), expectedTitle);
        Reporter.log("TC_001 Passed: User successfully logged in", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_002_passwordMasked() {
        Reporter.log("TC_002: Verifying password is masked", true);
        String password = testData.getData("LoginModule", 1, 1);
        login.enterPwd(password);
        Assert.assertEquals(login.getPasswordType(), "password");
        Reporter.log("TC_002 Passed: Password is masked", true);
    }

    @Test(groups = {"integration", "regression", "system"})
    public void TC_003_loginAndProductCatalog() {
        Reporter.log("TC_003: Verifying successful login integrates with Product Catalog", true);
        String username = testData.getData("LoginModule", 1, 0);
        String password = testData.getData("LoginModule", 1, 1);
        String expectedTitle = testData.getData("LoginModule", 1, 2);
        login.loginAction(username, password);
        Assert.assertEquals(product.getProductsTitle(), expectedTitle);
        Assert.assertTrue(product.isLogoDisplayed());
        Assert.assertTrue(product.getProducts().size() > 0);
        Reporter.log("TC_003 Passed: Product Catalog is displayed after login", true);
    }

    @Test(groups = {"integration", "functional", "regression"})
    public void TC_004_accessProducts() {
        Reporter.log("TC_004: Verifying logged-in user can access products", true);
        String username = testData.getData("LoginModule", 1, 0);
        String password = testData.getData("LoginModule", 1, 1);
        login.loginAction(username, password);
        Assert.assertTrue(product.getProductNames().size() > 0);
        Assert.assertTrue(product.getProductPrices().size() > 0);
        Assert.assertTrue(product.getProductDescriptions().size() > 0);
        Reporter.log("TC_004 Passed: Product names, prices and descriptions are accessible", true);
    }

    @Test(groups = {"smoke", "functional", "regression", "system"})
    public void TC_005_loginLogout() {
        Reporter.log("TC_005: Verifying complete login and logout flow", true);
        String username = testData.getData("LoginModule", 1, 0);
        String password = testData.getData("LoginModule", 1, 1);
        String expectedTitle = testData.getData("LoginModule", 1, 2);
        login.loginAction(username, password);
        Assert.assertEquals(product.getProductsTitle(), expectedTitle);
        menu.clickMenu();
        menu.clickLogout();
        Assert.assertTrue(login.isUsernameDisplayed());
        Assert.assertTrue(login.isPasswordDisplayed());
        Reporter.log("TC_005 Passed: User logged out successfully", true);
    }

    @Test(groups = {"functional", "regression", "system"})
    public void TC_006_loginAgainAfterLogout() {
        Reporter.log("TC_006: Verifying user can login again after logout", true);
        String username = testData.getData("LoginModule", 1, 0);
        String password = testData.getData("LoginModule", 1, 1);
        String expectedTitle = testData.getData("LoginModule", 1, 2);
        login.loginAction(username, password);
        menu.clickMenu();
        menu.clickLogout();
        Assert.assertTrue(login.isUsernameDisplayed());
        login.loginAction(username, password);
        Assert.assertEquals(product.getProductsTitle(), expectedTitle);
        Reporter.log("TC_006 Passed: User logged in successfully again", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_007_loginPageControls() {
        Reporter.log("TC_007: Verifying essential Login page controls", true);
        Assert.assertTrue(login.isUsernameDisplayed());
        Assert.assertTrue(login.isPasswordDisplayed());
        Assert.assertTrue(login.isLoginButtonDisplayed());
        Reporter.log("TC_007 Passed: Username, Password and Login button are displayed", true);
    }

    @Test(groups = {"functional", "regression"})
    public void TC_008_enterLoginCredentials() {
        Reporter.log("TC_008: Verifying user can enter login credentials", true);
        String username = testData.getData("LoginModule", 1, 0);
        String password = testData.getData("LoginModule", 1, 1);
        login.enterUn(username);
        login.enterPwd(password);
        Assert.assertEquals(login.getUsernameValue(), username);
        Assert.assertEquals(login.getPasswordValue(), password);
        Reporter.log("TC_008 Passed: Login credentials are entered correctly", true);
    }
}