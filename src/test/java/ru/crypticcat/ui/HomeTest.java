package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.remote.SessionId;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.HomePage;

import java.util.Set;

public class HomeTest {
    BasePage basePage;
    private static final String FORMY_DOMAIN = "formy-project.herokuapp.com";
    private static final String FORMY_NAME = "Formy";
    private static final String FORMY_WELCOME = "Welcome to Formy";

    SoftAssertions softAssert = new SoftAssertions();

    HomePage homePage;

    @BeforeEach
    void setup() {
        homePage = new HomePage("chrome");
    }

    @AfterEach
    public void teardown() {
        homePage.quit();
    }

    @Test
    void performBasicChecks() {
        BasePage.logger.debug("Performing basic checks");
        softAssert.assertThat(homePage.getTitle())
                .withFailMessage("Actual page title was %s", homePage.getTitle())
                .isEqualTo(FORMY_NAME);
        softAssert.assertThat(homePage.getCurrentUrl())
                .withFailMessage("Actual url was %s", homePage.getCurrentUrl())
                .isEqualTo(BasePage.FORMY_HOME);
        softAssert.assertThat(homePage.getPageSource())
                .withFailMessage("Page source does not contain specified words")
                .containsIgnoringCase(FORMY_WELCOME);

        softAssert.assertAll();
    }

    @Test
    void performSessionIdCheck() {
        BasePage.logger.debug("Performing session id checks");
        SessionId sessionId = homePage.getSessionId();
        softAssert.assertThat(sessionId)
                .withFailMessage("Session id is null").isNotNull();

        softAssert.assertAll();
    }

    @Test
    void performCookiesChecks() {
        BasePage.logger.debug("Reading cookies on the page");
        int expectedCookiesSize = 1;
        Set<Cookie> cookies = homePage.readCookie();
        BasePage.logger.debug("Getting cookies: {}", cookies);
        softAssert.assertThat(cookies)
                .withFailMessage("Cookies' size is not equal to %s", expectedCookiesSize)
                .hasSize(expectedCookiesSize);

        BasePage.logger.debug("Getting cookie with specific name and performing checks");
        String cookieName = "_formy_session";
        Cookie cookie = homePage.getCookieWithName(cookieName);
        softAssert.assertThat(cookie.getValue())
                .withFailMessage("No cookie with %s", cookieName).isNotNull();
        softAssert.assertThat(cookie.isHttpOnly())
                .withFailMessage("Cookie is not HTTP-only").isTrue();
        softAssert.assertThat(cookie.getDomain())
                .withFailMessage("The actual domain was %s", cookie.getDomain())
                .isEqualTo(FORMY_DOMAIN);

        softAssert.assertAll();
    }

    @Test
    void testMaximizeWindow() {
        BasePage.logger.debug("Getting window object, its position and size");
        Window window = basePage.manageWindow();
        Point initialPosition = window.getPosition();
        Dimension initialSize = window.getSize();
        BasePage.logger.debug("Initial window: position {} -- size {}", initialPosition, initialSize);

        BasePage.logger.debug("Maximizing window");
        window.maximize();
        Point maximizedPosition = window.getPosition();
        Dimension maximizedSize = window.getSize();
        BasePage.logger.debug("Maximized window: position {} -- size {}", maximizedPosition, maximizedSize);

        softAssert.assertThat(initialPosition).isNotEqualTo(maximizedPosition);
        softAssert.assertThat(initialSize).isNotEqualTo(maximizedSize);

        softAssert.assertAll();
    }
}
