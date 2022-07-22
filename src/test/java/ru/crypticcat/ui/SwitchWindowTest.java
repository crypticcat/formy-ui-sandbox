package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.SwitchWindowPage;

public class SwitchWindowTest {
    SoftAssertions softAssert = new SoftAssertions();

    SwitchWindowPage switchWindowPage;

    @BeforeEach
    void setup() {
        switchWindowPage = new SwitchWindowPage("chrome");
    }

    @AfterEach
    public void teardown() {
        switchWindowPage.quit();
    }

    @Test
    void performAlertChecks() {
        BasePage.logger.debug("Finding the alert button and clicking it");
        switchWindowPage.clickAlertButton();

        BasePage.logger.debug("Switching the focus to the alert");
        Alert alert = switchWindowPage.getAlert();
        softAssert.assertThat(alert.getText())
                .withFailMessage("Issues with the alert").contains("This is a test alert");

        BasePage.logger.debug("Accepting alert and verifying alert is not shown after accept");
        alert.accept();
        softAssert.assertThat(switchWindowPage.getAlertIfPresent())
                .withFailMessage("Alert is still present").isNull();

        softAssert.assertAll();
    }

    @Test
    void performNewTabChecks() {
        BasePage.logger.debug("Getting window handle and switching to new tab");
        String initHandle = switchWindowPage.getInitHandle();
        switchWindowPage.switchToNewTab();
        softAssert.assertThat(switchWindowPage.getWindowHandlesNum()).isEqualTo(2);

        BasePage.logger.debug("Switching to initial window and closing it");
        switchWindowPage.switchToInitHandle(initHandle);
        switchWindowPage.closeWindow();
        softAssert.assertThat(switchWindowPage.getWindowHandlesNum()).isEqualTo(1);

        softAssert.assertAll();
    }
}
