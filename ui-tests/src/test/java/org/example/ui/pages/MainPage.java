package org.example.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.common.config.Config;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public String mainPageURL = Config.INSTANCE.url();

    public SelenideElement
            cartButton = $x("//*[@id='open-cart-btn']"),
            closeCart = $x("//*[@id='close-modal']"),
            addToCartButton = $x("//*[@data-action='add-to-cart']");

    public ElementsCollection
            productCardList = $$x("//*[contains(@id, 'card')]"),
            addToCardButtonList = $$x("//*[@data-action='add-to-cart']"),
            productNameList = $$x("//h4"),
            productCountInputList = $$x("//*[@type='number']");

    @Step("Нажать на кнопку корзины")
    public MainPage clickCartButton() {
        cartButton.click();
        return this;
    }

    @Step("Проверить, что кнопка корзины видна")
    public MainPage checkCartButtonIsVisible() {
        cartButton.should(visible);
        return this;
    }

    @Step("Ввести количество товара [{index}]: {text}")
    public MainPage inputProductCount(int index, String text) {
        productCountInputList.get(index).clear();
        productCountInputList.get(index).sendKeys(text);
        return this;
    }

    @Step("Нажать клавишу {keys} в поле количества [{index}]")
    public MainPage inputProductCount(int index, Keys keys) {
        productCountInputList.get(index).sendKeys(keys);
        return this;
    }

    @Step("Закрыть корзину")
    public MainPage clickCloseCartButton() {
        closeCart.click();
        return this;
    }

    @Step("Добавить в корзину товар с индексом {index}")
    public MainPage addToCart(int index) {
        addToCardButtonList.get(index).click();
        return this;
    }

    @Step("Добавить в корзину товар с id={productId}")
    public MainPage addToCartById(int productId) {
        $x("//button[@data-id='" + productId + "' and @data-action='add-to-cart']").click();
        return this;
    }

    @Step("Получить цену товара с индексом {index}")
    public int getProductPrice(int index) {
        String priceText = productCardList.get(index).getText();
        return Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
    }
}