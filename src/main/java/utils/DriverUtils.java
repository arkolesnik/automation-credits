package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverUtils {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (isDriverAlive()) {
            return driver.get();
        }
        ChromeOptions options = new ChromeOptions();
        setOptionsForChrome(options);
        driver.set(new ChromeDriver(options));
        return driver.get();
    }

    private boolean isDriverAlive() {
        return driver.get() != null;
    }

    private static void setOptionsForChrome(ChromeOptions options) {
        options.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check " +
                "--disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
    }
}
