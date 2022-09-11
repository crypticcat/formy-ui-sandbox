package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.DropdownPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.DROPDOWN_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DropDownTest extends BaseTest {
    private static DropdownPage dropdownPage;

    @BeforeEach
    void setup() {
        dropdownPage = new DropdownPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        dropdownPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Drag and Drop page")
    void perfromDropdownChecks() {
        SoftAssertions softAssert = new SoftAssertions();

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

        softAssert.assertAll();
    }
}