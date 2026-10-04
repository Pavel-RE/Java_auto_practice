package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CartModalPageAssert extends AbstractAssert<CartModalPageAssert, CartModalPage> {

    public CartModalPageAssert(CartModalPage cartModalPage) {
        super(cartModalPage, CartModalPageAssert.class);
    }

    @Step("Проверить, что итоговая сумма равна {expectedTotal}")
    public void totalPriceEquals(int expectedTotal) {
        actual.totalPrice.shouldHave(text(String.valueOf(expectedTotal)));
    }

    @Step("Проверить, что итоговая сумма ≤ {max}")
    public void totalPriceLessOrEqual(int max) {
        int actualTotal = Integer.parseInt(actual.totalPrice.getText());
        Assertions.assertThat(actualTotal)
                .as("Сумма в корзине должна быть меньше или равна " + max)
                .isLessThanOrEqualTo(max);
    }

    @Step("Проверить, что тост 'Заказ принят в обработку!' виден")
    public void toastIsVisible() {
        actual.toast.shouldBe(visible);
    }
}