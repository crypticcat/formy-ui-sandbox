package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.DisabledEnabledPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.ENABLED_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DisabledElementsTest extends BaseTest {
    private static DisabledEnabledPage disabledEnabledPage;

    @BeforeEach
    void setup() {
        disabledEnabledPage = new DisabledEnabledPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        disabledEnabledPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Enabled and disabled elements page")
    void performCheckForDisabledEnabled() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Finding the disabled element and checking it is not enabled");
        disabledEnabledPage.openPage(FORMY_HOME + ENABLED_ENDPOINT);
        softAssert.assertThat(disabledEnabledPage.isFirstElemEnabled())
                .withFailMessage("Element should not be enabled").isFalse();

        LOG.debug("Find the enabled element and check it is enabled");
        softAssert.assertThat(disabledEnabledPage.isSecondElemEnabled())
                .withFailMessage("Element should be enabled").isTrue();

        softAssert.assertAll();
    }
}
