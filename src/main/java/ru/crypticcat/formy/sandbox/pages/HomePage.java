package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class HomePage extends BasePage {

    public HomePage(BrowserWebDriverContainer webDriverContainer) {
        super(webDriverContainer);
        PageFactory.initElements(driver, this);
    }

    public HomePage(BrowserWebDriverContainer webDriverContainer, int timeoutInSec) {
        this(webDriverContainer);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public SessionId getSessionId() {
        return ((RemoteWebDriver) driver).getSessionId();
    }

    public Window manageWindow() {
        return driver.manage().window();
    }
}
