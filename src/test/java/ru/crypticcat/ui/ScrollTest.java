package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.ScrollPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.PAGESCROLL_ENDPOINT;

public class ScrollTest extends BaseTest {
    private static ScrollPage scrollPage;

    @BeforeEach
    void setup() {
        scrollPage = new ScrollPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        scrollPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Scroll page")
    void scrollIntoView() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Scroll till Full Name and Date Fields");
        scrollPage.openPage(FORMY_HOME + PAGESCROLL_ENDPOINT);
        scrollPage.scrollTillFirstNameField();
        softAssert.assertThat(scrollPage.isNameFieldDisplayed())
                .withFailMessage("The first checkbox should not be selected").isTrue();
        softAssert.assertThat(scrollPage.isDateFieldDisplayed())
                .withFailMessage("The first checkbox should not be selected").isTrue();

        softAssert.assertAll();
    }
}