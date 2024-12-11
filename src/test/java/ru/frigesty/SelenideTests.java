package ru.frigesty;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Feature("Сериал во вкладке")
@Story("Отображение сериала в блоке Перестал")
@Owner("Frigesty")
@Severity(SeverityLevel.CRITICAL)
@Link(value = "testing", url = "https://myshows.me/")
public class SelenideTests extends TestBase {

    @BeforeEach
    public void initEach(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

        private WebSteps steps = new WebSteps();

    private static final String USER = "MGW",
            CONTENT = "Сабрина - маленькая ведьма";

    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    public void clearSearchTest() {
        open("https://myshows.me/");
        $$(".Footer__menus .text-menu-item__label").findBy(text("Друзья")).click();
        $(".SearchField__input").sendKeys(USER);
        $(".SearchField__input").submit();
        $(".UserItemList").$(byText(USER)).click();
        $(".Tabs-container").$(byText("Перестал")).click();
        $(".UserShowsBlock__button-more").click();
        $(".UserShowsBlock").shouldHave(text(CONTENT));
    }

    @Test
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    public void lambdaStepTest() {

        step("Открываем главную страницу", () ->
                open("https://myshows.me/")
        );

        step("Кликаем по кнопке Друзья в Footer", () ->
                $$(".Footer__menus .text-menu-item__label").findBy(text("Друзья")).click()
        );

        step("Ищем пользователя " + USER + " в поиске друзей", () -> {
            $(".SearchField__input").sendKeys(USER);
            $(".SearchField__input").submit();
        });

        step("Кликаем по пользователю " + USER, () ->
                $(".UserItemList").$(byText(USER)).click()
        );

        step("Кликаем по кнопке Перестал", () ->
                $(".Tabs-container").$(byText("Перестал")).click()
        );

        step("Кликаем по кнопке Показать больше", () ->
                $(".UserShowsBlock__button-more").click()
        );

        step("Проверяем что в блоке с сериалами с надписью Перестал присутствует сериал " + CONTENT , () ->
                $(".UserShowsBlock").shouldHave(text(CONTENT))
        );
    }

    @Test
    @DisplayName("Шаги с аннотацией @Step")
    public void testWebSteps() {
        steps.openMainPage();
        steps.clickOnPageFriendsOnFooter();
        steps.lookingForUserInFriendSearch(USER);
        steps.clickOnUser(USER);
        steps.clickOnTheStopWatchingButton();
        steps.clickOnTheShowMoreButton();
        steps.checkThatInTheBlockWithSeriesWithTheInscriptionStoppedThereIsASeries(CONTENT);
    }
}