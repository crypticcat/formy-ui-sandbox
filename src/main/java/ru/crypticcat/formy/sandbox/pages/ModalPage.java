package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModalPage extends BasePage {

    @FindBy(xpath = "//button[@id='modal-button']")
    @CacheLookup
    WebElement modalBtn;

    @FindBy(xpath = "//div[contains(@Class, 'modal-content')]")
    @CacheLookup
    WebElement modalWindow;

    @FindBy(xpath = "//button[@id='close-button']")
    @CacheLookup
    WebElement closeBtn;

    public ModalPage(Capabilities options) {
        super(options);
        PageFactory.initElements(driver, this);
    }

    public ModalPage(Capabilities options, int timeoutInSec) {
        this(options);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public void clickModalBtn() {
        click(modalBtn);
    }

    public String getCloseBtnTagName() {
        return closeBtn.getTagName();
    }

    public String getCloseBtnText() {
        return closeBtn.getText();
    }

    public void clickCloseBtn() {
        click(closeBtn);
    }

    public boolean isModalWindowDisplayed() {
        return isDisplayed(modalWindow);
    }

    public boolean isCloseBtnDisplayed() {
        return isDisplayed(ExpectedConditions.elementToBeClickable(closeBtn));
    }

    public boolean isModalWindowClosed() {
        return isDisplayed(ExpectedConditions.invisibilityOf(closeBtn));
    }

}
