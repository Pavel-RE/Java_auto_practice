package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public String mainPageURL = "http://localhost:8080";

    SelenideElement
            cartButton = $x("//*[@id = 'open-cart-btn']"),
            closeCart = $x("//*[@id = 'close-modal']"),
            AddToCartButton = $x("//*[@data-action = 'add-to-cart']");

    ElementsCollection
            productCardList = $$x("//*[contains(@id, 'card')]"),
            addToCardButtonList = $$x("//*[@data-action='add-to-cart']"),
            productNameList = $$x("//h4"),
            productCountInputList = $$x("//*[@type='number']");

    @Step("Нажать на кнопку корзины")
    public void clickCartButton() {
        cartButton.click();
    }

    @Step("Проверить, что кнопка корзины видна")
    public void checkCartButtonIsVisible() {
        cartButton.should(visible);
    }

    @Step("Ввести количество товара [{index}]: {text}")
    public void inputProductCount(int index, String text) {
        productCountInputList.get(index).clear();
        productCountInputList.get(index).sendKeys(text);
    }

    @Step("Нажать клавишу {keys} в поле количества [{index}]")
    public void inputProductCount(int index, Keys keys) {
        productCountInputList.get(index).sendKeys(keys);
    }

    @Step("Закрыть корзину")
    public void clickCloseCartButton() {
        closeCart.click();
    }

    @Step("Добавить в корзину товар с индексом {index}")
    public void addToCart(int index) {
        addToCardButtonList.get(index).click();
    }

    @Step("Получить цену товара с индексом {index}")
    public int getProductPrice(int index) {
        String priceText = productCardList.get(index).getText();
        return Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
    }
}