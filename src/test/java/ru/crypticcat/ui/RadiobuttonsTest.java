package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.RadiobuttonsPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.RADIOBUTTON_ENDPOINT;

public class RadiobuttonsTest extends BaseTest {
    private static RadiobuttonsPage radiobuttonsPage;

    @BeforeEach
    void setup() {
        radiobuttonsPage = new RadiobuttonsPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        radiobuttonsPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Radiobuttons page")
    void performRadiosChecks() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Find the first radiobutton and check it is selected");
        radiobuttonsPage.openPage(FORMY_HOME + RADIOBUTTON_ENDPOINT);
        softAssert.assertThat(radiobuttonsPage.getDomAttrForFirstRadio("id"))
                .isEqualTo("radio-button-1");
        softAssert.assertThat(radiobuttonsPage.isFirstRadioSelected())
                .withFailMessage("The first radiobutton should be selected").isTrue();

        LOG.debug("Find the second radiobutton and check it is not selected");
        softAssert.assertThat(radiobuttonsPage.getDomAttrForSecondRadio("id"))
                .isNull();
        softAssert.assertThat(radiobuttonsPage.isSecondRadioSelected())
                .withFailMessage("The second radiobutton should not be selected").isFalse();

        LOG.debug("Find the third radiobutton and check it is not selected");
        softAssert.assertThat(radiobuttonsPage.getDomAttrForThirdRadio("id"))
                .isNull();
        softAssert.assertThat(radiobuttonsPage.isThirdRadioSelected())
                .withFailMessage("The third radiobutton should not be selected").isFalse();

        LOG.debug("Click the second radiobutton and perform the checks for all radiobuttons");
        radiobuttonsPage.clickSecondRadio();
        softAssert.assertThat(radiobuttonsPage.isSecondRadioSelected())
                .withFailMessage("The second radiobutton should be selected").isTrue();
        softAssert.assertThat(radiobuttonsPage.isFirstRadioSelected())
                .withFailMessage("The first radiobutton should be unselected").isFalse();
        softAssert.assertThat(radiobuttonsPage.isThirdRadioSelected())
                .withFailMessage("The third radiobutton should not be selected").isFalse();

        softAssert.assertAll();
    }
}
