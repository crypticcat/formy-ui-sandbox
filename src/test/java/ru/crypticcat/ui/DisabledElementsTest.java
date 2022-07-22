package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.DisabledEnabledPage;

public class DisabledElementsTest {
    SoftAssertions softAssert = new SoftAssertions();
    DisabledEnabledPage disabledEnabledPage;

    @BeforeEach
    void setup() {
        disabledEnabledPage = new DisabledEnabledPage("chrome");
    }

    @AfterEach
    public void teardown() {
        disabledEnabledPage.quit();
    }

    @Test
    void performCheckForDisabledEnabled() {
        BasePage.logger.debug("Find the disabled element and check it is not enabled");
        softAssert.assertThat(disabledEnabledPage.isFirstElemEnabled())
                .withFailMessage("Element should not be enabled").isFalse();

        BasePage.logger.debug("Find the enabled element and check it is enabled");
        softAssert.assertThat(disabledEnabledPage.isSecondElemEnabled())
                .withFailMessage("Element should be enabled").isTrue();

        softAssert.assertAll();
    }
}
