package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.DatePickerPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerTest {
    SoftAssertions softAssert = new SoftAssertions();
    DatePickerPage datePickerPage;
    public LocalDate today = LocalDate.now();

    @BeforeEach
    void setup() {
        datePickerPage = new DatePickerPage("chrome");
    }

    @AfterEach
    public void teardown() {
        datePickerPage.quit();
    }

    @Test
    void performDatePickerChecks() {
        BasePage.logger.debug("Verifying the placeholder is displayed in the date picker field");
        String datePickerPlaceholder = datePickerPage.getDatePickerAttr("placeholder");
        softAssert.assertThat(datePickerPlaceholder)
                .withFailMessage("Placeholder includes: %s", datePickerPlaceholder)
                .isEqualTo("mm/dd/yyyy");

        BasePage.logger.debug("Opening the calendar; verifying that the current year is displayed");
        int currentYear = today.getYear();
        datePickerPage.clickDatePicker();
        softAssert.assertThat(datePickerPage.isDatePickerSwitchDisplayed())
                .withFailMessage("Calendar is not displayed")
                .isTrue();
        softAssert.assertThat(datePickerPage.getDatePickerSwitchText())
                .withFailMessage("Date picker switch does not include the current year, but %s",
                        datePickerPage.getDatePickerSwitchText())
                .contains(String.valueOf(currentYear));

        BasePage.logger.debug("Selecting the previous year date");
        datePickerPage.selectPrevYearDateInCalendar();

        BasePage.logger.debug("Getting the final date from the input text and performing checks");
        LocalDate previousYear = today.minusYears(1);
        String prevYearDate = datePickerPage.getDatePickerAttr("value");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        softAssert.assertThat(prevYearDate)
                .withFailMessage("Actual date %s is not equal to the expected date %s",
                        prevYearDate, expectedDate)
                .isEqualTo(expectedDate);

        softAssert.assertAll();
    }
}