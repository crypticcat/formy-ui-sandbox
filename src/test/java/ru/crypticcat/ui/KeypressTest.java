package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.KeypressPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.KEYPRESS_ENDPOINT;

public class KeypressTest extends BaseTest {

    private static KeypressPage keypressPage;

    @BeforeEach
    void setup() {
        keypressPage = new KeypressPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        keypressPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Key and Mouse Press page")
    void testCopyAndPaste() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Entering the value into Full name field");
        keypressPage.openPage(FORMY_HOME + KEYPRESS_ENDPOINT);
        String valueToEnter = "Meow";
        keypressPage.enterValueIntoNameField(valueToEnter);
        softAssert.assertThat(keypressPage.getFullNameFieldValue()).isEqualTo(valueToEnter);

        LOG.debug("Clearing the value in Full name field");
        keypressPage.clearFullNameField();
        softAssert.assertThat(keypressPage.getFullNameFieldValue()).isEmpty();

        softAssert.assertAll();
    }
}
