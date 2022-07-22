package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.RadiobuttonsPage;

public class RadiobuttonsTest {
    SoftAssertions softAssert = new SoftAssertions();
    RadiobuttonsPage radiobuttonsPage;

    @BeforeEach
    void setup() {
        radiobuttonsPage = new RadiobuttonsPage("chrome");
    }

    @AfterEach
    public void teardown() {
        radiobuttonsPage.quit();
    }

    @Test
    void performRadiosChecks() {
        BasePage.logger.debug("Find the first radiobutton and check it is selected");
        softAssert.assertThat(radiobuttonsPage.getDomAttrForFirstRadio("id"))
                .isEqualTo("radio-button-1");
        softAssert.assertThat(radiobuttonsPage.isFirstRadioSelected())
                .withFailMessage("The first radiobutton should be selected").isTrue();

        BasePage.logger.debug("Find the second radiobutton and check it is not selected");
        softAssert.assertThat(radiobuttonsPage.getDomAttrForSecondRadio("id"))
                .isNull();
        softAssert.assertThat(radiobuttonsPage.isSecondRadioSelected())
                .withFailMessage("The second radiobutton should not be selected").isFalse();

        BasePage.logger.debug("Find the third radiobutton and check it is not selected");
        softAssert.assertThat(radiobuttonsPage.getDomAttrForThirdRadio("id"))
                .isNull();
        softAssert.assertThat(radiobuttonsPage.isThirdRadioSelected())
                .withFailMessage("The third radiobutton should not be selected").isFalse();

        BasePage.logger.debug("Click the second radiobutton and perform the checks for all radiobuttons");
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
