package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.Capabilities;
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

    public DisabledEnabledPage(Capabilities options) {
        super(options);
        PageFactory.initElements(driver, this);
    }

    public DisabledEnabledPage(Capabilities options, int timeoutInSec) {
        this(options);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public boolean isFirstElemEnabled(){
        return firstElem.isEnabled();
    }

    public boolean isSecondElemEnabled(){
        return secondElem.isEnabled();
    }
}
