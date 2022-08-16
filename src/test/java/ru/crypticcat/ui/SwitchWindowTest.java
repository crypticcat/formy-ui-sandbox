package ru.crypticcat.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;
import static ru.crypticcat.formy.sandbox.pages.BasePage.SWITCHWINDOW_ENDPOINT;

public class SwitchWindowTest extends BaseTest {
    @Test
    @Disabled
    @DisplayName("Perform checks for alert on Switch Window page")
    void performAlertChecks() {
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
    }

    @Test
    @Disabled
    @DisplayName("Perform checks for new tab on Switch Window page")
    void performNewTabChecks() {
        LOG.debug("Getting window handle and switching to new tab");
        String initHandle = switchWindowPage.getInitHandle();
        switchWindowPage.switchToNewTab();
        softAssert.assertThat(switchWindowPage.getWindowHandlesNum()).isEqualTo(2);

        LOG.debug("Switching to initial window and closing it");
        switchWindowPage.switchToInitHandle(initHandle);
        switchWindowPage.closeWindow();
        softAssert.assertThat(switchWindowPage.getWindowHandlesNum()).isEqualTo(1);
    }
}
