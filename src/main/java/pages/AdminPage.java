package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AdminPage {

    public String linkToAuth = "http://localhost:8080/login";

    SelenideElement
            userName = $x("//input[@id='username']"),
            password = $x("//input[@id='password']"),
            signInButton = $x("//button[contains(text(), 'Sign in')]"),
            nameCardInput = $x("//input[@id='n-name']"),
            priceCardInput = $x("//input[@id='n-price']"),
            addCardButton = $x("//button[@id='add-btn']"),
            toast = $x("//div[@class='toast' and contains(text(), 'Товар успешно добавлен')]"),
            backToMainButton = $x("//a[contains(text(), 'Вернуться на сайт')]");

    ElementsCollection
            productNameInputList = $$x("//input[starts-with(@id, 'nm-')]"),
            productPriceInputList = $$x("//input[starts-with(@id, 'pr-')]"),
            saveButtonList = $$x("//button[contains(text(), 'Сохранить')]");


    public void checkSignInButtonIsVisible(){
        signInButton.should(visible);
    }

    public AdminPage fillUsername(String username) {
        userName.clear();
        userName.sendKeys(username);
        return this;
    }

    public AdminPage fillPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }

    public AdminPage pressKey(Keys key) {
        signInButton.sendKeys(key);
        return this;
    }

    public void clickSignInButton(){
        signInButton.click();
    }

    public AdminPage fillCardName(String name) {
        nameCardInput.clear();
        nameCardInput.sendKeys(name);
        return this;
    }

    public AdminPage fillCardPrice(String price) {
        priceCardInput.clear();
        priceCardInput.sendKeys(price);
        return this;
    }

    public void clickAddCardButton(){
        addCardButton.click();
    }

    public void inputProductName(int index, String text) {
        productNameInputList.get(index).clear();
        productNameInputList.get(index).sendKeys(text);
    }

    public void inputProductPrice(int index, String text) {
        productPriceInputList.get(index).clear();
        productPriceInputList.get(index).sendKeys(text);
    }

    public void clickSave(int index) {
        saveButtonList.get(index).click();
    }

    public void backToMainButtonClick() {
        backToMainButton.click();
    }

}
