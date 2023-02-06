package org.wikipedia.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static io.appium.java_client.AppiumBy.className;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class TopicTest extends BaseTest {

    @Test
    @DisplayName("Открытие статьи")
    void openTopic() {
        back();
        step("Type search", () -> {
            $(id("org.wikipedia:id/search_container")).click();
            $(id("org.wikipedia:id/search_src_text")).setValue("BrowserStack");
        });
        step("Open topic", () ->
                $(id("org.wikipedia:id/page_list_item_title"))).click();
        step("Check topic's article", () ->
                $(className("android.widget.TextView")).shouldHave(text("BrowserStack")));
    }
}
