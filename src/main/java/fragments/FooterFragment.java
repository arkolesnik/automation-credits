package fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by olena.kolesnyk on 14/02/2018.
 */
public class FooterFragment extends AbstractFragment {

    @FindBy(className = "//TODO: add locator")
    private WebElement root;

    @FindBy(linkText = "Personal Loans")
    private WebElement personalLoansTab;

    public void openPersonalLoansTab() {
        $(personalLoansTab).click();
    }
}
