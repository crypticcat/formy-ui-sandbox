package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.FileUploadPage;

import java.io.IOException;

public class FileUploadTest {
    SoftAssertions softAssert = new SoftAssertions();

    FileUploadPage fileUploadPage;

    @BeforeEach
    void setup() {
        fileUploadPage = new FileUploadPage("chrome");
    }

    @AfterEach
    public void teardown() {
        fileUploadPage.quit();
    }

    @Test
    void performFileUpload() throws IOException {
        BasePage.logger.debug("Creating temporal file anf uploading it");
        String fileName = fileUploadPage.uploadFile();
        softAssert.assertThat(fileUploadPage.isFileNameDisplayed(fileName))
                        .withFailMessage("File name is not displayed, should be %s", fileName)
                .isTrue();

        softAssert.assertThat(fileUploadPage.getFileUploadFieldAtr("value"))
                .withFailMessage("File name actual value is %s",
                        fileUploadPage.getFileUploadFieldAtr("value"))
                .isEqualTo(fileName);

        BasePage.logger.debug("Resetting uploaded file");
        fileUploadPage.resetUploadedFile();
        softAssert.assertThat(fileUploadPage.isFileNameDisplayed(fileName))
                .withFailMessage("File name %s is still present in File Upload field", fileName)
                .isFalse();

        softAssert.assertAll();
    }
}
