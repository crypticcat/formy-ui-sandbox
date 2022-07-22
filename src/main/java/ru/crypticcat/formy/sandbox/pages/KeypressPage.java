package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KeypressPage extends BasePage {
    @FindBy(xpath = "//input[@id='name']")
    @CacheLookup
    WebElement fullNameField;

    public KeypressPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        openPage(FORMY_HOME + KEYPRESS_ENDPOINT);
    }

    public KeypressPage(String browser, int timeoutInSec) {
        this(browser);
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
