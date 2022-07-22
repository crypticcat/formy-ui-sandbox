package ru.crypticcat.ui;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.crypticcat.formy.sandbox.pages.BasePage;
import ru.crypticcat.formy.sandbox.pages.DragAndDropPage;

public class DragAndDropTest {
    SoftAssertions softAssert = new SoftAssertions();

    DragAndDropPage dragAndDropPage;

    @BeforeEach
    void setup() {
        dragAndDropPage = new DragAndDropPage("chrome");
    }

    @AfterEach
    public void teardown() {
        dragAndDropPage.quit();
    }

    @Test
    void testDragAndDrop() {
        BasePage.logger.debug("");
        softAssert.assertThat(dragAndDropPage.getBoxText()).isEqualTo("Drop here");

        BasePage.logger.debug("Moving the picture 100 px to the box");
        dragAndDropPage.performDragAndDrop();
        softAssert.assertThat(dragAndDropPage.getBoxText()).isEqualTo("Dropped!");

        softAssert.assertAll();
    }
}
