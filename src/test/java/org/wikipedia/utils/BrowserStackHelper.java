package org.wikipedia.utils;

import org.wikipedia.models.BrowserStackSessionInfo;

import static io.restassured.RestAssured.given;
import static org.wikipedia.config.Prop.PROP;

public class BrowserStackHelper {

    public static BrowserStackSessionInfo getSessionInfo(String sessionId) {
        return given()
                .baseUri("https://api.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .auth().basic(PROP.getBrowserStackUser(), PROP.getBrowserStackPassword())
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().as(BrowserStackSessionInfo.class);
    }

    public static String getBrowserStackLog(String url) {
        return given()
                .baseUri(url)
                .auth().basic(PROP.getBrowserStackUser(), PROP.getBrowserStackPassword())
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().asString();
    }
}
