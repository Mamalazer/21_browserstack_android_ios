package org.wikipedia.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.wikipedia.models.BrowserStackSessionInfo;

import java.nio.charset.StandardCharsets;

import static org.wikipedia.utils.BrowserStackHelper.getBrowserStackLog;

public class AllureAttachmentHelper {

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return WebDriverRunner.source()
                .getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String getVideo(BrowserStackSessionInfo sessionInfo) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + sessionInfo.getAutomationSession().getVideoUrl()
                + "' type='video/mp4'></video></body></html>";
    }

    @Attachment(value = "Logs", type = "text/plain")
    public static byte[] logs(BrowserStackSessionInfo sessionInfo) {
        return getBrowserStackLog(sessionInfo.getAutomationSession().getLogs())
                .getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Private link to the test run", type = "text/html", fileExtension = ".html")
    public static String privateLink(BrowserStackSessionInfo sessionInfo) {
        String browserUrl = sessionInfo.getAutomationSession().getBrowserUrl();
        return "<html><body><a href='" + browserUrl + "' target='_blank'>Private link to the test run</a></body></html>";
    }

    @Attachment(value = "Public link to the test run", type = "text/html", fileExtension = ".html")
    public static String publicLink(BrowserStackSessionInfo sessionInfo) {
        String publicUrl = sessionInfo.getAutomationSession().getPublicUrl();
        return "<html><body><a href='" + publicUrl + "' target='_blank'>Public link to the test run</a></body></html>";
    }
}
