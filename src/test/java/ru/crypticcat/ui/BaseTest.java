package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import ru.crypticcat.formy.sandbox.pages.*;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BaseTest {
    protected static final Logger LOG = getLogger(lookup().lookupClass());
    static SoftAssertions softAssert = new SoftAssertions();
    static BasePage basePage;
    static CheckboxesPage checkboxesPage;
    static DatePickerPage datePickerPage;
    static DisabledEnabledPage disabledEnabledPage;
    static DragAndDropPage dragAndDropPage;
    static DropdownPage dropdownPage;
    static FileUploadPage fileUploadPage;
    static HomePage homePage;
    static KeypressPage keypressPage;
    static ModalPage modalPage;
    static RadiobuttonsPage radiobuttonsPage;
    static ScrollPage scrollPage;
    static SwitchWindowPage switchWindowPage;

    @BeforeAll
    static void setup() {
        Capabilities chromeOptions = new ChromeOptions();
        BasePage.setupGrid(Browser.CHROME.browserName());
        checkboxesPage = new CheckboxesPage(chromeOptions);
        datePickerPage = new DatePickerPage(chromeOptions);
        disabledEnabledPage = new DisabledEnabledPage(chromeOptions);
        dragAndDropPage = new DragAndDropPage(chromeOptions);
        dropdownPage = new DropdownPage(chromeOptions);
        fileUploadPage = new FileUploadPage(chromeOptions);
        homePage = new HomePage(chromeOptions);
        keypressPage = new KeypressPage(chromeOptions);
        modalPage = new ModalPage(chromeOptions);
        radiobuttonsPage = new RadiobuttonsPage(chromeOptions);
        scrollPage = new ScrollPage(chromeOptions);
        switchWindowPage = new SwitchWindowPage(chromeOptions);
    }

    @AfterAll
    static void teardown() {
        checkboxesPage.quit();
        datePickerPage.quit();
        disabledEnabledPage.quit();
        dropdownPage.quit();
        dropdownPage.quit();
        fileUploadPage.quit();
        homePage.quit();
        keypressPage.quit();
        modalPage.quit();
        radiobuttonsPage.quit();
        scrollPage.quit();
        switchWindowPage.quit();

        System.clearProperty("webdriver.remote.server");
        softAssert.assertAll();
    }
}