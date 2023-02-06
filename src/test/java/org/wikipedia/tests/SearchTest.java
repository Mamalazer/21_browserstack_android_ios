package org.wikipedia.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class SearchTest extends BaseTest {

    @Test
    @DisplayName("Поиск статей")
    void searchTest() {
        back();
        step("Type search", () -> {
            $(id("org.wikipedia:id/search_container")).click();
            $(id("org.wikipedia:id/search_src_text")).setValue("BrowserStack");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia:id/page_list_item_title")).shouldHave(sizeGreaterThan(0)));
    }
}
