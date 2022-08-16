package ru.crypticcat.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.DROPDOWN_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DropDownTest extends BaseTest {
    @Test
    @DisplayName("Perform checks for Drag and Drop page")
    void perfromDropdownChecks() {
        LOG.debug("Opening the dropdown on Dropdown page");
        dropdownPage.openPage(FORMY_HOME + DROPDOWN_ENDPOINT);
        dropdownPage.clickDropdown();
        softAssert.assertThat(dropdownPage.isDropdownDisplayed())
                .withFailMessage("Dropdown is not opened").isTrue();

        LOG.debug("Performing checks for the first option");
        String firstOptionId = "autocomplete";
        softAssert.assertThat(dropdownPage.getAttrForFirstOption("id"))
                .withFailMessage("The first option id differs from %s", firstOptionId)
                .isEqualTo(firstOptionId);
    }
}