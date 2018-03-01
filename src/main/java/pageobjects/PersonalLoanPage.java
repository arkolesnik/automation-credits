package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by olena.kolesnyk on 14/02/2018.
 */
public class PersonalLoanPage extends AbstractPage {

    @FindBy(id = "c68_loan_amount")
    private WebElement enterAmountInput;

    @FindBy(css = "#find-my-rate-form-1-desktop button")
    private WebElement findMyRateBtn;

    public PersonalLoanPage(WebDriver driver) {
        super(driver);
    }

    public void proceedWithAmount(String amount) {
        enterAmount(amount);
        clickFindMyRate();
    }

    private void enterAmount(String amount) {
        $(enterAmountInput).setValue(amount);
    }

    private void clickFindMyRate() {
        $(findMyRateBtn).click();
    }


}
