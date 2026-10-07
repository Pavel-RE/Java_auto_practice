package org.example.api.basicApi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.rest.RestApiBuilder;

import static io.restassured.RestAssured.given;

public class GoodsApi {

    private final RequestSpecification spec;

    public GoodsApi() {
        this.spec = new RestApiBuilder().withAuth().getSpec();
    }

    @Step("GET /goods/list — получить список товаров (page={page}, size={size})")
    public Response getAll(int page, int size) {
        return given()
                .spec(spec)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/list");
    }

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

    @Step("POST /goods/add — создать товар с телом {body}")
    public Response createWithBody(String body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .post("/add");
    }

    @Step("GET /goods/{id} — получить товар по id={id}")
    public Response getById(int id) {
        return given()
                .spec(spec)
                .when()
                .get("/" + id);
    }

    @Step("PATCH /goods/{id} — обновить товар id={id}")
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

    @Step("PATCH /goods/{id} — обновить с телом {body}")
    public Response updateWithBody(int id, String body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .patch("/" + id);
    }

    @Step("DELETE /goods/{id} — удалить товар id={id}")
    public Response delete(int id) {
        return given()
                .spec(spec)
                .when()
                .delete("/" + id);
    }

    @Step("Удалить все товары")
    public void deleteAll() {
        int[] ids = getAll(0, 1000)
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