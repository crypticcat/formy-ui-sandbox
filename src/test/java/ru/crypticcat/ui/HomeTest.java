package ru.crypticcat.ui;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.remote.SessionId;
import ru.crypticcat.formy.sandbox.pages.CheckboxesPage;
import ru.crypticcat.formy.sandbox.pages.HomePage;

import java.util.Set;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class HomeTest extends BaseTest {
    private static HomePage homePage;

    @BeforeEach
    void setup() {
        homePage = new HomePage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        homePage.quit();
    }
    private static final String FORMY_DOMAIN = "formy-project.herokuapp.com";
    private static final String FORMY_NAME = "Formy";
    private static final String FORMY_WELCOME = "Welcome to Formy";

    @Test()
    @Description("Perform basic checks for Home page")
    void performBasicChecks() {
        SoftAssertions softAssert = new SoftAssertions();

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

        softAssert.assertAll();
    }

    @Test
    @Description("Perform sessionId checks for Home page")
    void performSessionIdCheck() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Performing session id checks");
        SessionId sessionId = homePage.getSessionId();
        softAssert.assertThat(sessionId)
                .withFailMessage("Session id is null").isNotNull();

        softAssert.assertAll();
    }

    @Test
    @Description("Perform cookies checks for Home page")
    void performCookiesChecks() {
        SoftAssertions softAssert = new SoftAssertions();

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

        softAssert.assertAll();
    }

    @Test
    @Description("Perform maximize window checks for Home page")
    void testMaximizeWindow() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Getting window object, its position and size");
        Window window = homePage.manageWindow();
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

        softAssert.assertAll();
    }
}
