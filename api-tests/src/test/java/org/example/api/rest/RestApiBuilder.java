package org.example.api.rest;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.common.config.Config;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {

    private final RequestSpecification spec;

    // ============================================
    // КОНСТРУКТОРЫ
    // ============================================

    public RestApiBuilder() {
        this(Config.INSTANCE.url());
    }

    public RestApiBuilder(String url) {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath("/goods")
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    // ============================================
    // НАСТРОЙКИ
    // ============================================

    public RestApiBuilder withAuth() {
        spec.auth().basic(
                Config.INSTANCE.adminLogin(),
                Config.INSTANCE.adminPassword()
        );
        return this;
    }

    public RestApiBuilder withAuth(String login, String password) {
        spec.auth().basic(login, password);
        return this;
    }

    public RestApiBuilder withPage(int page, int size) {
        spec.queryParam("page", page);
        spec.queryParam("size", size);
        return this;
    }

    public RequestSpecification getSpec() {
        return spec;
    }

    // ============================================
    // МЕТОДЫ ЗАПРОСОВ (все из Lesson4Tests2)
    // ============================================

    /**
     * GET /goods/list — получить список товаров
     */
    @Step("GET /goods/list — получить список товаров (page={page}, size={size})")
    public Response getAll(int page, int size) {
        return given()
                .spec(spec)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/list");
    }

    /**
     * POST /goods/add — создать товар
     */
    @Step("POST /goods/add — создать товар: name={name}, price={price}")
    public Response create(String name, double price) {
        return given()
                .spec(spec)
                .body("""
                {
                    "name": "%s",
                    "price": %s
                }
                """.formatted(name, price))
                .when()
                .post("/add");
    }

    /**
     * POST /goods/add — создать товар (с готовым телом)
     */
    @Step("POST /goods/add — создать товар с телом {body}")
    public Response createWithBody(String body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .post("/add");
    }

    /**
     * GET /goods/{id} — получить товар по ID
     */
    @Step("GET /goods/{id} — получить товар по id={id}")
    public Response getById(int id) {
        return given()
                .spec(spec)
                .when()
                .get("/" + id);
    }

    /**
     * PATCH /goods/{id} — обновить товар
     */
    @Step("PATCH /goods/{id} — обновить товар id={id}: name={name}, price={price}")
    public Response update(int id, String name, double price) {
        return given()
                .spec(spec)
                .body("""
                {
                    "name": "%s",
                    "price": %s
                }
                """.formatted(name, price))
                .when()
                .patch("/" + id);
    }

    /**
     * PATCH /goods/{id} — обновить с произвольным телом (для 400)
     */
    @Step("PATCH /goods/{id} — обновить товар id={id} с телом {body}")
    public Response updateWithBody(int id, String body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .patch("/" + id);
    }

    /**
     * DELETE /goods/{id} — удалить товар
     */
    @Step("DELETE /goods/{id} — удалить товар id={id}")
    public Response delete(int id) {
        return given()
                .spec(spec)
                .when()
                .delete("/" + id);
    }

    /**
     * DELETE /goods — удалить ВСЕ товары (утилита для очистки)
     */
    @Step("DELETE /goods — удалить все товары")
    public void deleteAll() {
        int[] ids = given()
                .spec(spec)
                .queryParam("page", 0)
                .queryParam("size", 1000)
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("goods.id", Integer.class)
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();

        for (int id : ids) {
            delete(id);
        }
    }
}