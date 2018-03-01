package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageObjectUtils;
import utils.PropertiesUtils;

import static com.codeborne.selenide.Selenide.$;
import static utils.PageObjectUtils.selectRandomDropItem;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olena.kolesnyk on 14/02/2018.
 */
public class PersonalStepPage extends AbstractPage {

    private static final String PERSONAL_STEP_HEADER_KEY = "personal_step_header_text";
    private static final String INCOME_TOOLTIP_TEXT_KEY = "income_tooltip_text";

    @FindBy(css = "fieldset[data-step='personal'] .big-title")
    private WebElement personalHeaderTxt;

    @FindBy(css = "div[data-fields='employment.income_yearly'] span")
    private WebElement getAnnualIncomeLink;

    @FindBy(css = ".tooltip-inner")
    private WebElement annualIncomeTooltip;

    @FindBy(css = "div[data-fields='loan_purpose'] a")
    private WebElement loanPersonalDrop;

    @FindBy(css = "#select2-drop li")
    private List<WebElement> dropItems;

    @FindBy(css = "div[data-fields='borrower.highest_degree_type'] a")
    private WebElement educationLevelDrop;

    @FindBy(css = "div[data-fields='employment.employment_status'] a")
    private WebElement employmentStatusDrop;

    @FindBy(css = "div[data-fields='borrower.dob'] input")
    private WebElement dateOfBirthInput;

    @FindBy(css = "div[data-fields='employment.income_yearly'] input")
    private WebElement annualIncomeInput;

    @FindBy(css = "div[data-fields='borrower.credit_score_approx'] a")
    private WebElement creditScoreDrop;

    @FindBy(css = "fieldset[data-step='personal'] .next-step")
    private WebElement nextStepBtn;

    private PropertiesUtils utils = new PropertiesUtils();

    public PersonalStepPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPersonalStepHeaderCorrect() throws IOException {
        return personalHeaderTxt.getText().equalsIgnoreCase(
                utils.getPropertiesFile().getProperty(PERSONAL_STEP_HEADER_KEY));
    }

    public boolean isIncomeTooltipTextCorrect(WebDriverWait wait) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(getAnnualIncomeLink));
        getAnnualIncomeLink.click();
        return annualIncomeTooltip.getText().equalsIgnoreCase(
                utils.getPropertiesFile().getProperty(INCOME_TOOLTIP_TEXT_KEY));
    }

    @Step("Fill in Personal form with random values")
    public void fillInPersonalFormWithRandom() {
        selectRandomPersonalLoan();
        selectRandomEducation();
        selectRandomEmployment();
        setRandomDOB();
        setRandomIncome();
        selectRandomCreditScore();
    }

    public void submitForm() {
        nextStepBtn.click();
    }

    private void selectRandomPersonalLoan() {
        $(loanPersonalDrop).click();
        selectRandomDropItem(dropItems);
    }

    private void selectRandomEducation() {
        $(educationLevelDrop).click();
        selectRandomDropItem(dropItems);
    }

    private void selectRandomEmployment() {
        $(employmentStatusDrop).click();
        selectRandomDropItem(dropItems);
    }

    private void setRandomDOB() {
        $(dateOfBirthInput).setValue(PageObjectUtils.generateDOB());
    }

    private void setRandomIncome() {
        $(annualIncomeInput).setValue(String.valueOf(ThreadLocalRandom.current().nextInt(10000, 80000)));
    }

    private void selectRandomCreditScore() {
        $(creditScoreDrop).click();
        selectRandomDropItem(dropItems);
    }

}
