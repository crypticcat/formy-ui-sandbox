package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage extends BasePage {
    @FindBy(xpath = "//img[@alt='Selenium logo']")
    @CacheLookup
    WebElement picToDrop;

    @FindBy(xpath = "//*[@id='box']")
    @CacheLookup
    WebElement box;

    public DragAndDropPage(Capabilities options) {
        super(options);
        PageFactory.initElements(driver, this);
    }

    public DragAndDropPage(Capabilities options, int timeoutInSec) {
        this(options);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public String getBoxText() {
        return box.getText();
    }

    public void performDragAndDrop(){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(picToDrop, box).build().perform();
    }
}
