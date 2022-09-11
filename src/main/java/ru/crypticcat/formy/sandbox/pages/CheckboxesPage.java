package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class CheckboxesPage extends BasePage {
    //example how to find elements by advanced XPATH
    @FindBy(xpath = "//input[@type='checkbox' and @id='checkbox-1']")
    @CacheLookup
    WebElement checkbox1;

    @FindBy(xpath = "//input[@type='checkbox' and @id='checkbox-2']")
    @CacheLookup
    WebElement checkbox2;

    @FindBy(xpath = "//input[@type='checkbox' and @id='checkbox-3']")
    @CacheLookup
    WebElement checkbox3;

    public CheckboxesPage(BrowserWebDriverContainer webDriverContainer) {
        super(webDriverContainer);
        PageFactory.initElements(driver, this);
    }

    public CheckboxesPage(BrowserWebDriverContainer webDriverContainer, int timeoutInSec) {
        this(webDriverContainer);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public void clickFirstCheckbox() {
        click(checkbox1);
    }

    public void clickThirdCheckbox() {
        click(checkbox3);
    }

    public boolean isFirstCheckboxSelected() {
        return checkbox1.isSelected();
    }

    public boolean isSecondCheckboxSelected() {
        return checkbox2.isSelected();
    }

    public boolean isThirdCheckboxSelected() {
        return checkbox3.isSelected();
    }
}
