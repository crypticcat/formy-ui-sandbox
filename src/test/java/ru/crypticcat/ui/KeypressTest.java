package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.KeypressPage;

public class KeypressTest {

    SoftAssertions softAssert = new SoftAssertions();

    KeypressPage keypressPage;

    @BeforeEach
    void setup() {
        keypressPage = new KeypressPage("chrome");
    }

    @AfterEach
    public void teardown() {
        keypressPage.quit();
    }

    @Test
    void testCopyAndPaste() {
        BasePage.logger.debug("Entering the value into Full name field");
        String valueToEnter = "Meow";
        keypressPage.enterValueIntoNameField(valueToEnter);
        softAssert.assertThat(keypressPage.getFullNameFieldValue()).isEqualTo(valueToEnter);

        BasePage.logger.debug("Clearing the value in Full name field");
        keypressPage.clearFullNameField();
        softAssert.assertThat(keypressPage.getFullNameFieldValue()).isEmpty();

        softAssert.assertAll();
    }
}
