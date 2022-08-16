package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ScrollPage extends BasePage {
    @FindBy(xpath = "//input[@type='text' and @id='name']")
    @CacheLookup
    WebElement firstNameField;

    @FindBy(xpath = "//input[@type='text' and @id='date']")
    @CacheLookup
    WebElement dateField;
    public ScrollPage(Capabilities options) {
        super(options);
        PageFactory.initElements(driver, this);
    }

    public ScrollPage(Capabilities options, int timeoutInSec) {
        this(options);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public void scrollTillFirstNameField(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //synch. script.
        //we pass the located element as the first argument, therefore we call arguments[0];
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, firstNameField);
    }

    public boolean isNameFieldDisplayed(){
        return isDisplayed(firstNameField);
    }

    public boolean isDateFieldDisplayed(){
        return isDisplayed(dateField);
    }
}
