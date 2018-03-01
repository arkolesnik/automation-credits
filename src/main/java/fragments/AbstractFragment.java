package fragments;

import components.FragmentFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverUtils;

/**
 * Created by olena.kolesnyk on 14/02/2018.
 */
public abstract class
    AbstractFragment {

    private FragmentFactory fragmentFactory = new FragmentFactory();
    private WebElement rootElement;
    protected DriverUtils driverUtils = new DriverUtils();

    public AbstractFragment() {
        PageFactory.initElements(driverUtils.getDriver(), this);
        initializeFragment();
    }

    protected void initializeFragment() {
        // Should be overridden if necessary in child
    }

    protected WebElement getRootElement() {
        return rootElement;
    }

    public void setRootElement(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    protected FragmentFactory getFragmentFactory() {
        return fragmentFactory;
    }
}
