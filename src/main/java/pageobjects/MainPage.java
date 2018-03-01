package pageobjects;

import fragments.FooterFragment;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageObjectUtils;

import java.net.URL;
import java.util.*;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public FooterFragment getFooterFragment() {
        return getFragmentFactory().createFragment(FooterFragment.class);
    }

}
