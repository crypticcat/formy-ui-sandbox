package ru.crypticcat.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.PAGESCROLL_ENDPOINT;

public class ScrollTest extends BaseTest {
    @Test
    @Disabled
    @DisplayName("Perform checks for Scroll page")
    void scrollIntoView() {
        LOG.debug("Scroll till Full Name and Date Fields");
        scrollPage.openPage(FORMY_HOME + PAGESCROLL_ENDPOINT);
        scrollPage.scrollTillFirstNameField();
        softAssert.assertThat(scrollPage.isNameFieldDisplayed())
                .withFailMessage("The first checkbox should not be selected").isTrue();
        softAssert.assertThat(scrollPage.isDateFieldDisplayed())
                .withFailMessage("The first checkbox should not be selected").isTrue();
    }
}