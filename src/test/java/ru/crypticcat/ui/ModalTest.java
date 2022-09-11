package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.CheckboxesPage;
import ru.crypticcat.formy.sandbox.pages.ModalPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.MODAL_ENDPOINT;

public class ModalTest extends BaseTest {
    private static ModalPage modalPage;

    @BeforeEach
    void setup() {
        modalPage = new ModalPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        modalPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Modal page")
    void performModalChecks() {
        SoftAssertions softAssert = new SoftAssertions();

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

        softAssert.assertAll();
    }
}
