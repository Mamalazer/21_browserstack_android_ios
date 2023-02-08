package org.wikipedia.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.wikipedia.drivers.BrowserStackAndroidDriver;
import org.wikipedia.drivers.BrowserStackIosDriver;
import org.wikipedia.drivers.LocalAndroidDriver;
import org.wikipedia.models.BrowserStackSessionInfo;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.wikipedia.config.Prop.PROP;
import static org.wikipedia.utils.AllureAttachmentHelper.*;
import static org.wikipedia.utils.BrowserStackHelper.getSessionInfo;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 15000;
        Configuration.browserSize = null;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void openBrowser() {
        if (PROP.getMobilePlatform().equals("android")) {
            Configuration.browser = BrowserStackAndroidDriver.class.getName();
        } else if (PROP.getMobilePlatform().equals("ios")){
            Configuration.browser = BrowserStackIosDriver.class.getName();
        } else {
            Configuration.browser = LocalAndroidDriver.class.getName();
        }
        SelenideAppium.launchApp();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        screenshotAs("Last screenshot");
        pageSource();
        closeWebDriver();
        if (!PROP.getMobilePlatform().equals("local")) {
            BrowserStackSessionInfo sessionInfo = getSessionInfo(sessionId);
            getVideo(sessionInfo);
            logs(sessionInfo);
            privateLink(sessionInfo);
            publicLink(sessionInfo);
        }
    }
}
