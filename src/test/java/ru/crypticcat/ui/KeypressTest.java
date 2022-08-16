package ru.crypticcat.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.KEYPRESS_ENDPOINT;

public class KeypressTest extends BaseTest {
    @Test
    @Disabled
    @DisplayName("Perform checks for Key and Mouse Press page")
    void testCopyAndPaste() {
        LOG.debug("Entering the value into Full name field");
        keypressPage.openPage(FORMY_HOME + KEYPRESS_ENDPOINT);
        String valueToEnter = "Meow";
        keypressPage.enterValueIntoNameField(valueToEnter);
        softAssert.assertThat(keypressPage.getFullNameFieldValue()).isEqualTo(valueToEnter);

        LOG.debug("Clearing the value in Full name field");
        keypressPage.clearFullNameField();
        softAssert.assertThat(keypressPage.getFullNameFieldValue()).isEmpty();
    }
}
