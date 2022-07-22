package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        openPage(FORMY_HOME);
    }

    public HomePage(String browser, int timeoutInSec) {
        this(browser);
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
