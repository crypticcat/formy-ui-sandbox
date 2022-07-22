package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.CheckboxesPage;

public class CheckboxesTest {
    SoftAssertions softAssert = new SoftAssertions();

    CheckboxesPage checkboxesPage;

    @BeforeEach
    void setup() {
        checkboxesPage = new CheckboxesPage("chrome");
    }

    @AfterEach
    public void teardown() {
        checkboxesPage.quit();
    }

    @Test
    void performCheckboxesCheck() {
        BasePage.logger.debug("Find the first checkbox and check it is not selected");
        softAssert.assertThat(checkboxesPage.isFirstCheckboxSelected())
                .withFailMessage("The first checkbox should not be selected").isFalse();

        BasePage.logger.debug("Click the first checkbox and check it is selected");
        checkboxesPage.clickFirstCheckbox();
        softAssert.assertThat(checkboxesPage.isFirstCheckboxSelected())
                .withFailMessage("The first checkbox should be selected").isTrue();

        BasePage.logger.debug("Find the second checkbox and check it is not selected");
        boolean secondCheckboxSelected = checkboxesPage.isSecondCheckboxSelected();
        softAssert.assertThat(secondCheckboxSelected)
                .withFailMessage("The second checkbox should not be selected").isFalse();

        BasePage.logger.debug("Find the third checkbox, click it and check it is selected");
        checkboxesPage.clickThirdCheckbox();
        softAssert.assertThat(checkboxesPage.isThirdCheckboxSelected())
                .withFailMessage("The third checkbox should be selected").isTrue();

        BasePage.logger.debug("Click the third checkbox and check it is unselected");
        checkboxesPage.clickThirdCheckbox();
        softAssert.assertThat(checkboxesPage.isThirdCheckboxSelected())
                .withFailMessage("The third checkbox should be unselected").isFalse();

        softAssert.assertAll();
    }
}
