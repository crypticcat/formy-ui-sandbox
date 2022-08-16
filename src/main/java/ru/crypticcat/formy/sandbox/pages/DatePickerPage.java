package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DatePickerPage extends BasePage {
    @FindBy(xpath = "//input[@type='text' and @id='datepicker']")
    @CacheLookup
    WebElement datePicker;

    @FindBy(xpath = "//th[contains(@class, 'datepicker-switch')]")
    @CacheLookup
    WebElement datePickerSwitch;

    @FindBy(xpath = "//th[contains(@class, 'prev')]")
    @CacheLookup
    WebElement arrowLeft;
    //Note: former example without PageFactory and with relative locators:
    //public static final By ARROW_LEFT_LOC = RelativeLocator.with(By.tagName("th")).toRightOf(MONTH_ELEM_LOC);

    @FindBy(xpath = "//th[contains(@class, 'next')]")
    @CacheLookup
    WebElement arrowRight;

    @FindBy(xpath = "//span[contains(@class, 'focused')]")
    @CacheLookup
    WebElement prevYearMonth;
    //Note: former example without PageFactory and with relative locators:
    //public static final By PREV_YEAR_MONTH_LOC = RelativeLocator
    //            .with(By.xpath("//span[contains(@class, 'focused')]")).below(ARROW_LEFT_LOC);

    @FindBy(xpath = "//td[contains(@class, 'today')]")
    @CacheLookup
    WebElement currentDayElem;

    public DatePickerPage(Capabilities options) {
        super(options);
        PageFactory.initElements(driver, this);
    }

    public DatePickerPage(Capabilities options, int timeoutInSec) {
        this(options);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public String getDatePickerAttr(String attrName) {
        return datePicker.getAttribute(attrName);
    }

    public void clickDatePicker() {
        click(datePicker);
    }

    public boolean isDatePickerSwitchDisplayed() {
        return isDisplayed(datePickerSwitch);
    }

    public String getDatePickerSwitchText() {
        return datePickerSwitch.getText();
    }

    public void selectPrevYearDateInCalendar() {
        click(datePickerSwitch);
        if (isLeftArrowDisplayed()) {
            click(arrowLeft);
        }
        click(prevYearMonth);
        click(currentDayElem);
    }

    public boolean isLeftArrowDisplayed() {
        return isDisplayed(ExpectedConditions.visibilityOf(arrowLeft));
    }
}