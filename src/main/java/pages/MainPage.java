package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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

    public void clickCartButton(){
        cartButton.click();
    }


    public void checkCartButtonIsVisible(){
        cartButton.should(visible);
    }

    public void inputProductCount(int index, String text){
        productCountInputList.get(index).clear();
        productCountInputList.get(index)
                .sendKeys(text);
    }

    public void inputProductCount(int index, Keys keys){
        productCountInputList.get(index)
                .sendKeys(keys);
    }

    public void clickCloseCartButton(){
        closeCart.click();
    }

    public void addToCart(int index) {
        addToCardButtonList.get(index).click();
    }

    public int getProductPrice(int index) {
        String priceText = productCardList.get(index).getText();  // "30 ₽"
        return Integer.parseInt(priceText.replaceAll("[^0-9]", "")); // 30
    }


}
