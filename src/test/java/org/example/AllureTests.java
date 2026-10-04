package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.*;
import rest.RestApiBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("AllureTests")

public class AllureTests {

    MainPage mainPage = new MainPage();
    MainPageAssert mainPageAssert = new MainPageAssert(mainPage);
    CartModalPage cartModalPage = new CartModalPage();
    CartModalPageAssert cartModalPageAssert = new CartModalPageAssert(cartModalPage);
    AdminPage adminPage = new AdminPage();
    AdminPageAssert adminPageAssert = new AdminPageAssert(adminPage);

    RestApiBuilder api = new RestApiBuilder().addAuth("admin", "secret123");
    List<Integer> createdIds = new ArrayList<>();

    @BeforeAll
    void setUpAllure() {   // ← НЕ static
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true));
    }

    @BeforeEach
    @Step("Открыть главную страницу")
    void setup(){
        open(mainPage.mainPageURL);
    }

    @AfterAll
    @Step("Очистка: удалить все товары из админки")
    void cleanUpAll() {
        api.deleteAllGoods();
    }

    @Test
    @Order(1)
    @Step("API-1: проверить, что список товаров пуст")
    @Epic("Проект-главный")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void apiTest1() {
        api.checkGoodsListIsEmpty();
    }

    @Test
    @Order(2)
    @Step("API-2: создать базовые товары")
    @Epic("Проект-главный")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void apiCreateBaseGoods() {
        createdIds.add(api.createGood("Тарелка", 30));
        createdIds.add(api.createGood("Стакан", 25));
        createdIds.add(api.createGood("Ложка", 5));
        createdIds.add(api.createGood("Вилка", 5));
    }

    @Test
    @Order(3)
    @Step("UI-1: добавить 3 товара и оформить заказ")
    @Epic("Проект-главный")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask1(){
        mainPage.addToCart(0);
        mainPage.addToCart(0);
        mainPage.addToCart(0);
        mainPage.clickCartButton();
        cartModalPageAssert.totalPriceLessOrEqual(300);
        cartModalPage.makeOrderClick();
        sleep(1000);
        cartModalPageAssert.toastIsVisible();
    }

    @Test
    @Order(4)
    @Step("UI-2: проверить корректность суммы в корзине")
    @Epic("Проект-главный")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask2() {
        int expectedTotal = 0;
        expectedTotal = mainPage.getProductPrice(0) + expectedTotal;
        mainPage.addToCart(0);
        expectedTotal = mainPage.getProductPrice(0)+ expectedTotal;
        mainPage.addToCart(0);
        expectedTotal = mainPage.getProductPrice(1) + expectedTotal;
        mainPage.addToCart(1);
        expectedTotal = mainPage.getProductPrice(2) + expectedTotal;
        mainPage.addToCart(2);
        mainPage.clickCartButton();
        cartModalPageAssert.totalPriceEquals(expectedTotal);
        sleep(1000);
    }

    @Test
    @Order(5)
    @Step("UI-3: создать товар через админку")
    @Epic("Проект-главный")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask3() {
        Selenide.open(adminPage.linkToAuth);
        adminPage.fillUsername("admin");
        adminPage.fillPassword("secret123");
        adminPage.clickSignInButton();
        sleep(1000);
        adminPage.fillCardName("Термос");
        adminPage.fillCardPrice("125");
        adminPage.clickAddCardButton();
        adminPageAssert.toastIsVisible();
    }

    @Test
    @Order(6)
    @Step("UI-4: отредактировать товар через админку")
    @Epic("Проект-главный")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask4() {
        Selenide.open(adminPage.linkToAuth);
        adminPage.fillUsername("admin");
        adminPage.fillPassword("secret123");
        adminPage.clickSignInButton();
        sleep(1000);
        adminPage.inputProductName(0, "Ваза");
        adminPage.inputProductPrice(0, "200");
        adminPage.clickSave(0);
        adminPage.backToMainButtonClick();
        sleep(3000);
        mainPageAssert.productNameListContainsProduct("Ваза");

    }


}
