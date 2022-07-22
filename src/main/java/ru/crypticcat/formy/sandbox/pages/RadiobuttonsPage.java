package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RadiobuttonsPage extends BasePage {

    @FindBy(xpath = "//input[@type='radio' and @checked]")
    @CacheLookup
    WebElement radio1;

    @FindBy(xpath = "//input[@type='radio' and @value='option2']")
    @CacheLookup
    WebElement radio2;

    @FindBy(xpath = "//input[@type='radio' and @value='option3']")
    @CacheLookup
    WebElement radio3;

    public RadiobuttonsPage(String browser, int timeoutInSec) {
        this(browser);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public RadiobuttonsPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        openPage(FORMY_HOME + RADIOBUTTON_ENDPOINT);
    }

    public void clickSecondRadio() {
        click(radio2);
    }

    public boolean isFirstRadioSelected() {
        return radio1.isSelected();
    }

    public boolean isSecondRadioSelected() {
        return radio2.isSelected();
    }

    public boolean isThirdRadioSelected() {
        return radio3.isSelected();
    }

    public String getDomAttrForFirstRadio(String attrName) {
        return radio1.getDomAttribute(attrName);
    }

    public String getDomAttrForSecondRadio(String attrName) {
        return radio2.getDomAttribute(attrName);
    }

    public String getDomAttrForThirdRadio(String attrName) {
        return radio3.getDomAttribute(attrName);
    }
}
