package org.example.api.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.api.asserts.ApiAssert;
import org.example.api.basicApi.GoodsApi;
import org.junit.jupiter.api.*;

@Epic("API-тесты")
@Feature("Товары")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("api")
public class ApiTests {

    private final GoodsApi goodsApi = new GoodsApi();

    private static int id;

    @Test
    @Order(1)
    @Tag("smoke")
    @Story("GET /goods/list")
    @DisplayName("GET /goods/list → 200")
    void shouldGetAllGoods() {
        Response response = goodsApi.getAll(0, 100);
        ApiAssert.assertStatusCode(response, 200);
    }

    @Test
    @Order(2)
    @Tag("smoke")
    @Story("POST /goods/add")
    @DisplayName("POST /goods/add → 200 (создание)")
    void shouldCreateGood() {
        Response response = goodsApi.create("Cheese", 11.50);
        ApiAssert.assertStatusCode(response, 200);
        ApiAssert.assertFieldNotNull(response, "data.id");

        id = response.jsonPath().getInt("data.id");
        System.out.println("Создан товар id = " + id);
    }

       @Test
       @Order(3)
       @Story("POST /goods/add — ошибка")
       @DisplayName("POST /goods/add дубликат → 400")
       void shouldNotCreateDuplicateGood() {
           Response response = goodsApi.create("Cheese", 11.50);
           ApiAssert.assertStatusCode(response, 400);
       }

       @Test
       @Order(4)
       @Story("PATCH /goods/{id}")
       @DisplayName("PATCH /goods/{id} → 200")
       void shouldUpdateGood() {
           Response response = goodsApi.update(id, "Cheese", 20.50);
           ApiAssert.assertStatusCode(response, 200);
       }

       @Test
       @Order(5)
       @Story("PATCH /goods/{id} — 404")
       @DisplayName("PATCH /goods/0 → 404")
       void shouldNotUpdateNotExistingGood() {
           Response response = goodsApi.update(0, "Cheese", 20.50);
           ApiAssert.assertStatusCode(response, 404);
       }

       @Test
       @Order(6)
       @Story("PATCH /goods/{id} — 400")
       @DisplayName("PATCH /goods/{id} некорректный body → 400")
       void shouldNotUpdateWithInvalidBody() {
           Response response = goodsApi.updateWithBody(0, "{ .. }");
           ApiAssert.assertStatusCode(response, 400);
       }

    /*
    @Test
    @Order(7)
    @Story("GET /goods/{id}")
    @DisplayName("GET /goods/{id} → 200")
    void shouldGetGoodById() {
        Response response = goodsApi.getById(id);
        ApiAssert.assertStatusCode(response, 200);
    }
*/
    @Test
    @Order(8)
    @Story("GET /goods/{id} — 404")
    @DisplayName("GET /goods/0 → 404")
    void shouldNotGetNotExistingGood() {
        Response response = goodsApi.getById(0);
        ApiAssert.assertStatusCode(response, 404);
    }

    @Test
    @Order(9)
    @Story("DELETE /goods/{id}")
    @DisplayName("DELETE /goods/{id} → 200")
    void shouldDeleteGood() {
        Response response = goodsApi.delete(id);
        ApiAssert.assertStatusCode(response, 200);
    }

    @Test
    @Order(10)
    @Story("DELETE /goods/{id} — 404")
    @DisplayName("DELETE /goods/0 → 404")
    void shouldNotDeleteNotExistingGood() {
        Response response = goodsApi.delete(0);
        ApiAssert.assertStatusCode(response, 404);
    }

    @AfterAll
    @Step("Очистка: удалить все товары")
    static void cleanUpAll() {
        new GoodsApi().deleteAll();
    }
}