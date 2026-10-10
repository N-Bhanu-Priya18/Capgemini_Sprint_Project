package ecom.swaglabs.tests.hamburgermenu;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.LoginBaseClass;

public class HamburgerMenuInvalidTest extends LoginBaseClass {

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_049_menuNotAccessibleAfterLogout() throws IOException {
        Reporter.log("TC_049: Verifying Menu is not accessible after logout", true);
        menu.clickMenu();
        menu.clickLogout();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
        Assert.assertFalse(driver.findElements(By.id("react-burger-menu-btn")).size() > 0);
        takeScreenshot("TC_049_menuNotAccessibleAfterLogout");
        Reporter.log("TC_049 - Menu Not Accessible After Logout", true);
    }

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_050_menuCannotBeAccessedBeforeLogin() throws IOException {
        Reporter.log("TC_050: Verifying Menu cannot be accessed before login", true);
        menu.clickMenu();
        menu.clickLogout();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
        Assert.assertEquals(driver.findElements(By.id("react-burger-menu-btn")).size(), 0);
        takeScreenshot("TC_050_menuCannotBeAccessedBeforeLogin");
        Reporter.log("TC_050 - Menu Not Accessible Before Login", true);
    }
}