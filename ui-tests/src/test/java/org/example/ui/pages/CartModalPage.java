package org.example.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartModalPage {

    public SelenideElement
            closeCartButton = $x("//*[@id='close-modal']"),
            makeOrderButton = $x("//*[@id='makeOrder']"),
            totalPrice = $x("//*[@id='total-price']"),
            toast = $x("//div[@class='toast' and contains(., 'Заказ принят в обработку!')]");

    public ElementsCollection
            cartItemList = $$x("//*[contains(@id, 'cart-item-')]");

    @Step("Нажать 'Оформить заказ'")
    public CartModalPage makeOrderClick() {
        makeOrderButton.shouldBe(visible).click();
        return this;
    }
}