package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class MainPageAssert extends AbstractAssert<MainPageAssert, MainPage> {

    public MainPageAssert(MainPage mainPage) {
        super(mainPage, MainPageAssert.class);
    }

    @Step("Проверить, что кнопка корзины видна")
    public void cartButtonIsVisible() {
        actual.cartButton.should(visible);
    }

    @Step("Проверить, что кнопка корзины не видна")
    public void cartButtonIsNotVisible() {
        actual.cartButton.shouldNot(visible);
    }

    @Step("Проверить, что поле ввода [{index}] содержит значение '{value}'")
    public void countInputContainsValue(int index, String value) {
        actual.productCountInputList.get(index)
                .should(value(value));
    }

    @Step("Проверить, что количество карточек товаров = {count}")
    public void productCardSizeIsEquals(int count) {
        actual.productCardList.should(size(count));
    }

    @Step("Проверить, что в списке товаров есть '{name}'")
    public void productNameListContainsProduct(String name) {
        actual.productNameList.shouldHave(itemWithText(name));
    }

    public MainPage page() {
        return actual;
    }
}