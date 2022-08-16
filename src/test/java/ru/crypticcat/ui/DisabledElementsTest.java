package ru.crypticcat.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.ENABLED_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DisabledElementsTest extends BaseTest {
    @Test
    @Disabled
    @DisplayName("Perform checks for Enabled and disabled elements page")
    void performCheckForDisabledEnabled() {
        LOG.debug("Finding the disabled element and checking it is not enabled");
        disabledEnabledPage.openPage(FORMY_HOME+ENABLED_ENDPOINT);
        softAssert.assertThat(disabledEnabledPage.isFirstElemEnabled())
                .withFailMessage("Element should not be enabled").isFalse();

        LOG.debug("Find the enabled element and check it is enabled");
        softAssert.assertThat(disabledEnabledPage.isSecondElemEnabled())
                .withFailMessage("Element should be enabled").isTrue();

    }
}
