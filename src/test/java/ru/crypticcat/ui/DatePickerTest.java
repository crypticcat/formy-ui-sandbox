package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.DatePickerPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.crypticcat.formy.sandbox.pages.BasePage.DATEPICKER_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DatePickerTest extends BaseTest {
    public LocalDate today = LocalDate.now();

    private static DatePickerPage datePickerPage;

    @BeforeEach
    void setup() {
        datePickerPage = new DatePickerPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        datePickerPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Datepicker page")
    void performDatePickerChecks() {
        SoftAssertions softAssert = new SoftAssertions();

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

        softAssert.assertAll();
    }
}