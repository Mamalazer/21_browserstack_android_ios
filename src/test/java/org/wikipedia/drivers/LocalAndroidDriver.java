package org.wikipedia.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static org.wikipedia.config.Prop.PROP;

public class LocalAndroidDriver implements WebDriverProvider {

    @Override
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setPlatformVersion(PROP.getLocalAndroidVersion());
        options.setDeviceName(PROP.getLocalAndroidDevice());
        options.setNewCommandTimeout(Duration.ofSeconds(11));
        options.setFullReset(false);
        options.setApp(new File(PROP.getLocalAndroidAppPath()).getAbsolutePath());
        options.setAppPackage(PROP.getLocalAndroidAppPackage());
        options.setAppActivity(PROP.getLocalAndroidAppActivity());

        try {
            return new AndroidDriver(new URL(PROP.getLocalUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
