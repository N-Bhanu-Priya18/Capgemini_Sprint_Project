package ecom.swaglabs.utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ecom.swaglabs.pages.AddToCartPage;
import ecom.swaglabs.pages.CheckoutPageOne;
import ecom.swaglabs.pages.CheckoutPageThree;
import ecom.swaglabs.pages.CheckoutPageTwo;
import ecom.swaglabs.pages.HamburgerMenu;
import ecom.swaglabs.pages.LoginPage;
import ecom.swaglabs.pages.ProductPage;

public class BaseClass extends CommonData {

    protected WebDriver driver = null;
    protected LoginPage login;
    protected ProductPage product;
    protected HamburgerMenu menu;
    protected TestData testData;
    protected AddToCartPage cart;
    protected CheckoutPageOne checkout1;
    protected CheckoutPageTwo checkout2;
    protected CheckoutPageThree checkout3;

    public void takeScreenshot(String testName) throws IOException {
        File folder = new File(System.getProperty("user.dir"), "screenshot");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(folder, testName + ".png");
        FileHandler.copy(src, dest);
    }

    @BeforeSuite(groups={"smoke","functional","integration","system","regression","negative"})
    public void openDatabaseConnectivity() {
        Reporter.log("Open DataBase Connectivity", true);
    }

    @AfterSuite(groups={"smoke","functional","integration","system","regression","negative"})
    public void closeDatabaseConnectivity() {
        Reporter.log("Close DataBase Connectivity", true);
    }

    @BeforeTest(groups={"smoke","functional","integration","system","regression","negative"})
    public void preConditions() {
        Reporter.log("Pre-conditions", true);
    }

    @AfterTest(groups={"smoke","functional","integration","system","regression","negative"})
    public void postConditions() {
        Reporter.log("Post-conditions", true);
    }

    @BeforeClass(groups={"smoke","functional","integration","system","regression","negative"}, alwaysRun=true)
    public void createTestDataObject() throws IOException {
        testData = new TestData();
    }

    @Parameters("browser")
    @BeforeMethod(groups={"smoke","functional","integration","system","regression","negative"}, alwaysRun=true)
    public void launchApplication(@Optional("chrome") String browser) throws IOException {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions settings = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.password_manager_leak_detection", false);
            settings.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(settings);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(getData("url"));

        login = new LoginPage(driver);
        product = new ProductPage(driver);
        menu = new HamburgerMenu(driver);
        cart = new AddToCartPage(driver);
        checkout1 = new CheckoutPageOne(driver);
        checkout2 = new CheckoutPageTwo(driver);
        checkout3 = new CheckoutPageThree(driver);

        Reporter.log("Launch the browser: " + browser, true);
        Reporter.log("Before Method Execution", true);
    }

    @AfterMethod(groups={"smoke","functional","integration","system","regression","negative"}, alwaysRun=true)
    public void closeApplication() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        Reporter.log("After Method Execution", true);
        Reporter.log("Close the browser", true);
    }

    @AfterClass(groups={"smoke","functional","integration","system","regression","negative"}, alwaysRun=true)
    public void closeTestData() throws IOException {
        if (testData != null) {
            testData.closeWorkbook();
            testData = null;
        }
    }
}