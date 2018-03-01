package pageobjects;

import components.FragmentFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    private FragmentFactory fragmentFactory = new FragmentFactory();

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public FragmentFactory getFragmentFactory() {
        return fragmentFactory;
    }
}
