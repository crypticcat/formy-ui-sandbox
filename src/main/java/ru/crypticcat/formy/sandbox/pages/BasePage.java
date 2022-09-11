package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.time.Duration;
import java.util.Set;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BasePage {
    protected static final Logger LOG = getLogger(lookup().lookupClass());
    //one more variant for soft assertions:
    //public List<Executable> ex = new ArrayList();
    long timeoutInSec = 5;
    Duration duration = Duration.ofSeconds(timeoutInSec);
    protected RemoteWebDriver driver;

    WebDriverWait wait;

    public static final String FORMY_HOME = "https://formy-project.herokuapp.com/";
    public static final String CHECKBOX_ENDPOINT = "checkbox";
    public static final String DATEPICKER_ENDPOINT = "datepicker";
    public static final String DRAGDROP_ENDPOINT = "dragdrop";
    public static final String DROPDOWN_ENDPOINT = "dropdown";
    public static final String ENABLED_ENDPOINT = "enabled";

    public static final String FILEUPLOAD_ENDPOINT = "fileupload";

    public static final String KEYPRESS_ENDPOINT = "keypress";
    public static final String MODAL_ENDPOINT = "modal";

    public static final String PAGESCROLL_ENDPOINT = "scroll";
    public static final String RADIOBUTTON_ENDPOINT = "radiobutton";
    public static final String SWITCHWINDOW_ENDPOINT = "switch-window";

    public BasePage(BrowserWebDriverContainer webDriverContainer) {
        driver = webDriverContainer.getWebDriver();
        wait = new WebDriverWait(driver, duration);
    }

    public void setDefaultTimeoutSec(int timeoutInSec) {
        this.timeoutInSec = timeoutInSec;
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public WebElement findElemBy(By locator) {
        return driver.findElement(locator);
    }

    public void click(WebElement elem) {
        elem.click();
    }

    public void findAndClick(By locator) {
        click(findElemBy(locator));
    }

    public void submit(WebElement elem) {
        elem.submit();
    }

    public void findAndSubmit(By locator) {
        submit(findElemBy(locator));
    }

    public void type(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void findAndType(By locator, String text) {
        type(findElemBy(locator), text);
    }

    public Alert getAlertIfPresent() {
        if (isDisplayed(ExpectedConditions.alertIsPresent())) {
            return wait.until(ExpectedConditions.alertIsPresent());
        } else {
            return null;
        }
    }

    public boolean isDisplayed(WebElement element) {
        return isDisplayed(ExpectedConditions.visibilityOf(element));
    }

    public boolean isDisplayed(By locator) {
        return isDisplayed(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isDisplayed(ExpectedCondition<?> expectedCondition) {
        try {
            wait.until(expectedCondition);
        } catch (TimeoutException e) {
            LOG.warn("Timeout of {} seconds for element ", timeoutInSec);
            return false;
        }
        return true;
    }

    public String takeScreenshotInBase64() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String screenshot = null;
        try {
            screenshot = ts.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            LOG.warn("Timeout of {} seconds for element ", timeoutInSec);
        }
        return screenshot;
    }

    public Set<Cookie> readCookie() {
        WebDriver.Options options = driver.manage();
        return options.getCookies();
    }

    public Cookie getCookieWithName(String cookieName) {
        WebDriver.Options options = driver.manage();
        return options.getCookieNamed(cookieName);
    }

    public Window manageWindow() {
        return driver.manage().window();
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}