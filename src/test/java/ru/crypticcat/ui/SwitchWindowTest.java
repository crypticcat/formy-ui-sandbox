package ru.crypticcat.ui;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import ru.crypticcat.formy.sandbox.pages.SwitchWindowPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.SWITCHWINDOW_ENDPOINT;

public class SwitchWindowTest extends BaseTest {
    private static SwitchWindowPage switchWindowPage;

    @BeforeEach
    void setup() {
        switchWindowPage = new SwitchWindowPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        switchWindowPage.quit();
    }

    @Test
    @Description("Perform checks for alert on Switch Window page")
    void performAlertChecks() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Finding the alert button and clicking it");
        switchWindowPage.openPage(FORMY_HOME + SWITCHWINDOW_ENDPOINT);
        switchWindowPage.clickAlertButton();

        LOG.debug("Switching the focus to the alert");
        Alert alert = switchWindowPage.getAlert();
        softAssert.assertThat(alert.getText())
                .withFailMessage("Issues with the alert").contains("This is a test alert");

        LOG.debug("Accepting alert and verifying alert is not shown after accept");
        alert.accept();
        softAssert.assertThat(switchWindowPage.getAlertIfPresent())
                .withFailMessage("Alert is still present").isNull();

        softAssert.assertAll();
    }

    @Test
    @Description("Perform checks for new tab on Switch Window page")
    void performNewTabChecks() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Getting window handle and switching to new tab");
        String initHandle = switchWindowPage.getInitHandle();
        switchWindowPage.switchToNewTab();
        softAssert.assertThat(switchWindowPage.getWindowHandlesNum()).isEqualTo(2);

        LOG.debug("Switching to initial window and closing it");
        switchWindowPage.switchToInitHandle(initHandle);
        switchWindowPage.closeWindow();
        softAssert.assertThat(switchWindowPage.getWindowHandlesNum()).isEqualTo(1);

        softAssert.assertAll();
    }
}