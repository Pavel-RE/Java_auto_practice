package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("selenideTests")

public class SelenideTests1 {
    @BeforeEach
    void setup(){
        Selenide.open("http://localhost:8080/");
    }
    @Test
    @Order(1)
    void selenideTest1() {
        $x("//a[contains(text(), 'Администрирование')]").click();
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $x("//button[contains(text(), 'Sign in')]").click();
        $("input[placeholder='Название']").setValue("Чайник");
        $("input[placeholder='Цена']").setValue("25");
        $("#add-btn").click();
        $x("//a[text()='Вернуться на сайт']").click();
        $x("//div[@data-name='Чайник']").shouldHave(text("Чайник"));
    }

    @Test
    @Order(2)
    void selenideTest2() {
        $x("//div[@data-name='Чайник']//button[@data-action='add-to-cart']").click();
        $("#open-cart-btn").click();
        $x("//div[@class='cart-item' and .//b[text()='Чайник']]").should(exist);
    }

    @Test
    @Order(3)
    void selenideTest3() {
        Selenide.open("http://localhost:8080/login");
        sleep(1000);
        $("#username").setValue("admin4");
        $("#password").setValue("secret1234");
        $x("//button[contains(text(), 'Sign in')]").click();
        sleep(1000);
        $x("//div[@role='alert']").shouldHave(text("Неверные учетные данные пользователя"));
    }

    @Test
    @Order(4)
    void selenideTest4() {
        SelenideElement addToCartButton = $x("//div[@data-name='Чайник']//button[@data-action='add-to-cart']");
        SelenideElement openCartButton = $("#open-cart-btn");
        SelenideElement cartItem = $x("//div[@class='cart-item' and .//b[text()='Чайник']]");

        addToCartButton.click();
        sleep(2000);
        refresh();
        openCartButton.click();
        cartItem
                .should(exist)
                .shouldHave(text("Чайник"));
    }

    @Test
    @Order(5)
    void selenideTest5() {
        SelenideElement addToCartButton = $x("//*[@data-action='add-to-cart']");
        for (int i = 0; i < 11; i++) {
            addToCartButton.click();
        }
        $("#open-cart-btn").click();
        $("#makeOrder").click();
        Alert activeAlert = Selenide.switchTo().alert();
        System.out.println(activeAlert.getText());
        activeAlert.accept();
    }
}
