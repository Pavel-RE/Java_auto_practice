package org.example.ui.asserts;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;
import org.example.ui.pages.MainPage;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class MainPageAssert extends AbstractAssert<MainPageAssert, MainPage> {

    public MainPageAssert(MainPage mainPage) {
        super(mainPage, MainPageAssert.class);
    }

    @Step("Проверить, что кнопка корзины видна")
    public MainPageAssert cartButtonIsVisible() {
        actual.cartButton.should(visible);
        return this;
    }

    @Step("Проверить, что кнопка корзины не видна")
    public MainPageAssert cartButtonIsNotVisible() {
        actual.cartButton.shouldNot(visible);
        return this;
    }

    @Step("Проверить, что поле ввода [{index}] содержит значение '{value}'")
    public MainPageAssert countInputContainsValue(int index, String value) {
        actual.productCountInputList.get(index).should(value(value));
        return this;
    }

    @Step("Проверить, что количество карточек товаров = {count}")
    public MainPageAssert productCardSizeIsEquals(int count) {
        actual.productCardList.should(size(count));
        return this;
    }

    @Step("Проверить, что в списке товаров есть '{name}'")
    public MainPageAssert productNameListContainsProduct(String name) {
        actual.productNameList.shouldHave(itemWithText(name));
        return this;
    }
}