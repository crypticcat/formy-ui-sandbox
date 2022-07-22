package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DropdownPage extends BasePage {
    @FindBy(xpath = "//button[@id='dropdownMenuButton']")
    @CacheLookup
    WebElement dropdown;

    @FindBy(xpath = "//a[@class='dropdown-item' and @id = 'autocomplete']")
    @CacheLookup
    WebElement firstOption;

    public DropdownPage(String browser, int timeoutInSec) {
        this(browser);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public DropdownPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        openPage(FORMY_HOME + DROPDOWN_ENDPOINT);
    }
    public boolean isDropdownDisplayed() {
        return isDisplayed(ExpectedConditions.visibilityOf(dropdown));
    }

    public void clickDropdown() {
        click(dropdown);
    }

    public String getAttrForFirstOption(String attrName) {
        return firstOption.getAttribute(attrName);
    }
}
