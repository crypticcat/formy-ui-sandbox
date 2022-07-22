package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.ScrollPage;

public class ScrollTest {
    SoftAssertions softAssert = new SoftAssertions();

    ScrollPage scrollPage;

    @BeforeEach
    void setup() {
        scrollPage = new ScrollPage("chrome");
    }

    @AfterEach
    public void teardown() {
        scrollPage.quit();
    }

    @Test
    void scrollIntoView() {
        BasePage.logger.debug("Scroll till Full Name and Date Fields");
        scrollPage.scrollTillFirstNameField();
        softAssert.assertThat(scrollPage.isNameFieldDisplayed())
                .withFailMessage("The first checkbox should not be selected").isTrue();
        softAssert.assertThat(scrollPage.isDateFieldDisplayed())
                .withFailMessage("The first checkbox should not be selected").isTrue();
    }
}