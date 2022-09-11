package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class KeypressPage extends BasePage {
    @FindBy(xpath = "//input[@id='name']")
    @CacheLookup
    WebElement fullNameField;

    public KeypressPage(BrowserWebDriverContainer webDriverContainer) {
        super(webDriverContainer);
        PageFactory.initElements(driver, this);
    }

    public KeypressPage(BrowserWebDriverContainer webDriverContainer, int timeoutInSec) {
        this(webDriverContainer);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public String getFullNameFieldValue() {
        return fullNameField.getAttribute("value");
    }

    public void enterValueIntoNameField(String textToEnter) {
        fullNameField.sendKeys(textToEnter);
    }

    public void clearFullNameField() {
        fullNameField.clear();
    }
}
