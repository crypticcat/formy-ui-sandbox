package ru.crypticcat.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.crypticcat.formy.sandbox.pages.BasePage.DATEPICKER_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DatePickerTest extends BaseTest {
    public LocalDate today = LocalDate.now();

    @Test
    @DisplayName("Perform checks for Datepicker page")
    void performDatePickerChecks() {
        LOG.debug("Verifying the placeholder is displayed in the date picker field");
        datePickerPage.openPage(FORMY_HOME + DATEPICKER_ENDPOINT);
        String datePickerPlaceholder = datePickerPage.getDatePickerAttr("placeholder");
        softAssert.assertThat(datePickerPlaceholder)
                .withFailMessage("Placeholder includes: %s", datePickerPlaceholder)
                .isEqualTo("mm/dd/yyyy");

        LOG.debug("Opening the calendar; verifying that the current year is displayed");
        int currentYear = today.getYear();
        datePickerPage.clickDatePicker();
        softAssert.assertThat(datePickerPage.isDatePickerSwitchDisplayed())
                .withFailMessage("Calendar is not displayed")
                .isTrue();
        softAssert.assertThat(datePickerPage.getDatePickerSwitchText())
                .withFailMessage("Date picker switch does not include the current year, but %s",
                        datePickerPage.getDatePickerSwitchText())
                .contains(String.valueOf(currentYear));

        LOG.debug("Selecting the previous year date");
        datePickerPage.selectPrevYearDateInCalendar();

        LOG.debug("Getting the final date from the input text and performing checks");
        LocalDate previousYear = today.minusYears(1);
        String prevYearDate = datePickerPage.getDatePickerAttr("value");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        softAssert.assertThat(prevYearDate)
                .withFailMessage("Actual date %s is not equal to the expected date %s",
                        prevYearDate, expectedDate)
                .isEqualTo(expectedDate);
    }
}