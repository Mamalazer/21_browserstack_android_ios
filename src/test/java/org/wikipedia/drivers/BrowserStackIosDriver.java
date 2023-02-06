package org.wikipedia.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static org.wikipedia.config.Prop.PROP;

public class BrowserStackIosDriver implements WebDriverProvider {

    @Override
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        XCUITestOptions options = new XCUITestOptions();
        options.merge(capabilities);
        options.setAutomationName("XCUITest");
        options.setPlatformName("ios");
        options.setPlatformVersion(PROP.getIosversion());
        options.setDeviceName(PROP.getIosDevice());
        options.setApp(PROP.getIosApp());
        options.setCapability("build", PROP.getBuildName());

        try {
            return new IOSDriver(new URL("http://" + PROP.getBrowserStackUser() + ":" +
                    PROP.getBrowserStackPassword() + "@hub-cloud.browserstack.com/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
