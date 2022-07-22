package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.ModalPage;

public class ModalTest {
    SoftAssertions softAssert = new SoftAssertions();

    ModalPage modalPage;

    @BeforeEach
    void setup() {
        modalPage = new ModalPage("chrome");
    }

    @AfterEach
    public void teardown() {
        modalPage.quit();
    }

    @Test
    void performModalChecks() {
        BasePage.logger.debug("Clicking modal button to open modal window and verify that modal window is displayed");
        modalPage.clickModalBtn();
        softAssert.assertThat(modalPage.isModalWindowDisplayed()).isTrue();

        BasePage.logger.debug("Performing check for Close button in the opened modal window");
        String closeBtnTagName = modalPage.getCloseBtnTagName();
        softAssert.assertThat(closeBtnTagName)
                .withFailMessage("It is not a button, but a %s", closeBtnTagName)
                .isEqualTo("button");
        String closeBtnText = modalPage.getCloseBtnText();
        softAssert.assertThat(closeBtnText)
                .withFailMessage("Button is not named Close, but %s", closeBtnText)
                .contains("Close");

        BasePage.logger.debug("Clicking Close button in the modal window after wait");
        softAssert.assertThat(modalPage.isCloseBtnDisplayed()).isTrue();
        modalPage.clickCloseBtn();
        softAssert.assertThat(modalPage.isModalWindowClosed())
                .withFailMessage("Modal window is not closed")
                .isTrue();

        softAssert.assertAll();
    }
}
