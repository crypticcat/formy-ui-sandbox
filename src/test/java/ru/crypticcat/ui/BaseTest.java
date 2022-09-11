package ru.crypticcat.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.crypticcat.formy.sandbox.pages.*;

import java.io.File;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@Testcontainers
public class BaseTest {
    @Container
    static BrowserWebDriverContainer CHROME_CONTAINER = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING,
                    new File("./build/"),
                    VncRecordingContainer.VncRecordingFormat.MP4);


    protected static final Logger LOG = getLogger(lookup().lookupClass());

}