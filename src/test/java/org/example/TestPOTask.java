package org.example;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import pages.*;

import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("POTests")

public class TestPOTask {

    MainPage mainPage = new MainPage();
    MainPageAssert mainPageAssert = new MainPageAssert(mainPage);
    CartModalPage cartModalPage = new CartModalPage();
    CartModalPageAssert cartModalPageAssert = new CartModalPageAssert(cartModalPage);
    AdminPage adminPage = new AdminPage();
    AdminPageAssert adminPageAssert = new AdminPageAssert(adminPage);

    @BeforeEach
    void setup(){
        open(mainPage.mainPageURL);
    }

    @Test
    @Order(1)
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
    @Order(2)
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
    @Order(3)
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
    @Order(4)
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
