package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KeypressPage extends BasePage {
    @FindBy(xpath = "//input[@id='name']")
    @CacheLookup
    WebElement fullNameField;

    public KeypressPage(Capabilities options) {
        super(options);
        PageFactory.initElements(driver, this);
    }

    public KeypressPage(Capabilities options, int timeoutInSec) {
        this(options);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public String getFullNameFieldValue() {
        return fullNameField.getAttribute("value");
    }

    public void enterValueIntoNameField(String textToEnter){
        fullNameField.sendKeys(textToEnter);
    }

    public void clearFullNameField(){
        fullNameField.clear();
    }
}
