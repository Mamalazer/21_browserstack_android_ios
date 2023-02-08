package org.wikipedia.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

public class Prop {

    private static Class<? extends PropInterface> getPropertySource() {
        String env = System.getProperty("env");
        if (env == null || env.equals("null")) {
            return PropInterfaceTest.class;
        } else if (env.equals("test")) {
            return PropInterfaceTest.class;
        } else {
            throw new RuntimeException("Invalid value for system property 'env'! Expected one of:[null, 'test']");
        }
    }

    public static final PropInterface PROP = ConfigFactory.create(getPropertySource());

    @LoadPolicy(LoadType.MERGE)
    @Sources({"system:properties", "classpath:test.properties"})
    interface PropInterfaceTest extends PropInterface {
    }

    public interface PropInterface extends Config {

        @Key("browserstack.user")
        String getBrowserStackUser();

        @Key("browserstack.password")
        String getBrowserStackPassword();

        @Key("android.version")
        String getAndroidversion();

        @Key("android.device")
        String getAndroidDevice();

        @Key("android.app")
        String getAndroidApp();

        @Key("build.name")
        String getBuildName();

        @Key("ios.app")
        String getIosApp();

        @Key("ios.device")
        String getIosDevice();

        @Key("ios.version")
        String getIosversion();

        @Key("mobileplatform")
        String getMobilePlatform();

        @Key("local.url")
        String getLocalUrl();

        @Key("android.local.version")
        String getLocalAndroidVersion();

        @Key("android.local.device")
        String getLocalAndroidDevice();

        @Key("android.local.app.path")
        String getLocalAndroidAppPath();

        @Key("android,local.apppackage")
        String getLocalAndroidAppPackage();

        @Key("android.local.appactivity")
        String getLocalAndroidAppActivity();
    }
}
