package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class SwitchWindowPage extends BasePage {
    @FindBy(xpath = "//button[@id='alert-button']")
    @CacheLookup
    WebElement alertButton;

    @FindBy(xpath = "//button[@id='new-tab-button']")
    @CacheLookup
    WebElement newTabButton;

    public SwitchWindowPage(BrowserWebDriverContainer webDriverContainer) {
        super(webDriverContainer);
        PageFactory.initElements(driver, this);
    }

    public SwitchWindowPage(BrowserWebDriverContainer webDriverContainer, int timeoutInSec) {
        this(webDriverContainer);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public void clickAlertButton() {
        click(alertButton);
    }

    public Alert getAlert() {
        return getAlertIfPresent();
    }

    public String getInitHandle() {
        return driver.getWindowHandle();
    }

    public void switchToNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void switchToInitHandle(String initHandle) {
        driver.switchTo().window(initHandle);
    }

    public void closeWindow() {
        driver.close();
    }

    public int getWindowHandlesNum() {
        return driver.getWindowHandles().size();
    }
}
