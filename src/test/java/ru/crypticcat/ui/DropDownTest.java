package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.DropdownPage;

public class DropDownTest {
    SoftAssertions softAssert = new SoftAssertions();

    DropdownPage dropdownPage;

    @BeforeEach
    void setup() {
        dropdownPage = new DropdownPage("chrome");
    }

    @AfterEach
    public void teardown() {
        dropdownPage.quit();
    }

    @Test
    void perfromDropdownChecks() {
        BasePage.logger.debug("Opening the dropdown");
        dropdownPage.clickDropdown();
        softAssert.assertThat(dropdownPage.isDropdownDisplayed())
                        .withFailMessage("Dropdown is not opened").isTrue();

        BasePage.logger.debug("Performing checks for the first option");
        String firstOptionId = "autocomplete";
        softAssert.assertThat(dropdownPage.getAttrForFirstOption("id"))
                .withFailMessage("The first option id differs from %s", firstOptionId)
                .isEqualTo(firstOptionId);

        softAssert.assertAll();
    }
}