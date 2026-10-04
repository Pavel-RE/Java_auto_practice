package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pages.MainPage;
import pages.MainPageAssert;

import static com.codeborne.selenide.Selenide.open;

public class P0Test1 {

    MainPage mainPage = new MainPage();
    MainPageAssert mainPageAssert = new MainPageAssert(mainPage);

    @BeforeEach
    void setup(){
        open("http://localhost:8080");
    }

    @Test
    void test(){
        mainPageAssert.productCardSizeIsEquals(5);
        mainPageAssert.productNameListContainsProduct("Стакан");
        mainPageAssert.cartButtonIsVisible();
        mainPage.clickCartButton();
        mainPage.clickCloseCartButton();

        mainPage.inputProductCount(1, "210");
        mainPageAssert.countInputContainsValue(1, "210");



    }
}
