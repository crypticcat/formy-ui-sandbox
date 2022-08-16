package ru.crypticcat.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.CHECKBOX_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class CheckboxesTest extends BaseTest {

    @Test
    @DisplayName("Perform checks for Checkbox page")
    void performCheckboxesCheck() {
        LOG.debug("Searching for the first checkbox and check it is not selected");
        checkboxesPage.openPage(FORMY_HOME + CHECKBOX_ENDPOINT);
        softAssert.assertThat(checkboxesPage.isFirstCheckboxSelected())
                .withFailMessage("The first checkbox should not be selected").isFalse();

        LOG.debug("Click the first checkbox and check it is selected");
        checkboxesPage.clickFirstCheckbox();
        softAssert.assertThat(checkboxesPage.isFirstCheckboxSelected())
                .withFailMessage("The first checkbox should be selected").isTrue();

        LOG.debug("Find the second checkbox and check it is not selected");
        boolean secondCheckboxSelected = checkboxesPage.isSecondCheckboxSelected();
        softAssert.assertThat(secondCheckboxSelected)
                .withFailMessage("The second checkbox should not be selected").isFalse();

        LOG.debug("Find the third checkbox, click it and check it is selected");
        checkboxesPage.clickThirdCheckbox();
        softAssert.assertThat(checkboxesPage.isThirdCheckboxSelected())
                .withFailMessage("The third checkbox should be selected").isTrue();

        LOG.debug("Click the third checkbox and check it is unselected");
        checkboxesPage.clickThirdCheckbox();
        softAssert.assertThat(checkboxesPage.isThirdCheckboxSelected())
                .withFailMessage("The third checkbox should be unselected").isFalse();
    }
}
