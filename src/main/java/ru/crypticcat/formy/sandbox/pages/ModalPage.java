package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testcontainers.containers.BrowserWebDriverContainer;

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

    public ModalPage(BrowserWebDriverContainer webDriverContainer) {
        super(webDriverContainer);
        PageFactory.initElements(driver, this);
    }

    public ModalPage(BrowserWebDriverContainer webDriverContainer, int timeoutInSec) {
        this(webDriverContainer);
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
