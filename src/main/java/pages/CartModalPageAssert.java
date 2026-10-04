package pages;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CartModalPageAssert extends AbstractAssert<CartModalPageAssert, CartModalPage> {

    public CartModalPageAssert(CartModalPage cartModalPage){
        super(cartModalPage, CartModalPageAssert.class);
    }

    public void totalPriceEquals(int expectedTotal) {
        actual.totalPrice.shouldHave(text(String.valueOf(expectedTotal)));
    }

    public void totalPriceLessOrEqual(int max) {
        int actualTotal = Integer.parseInt(actual.totalPrice.getText());
        Assertions.assertThat(actualTotal)
                .as("Сумма в корзине должна быть меньше или равна " + max)
                .isLessThanOrEqualTo(max);
    }

    public void toastIsVisible() {
        actual.toast.shouldBe(visible);
    }

}
