package org.example.ui.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.common.config.Config;
import org.example.ui.asserts.AdminPageAssert;
import org.example.ui.asserts.CartModalPageAssert;
import org.example.ui.asserts.MainPageAssert;
import org.example.ui.pages.AdminPage;
import org.example.ui.pages.CartModalPage;
import org.example.ui.pages.MainPage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("AllureTests")
@Epic("UI-тесты")
@Feature("Сквозной UI-сценарий")
public class UiTests {

    MainPage mainPage = new MainPage();
    MainPageAssert mainPageAssert = new MainPageAssert(mainPage);
    CartModalPage cartModalPage = new CartModalPage();
    CartModalPageAssert cartModalPageAssert = new CartModalPageAssert(cartModalPage);
    AdminPage adminPage = new AdminPage();
    AdminPageAssert adminPageAssert = new AdminPageAssert(adminPage);

    @BeforeAll
    void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true));
    }

    @BeforeEach
    @Step("Открыть главную страницу")
    void setup() {
        open(mainPage.mainPageURL);
    }

    @AfterAll
    @Step("Очистка: удалить все товары через админку")
    void cleanUpAll() {
        open(adminPage.linkToAuth);
        adminPage.fillUsername(Config.INSTANCE.adminLogin())
                .fillPassword(Config.INSTANCE.adminPassword())
                .clickSignInButton();

        sleep(2000);

        int deleted = 0;
        int maxAttempts = 50;

        while (deleted < maxAttempts) {
            ElementsCollection deleteButtons = $$x("//button[contains(text(), 'Удалить')]");

            if (deleteButtons.isEmpty()) {
                System.out.println("✅ Все товары удалены. Итого: " + deleted);
                break;
            }

            deleteButtons.first().click();
            sleep(1000);

            WebDriverRunner.getWebDriver().switchTo().alert().accept();
            sleep(1000);

            deleted++;
            System.out.println("Удалено товаров: " + deleted);
        }
    }

    @Test
    @Order(1)
    @Step("UI-1: создать 4 товара через админку")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask3() {
        open(adminPage.linkToAuth);

        adminPage.fillUsername("admin")
                .fillPassword("secret123")
                .clickSignInButton();

        adminPage.fillCardName("Тарелка")
                .fillCardPrice("30")
                .clickAddCardButton();
        adminPageAssert.toastIsVisible();

        adminPage.fillCardName("Стакан")
                .fillCardPrice("25")
                .clickAddCardButton();
        adminPageAssert.toastIsVisible();

        adminPage.fillCardName("Ложка")
                .fillCardPrice("5")
                .clickAddCardButton();
        adminPageAssert.toastIsVisible();

        adminPage.fillCardName("Вилка")
                .fillCardPrice("5")
                .clickAddCardButton();
        adminPageAssert.toastIsVisible();
    }

    @Test
    @Order(2)
    @Step("UI-2: отредактировать товар через админку")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask4() {
        open(adminPage.linkToAuth);

        adminPage.fillUsername("admin")
                .fillPassword("secret123")
                .clickSignInButton();

        adminPage.inputProductName(0, "Черпак")
                .inputProductPrice(0, "23")
                .clickSave(0)
                .backToMainButtonClick();

        mainPageAssert.productNameListContainsProduct("Черпак");
    }

    @Test
    @Order(3)
    @Step("UI-3: добавить 3 товара и оформить заказ")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask1() {
        mainPage.addToCart(0)
                .addToCart(0)
                .addToCart(0);

        mainPage.clickCartButton();

        cartModalPageAssert.totalPriceLessOrEqual(1000);
        cartModalPage.makeOrderClick();

        sleep(1000);
        cartModalPageAssert.toastIsVisible();
    }

    @Test
    @Order(4)
    @Step("UI-4: проверить корректность суммы в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("MMM-33322")
    void testPOTask2() {
        int expectedTotal = 0;

        expectedTotal += mainPage.getProductPrice(0);
        mainPage.addToCart(0);

        expectedTotal += mainPage.getProductPrice(0);
        mainPage.addToCart(0);

        expectedTotal += mainPage.getProductPrice(1);
        mainPage.addToCart(1);

        expectedTotal += mainPage.getProductPrice(2);
        mainPage.addToCart(2);

        mainPage.clickCartButton();
        cartModalPageAssert.totalPriceEquals(expectedTotal);
    }
}