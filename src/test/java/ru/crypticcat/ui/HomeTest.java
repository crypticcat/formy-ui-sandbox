package ru.crypticcat.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.remote.SessionId;

import java.util.Set;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class HomeTest extends BaseTest {
    private static final String FORMY_DOMAIN = "formy-project.herokuapp.com";
    private static final String FORMY_NAME = "Formy";
    private static final String FORMY_WELCOME = "Welcome to Formy";

    @Test
    @Disabled
    @DisplayName("Perform basic checks for Home page")
    void performBasicChecks() {
        LOG.debug("Performing basic checks");
        homePage.openPage(FORMY_HOME);
        softAssert.assertThat(homePage.getTitle())
                .withFailMessage("Actual page title was %s", homePage.getTitle())
                .isEqualTo(FORMY_NAME);
        softAssert.assertThat(homePage.getCurrentUrl())
                .withFailMessage("Actual url was %s", homePage.getCurrentUrl())
                .isEqualTo(FORMY_HOME);
        softAssert.assertThat(homePage.getPageSource())
                .withFailMessage("Page source does not contain specified words")
                .containsIgnoringCase(FORMY_WELCOME);
    }

    @Test
    @DisplayName("Perform sessionId checks for Home page")
    void performSessionIdCheck() {
        LOG.debug("Performing session id checks");
        SessionId sessionId = homePage.getSessionId();
        softAssert.assertThat(sessionId)
                .withFailMessage("Session id is null").isNotNull();
    }

    @Test
    @Disabled
    @DisplayName("Perform cookies checks for Home page")
    void performCookiesChecks() {
        LOG.debug("Reading cookies on the page");
        int expectedCookiesSize = 1;
        Set<Cookie> cookies = homePage.readCookie();
        LOG.debug("Getting cookies: {}", cookies);
        softAssert.assertThat(cookies)
                .withFailMessage("Cookies' size is not equal to %s", expectedCookiesSize)
                .hasSize(expectedCookiesSize);

        LOG.debug("Getting cookie with specific name and performing checks");
        String cookieName = "_formy_session";
        Cookie cookie = homePage.getCookieWithName(cookieName);
        softAssert.assertThat(cookie.getValue())
                .withFailMessage("No cookie with %s", cookieName).isNotNull();
        softAssert.assertThat(cookie.isHttpOnly())
                .withFailMessage("Cookie is not HTTP-only").isTrue();
        softAssert.assertThat(cookie.getDomain())
                .withFailMessage("The actual domain was %s", cookie.getDomain())
                .isEqualTo(FORMY_DOMAIN);
    }

    @Test
    @Disabled
    @DisplayName("Perform maximize window checks for Home page")
    void testMaximizeWindow() {
        LOG.debug("Getting window object, its position and size");
        Window window = basePage.manageWindow();
        Point initialPosition = window.getPosition();
        Dimension initialSize = window.getSize();
        LOG.debug("Initial window: position {} -- size {}", initialPosition, initialSize);

        LOG.debug("Maximizing window");
        window.maximize();
        Point maximizedPosition = window.getPosition();
        Dimension maximizedSize = window.getSize();
        LOG.debug("Maximized window: position {} -- size {}", maximizedPosition, maximizedSize);

        softAssert.assertThat(initialPosition).isNotEqualTo(maximizedPosition);
        softAssert.assertThat(initialSize).isNotEqualTo(maximizedSize);
    }
}
