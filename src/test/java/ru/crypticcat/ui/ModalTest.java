package ru.crypticcat.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.MODAL_ENDPOINT;

public class ModalTest extends BaseTest {
    @Test
    @DisplayName("Perform checks for Modal page")
    void performModalChecks() {
        LOG.debug("Clicking modal button to open modal window and verify that modal window is displayed");
        modalPage.openPage(FORMY_HOME + MODAL_ENDPOINT);
        modalPage.clickModalBtn();
        softAssert.assertThat(modalPage.isModalWindowDisplayed()).isTrue();

        LOG.debug("Performing check for Close button in the opened modal window");
        String closeBtnTagName = modalPage.getCloseBtnTagName();
        softAssert.assertThat(closeBtnTagName)
                .withFailMessage("It is not a button, but a %s", closeBtnTagName)
                .isEqualTo("button");
        String closeBtnText = modalPage.getCloseBtnText();
        softAssert.assertThat(closeBtnText)
                .withFailMessage("Button is not named Close, but %s", closeBtnText)
                .contains("Close");

        LOG.debug("Clicking Close button in the modal window after wait");
        softAssert.assertThat(modalPage.isCloseBtnDisplayed()).isTrue();
        modalPage.clickCloseBtn();
        softAssert.assertThat(modalPage.isModalWindowClosed())
                .withFailMessage("Modal window is not closed")
                .isTrue();
    }
}
