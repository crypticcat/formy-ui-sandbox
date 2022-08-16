package ru.crypticcat.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FILEUPLOAD_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class FileUploadTest extends BaseTest {
    @Test
    @Disabled
    @DisplayName("Perform checks for File Upload page")
    void performFileUpload() throws IOException {
        LOG.debug("Creating temporal file anf uploading it");
        fileUploadPage.openPage(FORMY_HOME + FILEUPLOAD_ENDPOINT);
        String fileName = fileUploadPage.uploadFile();
        softAssert.assertThat(fileUploadPage.isFileNameDisplayed(fileName))
                .withFailMessage("File name is not displayed, should be %s", fileName)
                .isTrue();

        softAssert.assertThat(fileUploadPage.getFileUploadFieldAtr("value"))
                .withFailMessage("File name actual value is %s",
                        fileUploadPage.getFileUploadFieldAtr("value"))
                .isEqualTo(fileName);

        LOG.debug("Resetting uploaded file");
        fileUploadPage.resetUploadedFile();
        softAssert.assertThat(fileUploadPage.isFileNameDisplayed(fileName))
                .withFailMessage("File name %s is still present in File Upload field", fileName)
                .isFalse();
    }
}
