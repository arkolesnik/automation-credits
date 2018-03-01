package test_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.DriverUtils;

public class DriverFixture {

    private static final String URL = "https://www.credible.com//?optimizely_disable=true";
    private DriverUtils driverUtils = new DriverUtils();

    WebDriver driver;
    //WebDriverWait wait;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        driver = driverUtils.getDriver();
        //wait = new WebDriverWait(driver, 15);
        driver.get(URL);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
