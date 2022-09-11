package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.FileUploadPage;

import java.io.IOException;

import static ru.crypticcat.formy.sandbox.pages.BasePage.FILEUPLOAD_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class FileUploadTest extends BaseTest {
    private static FileUploadPage fileUploadPage;

    @BeforeEach
    void setup() {
        fileUploadPage = new FileUploadPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        fileUploadPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for File Upload page")
    void performFileUpload() throws IOException {
        SoftAssertions softAssert = new SoftAssertions();

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

        softAssert.assertAll();
    }
}
