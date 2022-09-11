package ru.crypticcat.formy.sandbox.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUploadPage extends BasePage {
    @FindBy(xpath = "//input[@id = 'file-upload-field']")
    @CacheLookup
    WebElement chooseFileField;
    @FindBy(xpath = "//button[contains(@class, 'reset')]")
    @CacheLookup
    WebElement resetBtn;

    public FileUploadPage(BrowserWebDriverContainer webDriverContainer) {
        super(webDriverContainer);
        PageFactory.initElements(driver, this);
    }

    public FileUploadPage(BrowserWebDriverContainer webDriverContainer, int timeoutInSec) {
        this(webDriverContainer);
        setDefaultTimeoutSec(timeoutInSec);
    }

    public String uploadFile() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".tmp");
        String fileName = tempFile.toAbsolutePath().toString();
        //String shortFileName = String.valueOf(tempFile.getFileName());
        LOG.debug("Using temporal file {} in file uploading", fileName);
        chooseFileField.sendKeys(fileName);
        return fileName;
    }

    public String getFileUploadFieldAtr(String attrName) {
        return chooseFileField.getAttribute(attrName);
    }

    public boolean isFileNameDisplayed(String shortName) {
        return isDisplayed(ExpectedConditions.textToBePresentInElementValue(chooseFileField, shortName));
    }

    public void resetUploadedFile() {
        click(resetBtn);
    }
}
