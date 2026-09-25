package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("selenideTests2")

public class SelenideTests2 {
    private final List<Integer> createdGoodIds = new ArrayList<>();

    @BeforeEach
    void setup(){
        Selenide.open("http://localhost:8080/");
    }

    @AfterEach
    void cleanUp() {
        if (createdGoodIds.isEmpty()) {
            System.out.println("Нечего удалять");
            return;
        }

        System.out.println("=== Удаляем " + createdGoodIds.size() + " товаров");

        for (int id : createdGoodIds) {
            given()
                    .spec(basicRQ)
                    .contentType(ContentType.JSON)
                    .auth().basic("admin", "secret123")
                    .when()
                    .delete("/goods/" + id)
                    .then()
                    .log().all();
        }

        createdGoodIds.clear();
        System.out.println("Очистка завершена");
    }

    private RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .log(LogDetail.ALL)
            .addQueryParam("page", 0)
            .build();



    @Test
    @Order(1)
    void selenideTest1() {
        SelenideElement addToCartButton = $x("//*[@data-action='add-to-cart']");
        for (int i = 0; i < 3; i++) {
            addToCartButton.click();
        }
        $x("//*[@id='open-cart-btn']").click();
        sleep(1000);
        $x("//button[@id='makeOrder']").click();
        $x("//div[@id='toast-container']//div[contains(text(),'Заказ принят в обработку')]")
                .shouldBe(visible);
    }


    @Test
    @Order(2)
    void selenideTest2() {



        //создаем товары в переменные
        String name1 = "Бутылка";
        int price1 = 25;
        String name2 = "Кувшин";
        int price2 = 50;
        String name3 = "Ваза";
        int price3 = 100;


        //создаем товары в админке
        Response response1 = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .body("{" +
                        "\"name\": \"" + name1 + "\"," +
                        "\"price\": " + price1 +
                        "}")
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        int id1 = response1.jsonPath().getInt("data.id");
        createdGoodIds.add(id1);

        Response response2 = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .body("{" +
                        "\"name\": \"" + name2 + "\"," +
                        "\"price\": " + price2 +
                        "}")
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        int id2 = response2.jsonPath().getInt("data.id");
        createdGoodIds.add(id2);



        Response response3 = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .body("{" +
                        "\"name\": \"" + name3 + "\"," +
                        "\"price\": " + price3 +
                        "}")
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        int id3 = response3.jsonPath().getInt("data.id");
        createdGoodIds.add(id3);

        System.out.println("Создан товар с id1 = " + id1);
        System.out.println("Создан товар с id2 = " + id2);
        System.out.println("Создан товар с id3 = " + id3);

        // Считаем ожидаемую сумму
        int expectedTotal = price1 + price2 + price3;

        // Добавляем в корзину через UI

        refresh();

        $x("//button[@data-id='" + id1 + "' and @data-action='add-to-cart']").click();
        $x("//button[@data-id='" + id2 + "' and @data-action='add-to-cart']").click();
        sleep(4500);
        $x("//button[@data-id='" + id3 + "' and @data-action='add-to-cart']").click();

        $x("//*[@id='open-cart-btn']").click();
        sleep(1000);

        // Проверяем, что все товара в корзине
        $x("//*[contains(text(),'" + name1 + "')]").shouldBe(visible);
        $x("//*[contains(text(),'" + name2 + "')]").shouldBe(visible);
        $x("//*[contains(text(),'" + name3 + "')]").shouldBe(visible);

        // Проверяем сумму
        int actualTotal = Integer.parseInt($x("//*[@id='total-price']").getText());

        assertEquals(expectedTotal, actualTotal, "Сумма в корзине не совпадает");
    }

    @Test
    @Order(3)
    void selenideTest3() {
        $x("//a[contains(text(), 'Администрирование')]").click();
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $x("//button[contains(text(), 'Sign in')]").click();
        $("input[placeholder='Название']").setValue("Кружка");
        $("input[placeholder='Цена']").setValue("20");
        $("#add-btn").click();
        $x("//div[@id='toast-container']//div[@class='toast']").shouldBe(visible).shouldHave(text("Товар успешно добавлен!"));
        sleep(2000);
        String elementId = $x("//input[@value='Кружка']").getAttribute("id");  // "nm-107"
        int id = Integer.parseInt(elementId.replace("nm-", ""));  // 107
        createdGoodIds.add(id);
    }

    @Test
    @Order(4)
    void selenideTest4() {
        $x("//a[contains(text(), 'Администрирование')]").click();
        if ($("#username").exists()) {
            $("#username").setValue("admin");
            $("#password").setValue("secret123");
            $x("//button[contains(text(), 'Sign in')]").click();
        }
        $("input[placeholder='Название']").setValue("Кружка");
        $("input[placeholder='Цена']").setValue("20");
        $("#add-btn").click();
        $x("//div[@id='toast-container']//div[@class='toast']").shouldBe(visible).shouldHave(text("Товар успешно добавлен!"));
        sleep(2000);
        String elementId = $x("//input[@value='Кружка']").getAttribute("id");  // "nm-107"
        int id = Integer.parseInt(elementId.replace("nm-", ""));  // 107
        createdGoodIds.add(id);
        $x("//input[@id='nm-" + id + "']").setValue("Чашка");
        $x("//input[@id='pr-" + id + "']").setValue("15");
        $x("//button[@data-id='" + id + "' and @data-action='update']").click();
        sleep(2000);
        refresh();
        $x("//a[contains(text(), 'Вернуться на сайт')]").click();
        refresh();
        //$x("//div[@class='product-card' and @data-id='" + id + "' and @data-name='Чашка' and @data-price='15']").shouldBe(visible);
        $x("//div[@class='product-card' and @data-id='" + id + "']")
                .shouldHave(attribute("data-name", "Чашка"));
    }

}
