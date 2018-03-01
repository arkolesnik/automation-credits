package pageobjects;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PageObjectUtils;
import utils.PropertiesUtils;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static utils.PageObjectUtils.selectRandomDropItem;

/**
 * Created by olena.kolesnyk on 16/02/2018.
 */
public class ProfileStepPage extends AbstractPage {

    private static final String PROFILE_STEP_HEADER_KEY = "profile_step_header_text";
    private static final String PHONE_TOOLTIP_TEXT_KEY = "phone_tooltip_step_text";

    private static final String CITIZENSHIP_TOOLTIP_TEXT_KEY = "citizenship_tooltip_text";
    private static final String SECURITY_TOOLTIP_TEXT_KEY = "security_tooltip_text";

    @FindBy(css = "fieldset[data-step='profile'] .big-title")
    private WebElement profileHeaderTxt;

    @FindBy(css = "input[name='borrower_first_name']")
    private WebElement firstNameInput;

    @FindBy(css = "input[name='borrower_last_name']")
    private WebElement lastNameInput;

    @FindBy(css = ".tooltip-inner")
    private WebElement tooltip;

    @FindBy(css = "div[data-fields='borrower.phone'] span")
    private WebElement phoneLink;

    @FindBy(css = "input[name='borrower_phone']")
    private WebElement phoneInput;

    @FindBy(css = "div[data-fields='expenses.housing_type'] a")
    private WebElement housingStatusDrop;

    @FindBy(css = "#select2-drop li")
    private List<WebElement> dropItems;

    @FindBy(css = "a[class='to-manual-mode']")
    private WebElement addressLink;

    @FindBy(css = "input[name='current_address_street']")
    private WebElement streetInput;

    @FindBy(css = "input[name='current_address_unit']")
    private WebElement unitInput;

    @FindBy(css = "input[name='current_address_city']")
    private WebElement cityInput;

    @FindBy(css = "div[data-fields='current_address.state'] a")
    private WebElement stateDrop;

    @FindBy(css = "input[name='current_address_zipcode']")
    private WebElement zipCodeInput;

    @FindBy(css = "div[data-fields='borrower.citizenship_status'] span")
    private WebElement citizenshipLink;

    @FindBy(css = "div[data-fields='borrower.citizenship_status'] a")
    private WebElement citizenshipDrop;

    @FindBy(css = "div[data-fields='borrower.ssn'] span")
    private WebElement socialSecutiryLink;

    @FindBy(css = "input[name='borrower_ssn']")
    private WebElement socialSecutiryInput;

    @FindBy(css = "input[name='email']")
    private WebElement emailInput;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInput;

    @FindBy(css = "div.send-submission button")
    private WebElement agreeButton;

    @FindBy(css = "div.loading-message")
    private WebElement searchingMsg;

    private PropertiesUtils utils = new PropertiesUtils();

    public ProfileStepPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProfileStepHeaderCorrect(WebDriverWait wait) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(profileHeaderTxt));
        return profileHeaderTxt.getText().contains(
                utils.getPropertiesFile().getProperty(PROFILE_STEP_HEADER_KEY));
    }

    public boolean isPhoneTooltipCorrect(WebDriverWait wait) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(phoneLink));
        $(phoneLink).click();
        wait.until(ExpectedConditions.visibilityOf(tooltip));
        return tooltip.getText().contains(utils.getPropertiesFile().getProperty(PHONE_TOOLTIP_TEXT_KEY));
    }

    public boolean isCitizenshipTooltipCorrect(WebDriverWait wait) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(citizenshipLink));
        $(citizenshipLink).click();
        wait.until(ExpectedConditions.visibilityOf(tooltip));
        String tooltipText = tooltip.getText();
        $(socialSecutiryInput).click();
        return tooltipText.contains(utils.getPropertiesFile().getProperty(CITIZENSHIP_TOOLTIP_TEXT_KEY));
    }

    public boolean isSecurityTooltipCorrect(WebDriverWait wait) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(socialSecutiryLink));
        $(socialSecutiryLink).click();
        wait.until(ExpectedConditions.visibilityOf(tooltip));
        return tooltip.getText().contains(utils.getPropertiesFile().getProperty(SECURITY_TOOLTIP_TEXT_KEY));
    }

    @Step("Fill in Profile form with random values")
    public void fillInProfileFormWithRandom() {
        setRandomFirstName();
        setRandomLastName();
        setRandomPhone();
        selectRandomHousingStatus();
        openAddressForm();
        setRandomAddress();
        setRandomUnit();
        setRandomCity();
        selectRandomState();
        setRandomZipCode();
        selectRandomCitizenship();
        setRandomSocialNumber();
        setRandomEmail();
        setRandomPassword();
    }

    public void submitForm() {
        $(agreeButton).click();
    }

    public boolean isSearchingMessageDisplayed() {
        return $(searchingMsg).isDisplayed();
    }

    private void setRandomFirstName() {
        $(firstNameInput).setValue(RandomStringUtils.randomAlphabetic(7));
    }

    private void setRandomLastName() {
        $(lastNameInput).setValue(RandomStringUtils.randomAlphabetic(12));
    }

    private void setRandomPhone() {
        $(phoneInput).setValue(RandomStringUtils.randomNumeric(10));
    }

    private void selectRandomHousingStatus() {
        $(housingStatusDrop).click();
        selectRandomDropItem(dropItems);
    }

    private void openAddressForm() {
        $(addressLink).click();
    }

    private void setRandomAddress() {
        $(streetInput).setValue(RandomStringUtils.randomAlphabetic(15));
    }

    private void setRandomUnit() {
        $(unitInput).setValue(RandomStringUtils.randomAlphabetic(7));
    }

    private void setRandomCity() {
        $(cityInput).setValue(RandomStringUtils.randomAlphabetic(10));
    }

    private void selectRandomState() {
        $(stateDrop).click();
        selectRandomDropItem(dropItems);
    }

    private void setRandomZipCode() {
        $(zipCodeInput).setValue(RandomStringUtils.randomNumeric(5));
    }

    private void selectRandomCitizenship() {
        $(citizenshipDrop).click();
        selectRandomDropItem(dropItems);
    }

    private void setRandomSocialNumber() {
        $(socialSecutiryInput).setValue(RandomStringUtils.randomNumeric(9));
    }

    private void setRandomEmail() {
        $(emailInput).setValue(PageObjectUtils.generateEmail());
    }

    private void setRandomPassword() {
        $(passwordInput).setValue(RandomStringUtils.randomAlphanumeric(15));
    }

}

