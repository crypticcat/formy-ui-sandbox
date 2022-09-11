package ru.crypticcat.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.crypticcat.formy.sandbox.pages.DragAndDropPage;

import static ru.crypticcat.formy.sandbox.pages.BasePage.DRAGDROP_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DragAndDropTest extends BaseTest {
    private static DragAndDropPage dragAndDropPage;

    @BeforeEach
    void setup() {
        dragAndDropPage = new DragAndDropPage(CHROME_CONTAINER);
    }

    @AfterEach
    void teardown() {
        dragAndDropPage.quit();
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Description("Perform checks for Drag and Drop page")
    void testDragAndDrop() {
        SoftAssertions softAssert = new SoftAssertions();

        LOG.debug("Performing checks for the Drag&Drop box");
        dragAndDropPage.openPage(FORMY_HOME + DRAGDROP_ENDPOINT);
        softAssert.assertThat(dragAndDropPage.getBoxText()).isEqualTo("Drop here");

        LOG.debug("Moving the picture 100 px to the box");
        dragAndDropPage.performDragAndDrop();
        softAssert.assertThat(dragAndDropPage.getBoxText()).isEqualTo("Dropped!");

        softAssert.assertAll();
    }
}