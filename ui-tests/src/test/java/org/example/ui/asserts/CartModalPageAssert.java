package org.example.ui.asserts;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.example.ui.pages.CartModalPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CartModalPageAssert extends AbstractAssert<CartModalPageAssert, CartModalPage> {

    public CartModalPageAssert(CartModalPage cartModalPage) {
        super(cartModalPage, CartModalPageAssert.class);
    }

    @Step("Проверить, что итоговая сумма равна {expectedTotal}")
    public CartModalPageAssert totalPriceEquals(int expectedTotal) {
        actual.totalPrice.shouldHave(text(String.valueOf(expectedTotal)));
        return this;
    }

    @Step("Проверить, что итоговая сумма ≤ {max}")
    public CartModalPageAssert totalPriceLessOrEqual(int max) {
        int actualTotal = Integer.parseInt(actual.totalPrice.getText());
        Assertions.assertThat(actualTotal)
                .as("Сумма в корзине должна быть меньше или равна " + max)
                .isLessThanOrEqualTo(max);
        return this;
    }

    @Step("Проверить, что тост 'Заказ принят в обработку!' виден")
    public CartModalPageAssert toastIsVisible() {
        actual.toast.shouldBe(visible);
        return this;
    }
}