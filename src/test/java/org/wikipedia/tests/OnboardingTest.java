package org.wikipedia.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class OnboardingTest extends BaseTest{

    @Test
    @DisplayName("Прохождение онбординга")
    void searchTest() {
        step("Переход к экрану 'New ways to explore'", () -> {
            $(id("org.wikipedia:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia:id/primaryTextView")).shouldHave(text("New ways to explore"));

        });
        step("Переход к экрану 'Reading lists with sync'", () -> {
            $(id("org.wikipedia:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
        });
        step("Переход к экрану 'Send anonymous data'", () -> {
            $(id("org.wikipedia:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia:id/primaryTextView")).shouldHave(text("Send anonymous data"));
        });
        step("Завершение онбординга", () -> {
            $(id("org.wikipedia:id/fragment_onboarding_done_button")).click();
            $(id("org.wikipedia:id/main_toolbar_wordmark")).shouldBe(visible);
        });
    }
}
