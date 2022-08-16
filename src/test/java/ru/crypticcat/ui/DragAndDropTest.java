package ru.crypticcat.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.crypticcat.formy.sandbox.pages.BasePage.DRAGDROP_ENDPOINT;
import static ru.crypticcat.formy.sandbox.pages.BasePage.FORMY_HOME;

public class DragAndDropTest extends BaseTest {
    @Test
    @Disabled
    @DisplayName("Perform checks for Drag and Drop page")
    void testDragAndDrop() {
        LOG.debug("Performing checks for the Drag&Drop box");
        dragAndDropPage.openPage(FORMY_HOME + DRAGDROP_ENDPOINT);
        softAssert.assertThat(dragAndDropPage.getBoxText()).isEqualTo("Drop here");

        LOG.debug("Moving the picture 100 px to the box");
        dragAndDropPage.performDragAndDrop();
        softAssert.assertThat(dragAndDropPage.getBoxText()).isEqualTo("Dropped!");
    }
}