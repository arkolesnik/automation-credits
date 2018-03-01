package test_classes;

import io.qameta.allure.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageobjects.MainPage;
import pageobjects.PersonalLoanPage;
import pageobjects.PersonalStepPage;
import pageobjects.ProfileStepPage;

import java.io.IOException;

@Epic("Main example test")
@Feature("Fill in Personal loan request")
public class ExampleTest extends DriverFixture {

    private MainPage mainPage;
    private PersonalLoanPage personalLoanPage;
    private PersonalStepPage personalStepPage;
    private ProfileStepPage profileStepPage;

    @BeforeClass
    public void initializePages() {
        mainPage = new MainPage(driver);
        personalLoanPage = new PersonalLoanPage(driver);
        personalStepPage = new PersonalStepPage(driver);
        profileStepPage = new ProfileStepPage(driver);
    }

    @Test
    @Parameters("amount")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fill in 2 parts of web form in Personal loan request and submit")
    public void checkPersonalLoans(String amount) throws IOException {
        mainPage.getFooterFragment().openPersonalLoansTab();
        personalLoanPage.proceedWithAmount(amount);

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        softAssert.assertTrue(personalStepPage.isPersonalStepHeaderCorrect(),
                "The header text on Personal step page is not correct; ");
        softAssert.assertTrue(personalStepPage.isIncomeTooltipTextCorrect(wait));
        personalStepPage.fillInPersonalFormWithRandom();
        personalStepPage.submitForm();

        softAssert.assertTrue(profileStepPage.isProfileStepHeaderCorrect(wait),
                "The header text on Profile step page is not correct; ");
        softAssert.assertTrue(profileStepPage.isPhoneTooltipCorrect(wait),
                "The text of phone tooltip is not correct; ");


        profileStepPage.fillInProfileFormWithRandom();
        softAssert.assertTrue(profileStepPage.isCitizenshipTooltipCorrect(wait),
                "The text of citizenship tooltip is not correct; ");
        softAssert.assertTrue(profileStepPage.isSecurityTooltipCorrect(wait),
                "The text of security tooltip is not correct; ");
        profileStepPage.submitForm();

        softAssert.assertTrue(profileStepPage.isSearchingMessageDisplayed(),
                "Search progress bar is not displayed. ");
        softAssert.assertAll();
    }
}

