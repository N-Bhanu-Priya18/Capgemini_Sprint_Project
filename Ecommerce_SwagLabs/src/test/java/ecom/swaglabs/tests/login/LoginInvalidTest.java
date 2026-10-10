package ecom.swaglabs.tests.login;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import ecom.swaglabs.utility.BaseClass;

public class LoginInvalidTest extends BaseClass {

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_009_invalidUsername() throws IOException {
        Reporter.log("TC_009: Verifying login fails with invalid username", true);
        String username = testData.getData("LoginModule", 2, 0);
        String password = testData.getData("LoginModule", 2, 1);
        String expectedError = testData.getData("LoginModule", 2, 3);
        login.enterUn(username);
        login.enterPwd(password);
        login.clickLogin();
        Assert.assertTrue(login.getErrorMessage().contains(expectedError));
        takeScreenshot("TC_009_invalidUsername");
        Reporter.log("TC_009 Passed: Appropriate error message is displayed", true);
    }

    @Test(groups = {"functional", "regression", "negative"})
    public void TC_010_lockedOutUser() throws IOException {
        Reporter.log("TC_010: Verifying locked out user cannot login", true);
        String username = testData.getData("LoginModule", 3, 0);
        String password = testData.getData("LoginModule", 3, 1);
        String expectedError = testData.getData("LoginModule", 3, 3);
        login.enterUn(username);
        login.enterPwd(password);
        login.clickLogin();
        Assert.assertTrue(login.getErrorMessage().contains(expectedError));
        takeScreenshot("TC_010_lockedOutUser");
        Reporter.log("TC_010 Passed: Locked out user is prevented from login", true);
    }
}