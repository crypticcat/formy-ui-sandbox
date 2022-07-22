package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DisabledEnabledPage extends BasePage {

    @FindBy(xpath = "//input[@type='text' and @disabled]")
    @CacheLookup
    WebElement firstElem;

    @FindBy(css = "[type=text]:not(:disabled)")
    @CacheLookup
    WebElement secondElem;

    public DisabledEnabledPage(String browser, int timeoutInSec) {
        this(browser);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public DisabledEnabledPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        openPage(FORMY_HOME+ENABLED_ENDPOINT);
    }

    public boolean isFirstElemEnabled(){
        return firstElem.isEnabled();
    }

    public boolean isSecondElemEnabled(){
        return secondElem.isEnabled();
    }
}
