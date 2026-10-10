package ecom.swaglabs.utility;

import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

public class LoginBaseClass extends BaseClass {

    @BeforeMethod(groups={"smoke","functional","integration","system","regression","negative"}, alwaysRun=true)
    public void loginToApplication() throws IOException {
        String username = getData("username");
        String password = getData("password");
        login.enterUn(username);
        login.enterPwd(password);
        login.clickLogin();
        Reporter.log("Login Done", true);
    }
}