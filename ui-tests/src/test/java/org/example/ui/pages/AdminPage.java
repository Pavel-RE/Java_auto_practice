package org.example.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.common.config.Config;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AdminPage {

    public String linkToAuth = Config.INSTANCE.url() + "/login";

    public SelenideElement
            userName = $x("//input[@id='username']"),
            password = $x("//input[@id='password']"),
            signInButton = $x("//button[contains(text(), 'Sign in')]"),
            nameCardInput = $x("//input[@id='n-name']"),
            priceCardInput = $x("//input[@id='n-price']"),
            addCardButton = $x("//button[@id='add-btn']"),
            toast = $x("//div[@class='toast' and contains(text(), 'Товар успешно добавлен')]"),
            backToMainButton = $x("//a[contains(text(), 'Вернуться на сайт')]");

    public ElementsCollection
            productNameInputList = $$x("//input[starts-with(@id, 'nm-')]"),
            productPriceInputList = $$x("//input[starts-with(@id, 'pr-')]"),
            saveButtonList = $$x("//button[contains(text(), 'Сохранить')]");

    @Step("Проверить, что кнопка 'Sign in' видна")
    public AdminPage checkSignInButtonIsVisible() {
        signInButton.should(visible);
        return this;
    }

    @Step("Ввести логин: {username}")
    public AdminPage fillUsername(String username) {
        userName.clear();
        userName.sendKeys(username);
        return this;
    }

    @Step("Ввести пароль")
    public AdminPage fillPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }

    @Step("Нажать клавишу {key} на кнопке Sign in")
    public AdminPage pressKey(Keys key) {
        signInButton.sendKeys(key);
        return this;
    }

    @Step("Нажать 'Sign in'")
    public AdminPage clickSignInButton() {
        signInButton.click();
        return this;
    }

    @Step("Заполнить название товара: {name}")
    public AdminPage fillCardName(String name) {
        nameCardInput.clear();
        nameCardInput.sendKeys(name);
        return this;
    }

    @Step("Заполнить цену товара: {price}")
    public AdminPage fillCardPrice(String price) {
        priceCardInput.clear();
        priceCardInput.sendKeys(price);
        return this;
    }

    @Step("Нажать 'Создать товар'")
    public AdminPage clickAddCardButton() {
        addCardButton.click();
        return this;
    }

    @Step("Изменить название товара в строке {index}: {text}")
    public AdminPage inputProductName(int index, String text) {
        productNameInputList.get(index).clear();
        productNameInputList.get(index).sendKeys(text);
        return this;
    }

    @Step("Изменить цену товара в строке {index}: {text}")
    public AdminPage inputProductPrice(int index, String text) {
        productPriceInputList.get(index).clear();
        productPriceInputList.get(index).sendKeys(text);
        return this;
    }

    @Step("Нажать 'Сохранить' в строке {index}")
    public AdminPage clickSave(int index) {
        saveButtonList.get(index).click();
        return this;
    }

    @Step("Вернуться на главную страницу")
    public AdminPage backToMainButtonClick() {
        backToMainButton.click();
        return this;
    }

    @Step("Получить id последнего созданного товара '{name}'")
    public int getLastCreatedId(String name) {
        sleep(2000);
        String elementId = $x("//input[@value='" + name + "']").getAttribute("id");
        return Integer.parseInt(elementId.replace("nm-", ""));
    }
}