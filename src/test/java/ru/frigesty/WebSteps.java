package ru.frigesty;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://myshows.me/");
    }

    @Step("Кликаем по кнопке Сообщество")
    public void clickOnPageFriendsOnFooter() {
        $$(".Footer__menus .text-menu-item__label").findBy(text("Друзья")).click();
    }

    @Step("Ищем пользователя {user} в поиске друзей")
    public void lookingForUserInFriendSearch(String user) {
        $(".SearchField__input").sendKeys(user);
        $(".SearchField__input").submit();
    }

    @Step("Кликаем по пользователю {user}")
    public void clickOnUser(String user) {
        $(".UserItemList").$(byText(user)).click();
    }

    @Step("Кликаем по кнопке Перестал")
    public void clickOnTheStopWatchingButton() {
        $(".Tabs-container").$(byText("Перестал")).click();
    }

    @Step("Кликаем по кнопке Показать больше")
    public void clickOnTheShowMoreButton() {
        $(".UserShowsBlock__button-more").click();
    }

    @Step("Проверяем что в блоке с сериалами с надписью Перестал присутствует сериал {content}")
    public void checkThatInTheBlockWithSeriesWithTheInscriptionStoppedThereIsASeries(String content) {
        $(".UserShowsBlock").shouldHave(text(content));
    }
}