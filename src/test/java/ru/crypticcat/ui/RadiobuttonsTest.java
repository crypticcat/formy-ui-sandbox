package ru.crypticcat.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.RADIOBUTTON_ENDPOINT;

public class RadiobuttonsTest extends BaseTest {
    @Test
    @DisplayName("Perform checks for Radiobuttons page")
    void performRadiosChecks() {
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
    }
}
