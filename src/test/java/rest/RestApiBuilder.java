package rest;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {
    RequestSpecification spec;
    Properties props = new Properties();

    private static final String
            BASIC_URL = "http://localhost:8080",
            LOGIN = "admin",
            PASS = "secret123";

    public RestApiBuilder() {
        this(BASIC_URL);
    }

    public RestApiBuilder(String url) {
        try {
            props.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        spec = given()
                .filter(new AllureRestAssured())
                .baseUri(url)
                .basePath("/goods")
                .contentType(ContentType.JSON)
                .log().all()
                .relaxedHTTPSValidation();
    }

    public RestApiBuilder addAuth(String login, String password) {
        spec = spec.auth().basic(login, password);
        return this;
    }

    public RestApiBuilder setContentJSON() {
        spec = spec.contentType(ContentType.JSON);
        return this;
    }

    public RequestSpecification getSpec() {
        return spec;
    }

    public RestApiBuilder getBuilder() {
        return new RestApiBuilder().addAuth(LOGIN, PASS);
    }

    private String getResource(String key) {
        return props.getProperty(key);
    }

    @Step("Проверить, что список товаров пуст")
    public void checkGoodsListIsEmpty() {
        given()
                .spec(spec)
                .filter(new AllureRestAssured())
                .auth().basic(LOGIN, PASS)
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.isEmpty()", org.hamcrest.Matchers.is(true));
    }

    @Step("Создать товар: name={name}, price={price}")
    public int createGood(String name, int price) {
        Response response = given()
                .spec(spec)
                .filter(new AllureRestAssured())
                .auth().basic(LOGIN, PASS)
                .body("{\"name\": \"" + name + "\", \"price\": " + price + "}")
                .when()
                .post("/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        return response.jsonPath().getInt("data.id");
    }

    @Step("Удалить товар с id={id}")
    public void deleteGood(int id) {
        given()
                .spec(spec)
                .filter(new AllureRestAssured())
                .auth().basic(LOGIN, PASS)
                .when()
                .delete("/" + id)
                .then()
                .log().all();
    }

    @Step("Проверить, что список товаров НЕ пуст")
    public void checkGoodsListIsNotEmpty() {
        given()
                .spec(spec)
                .filter(new AllureRestAssured())
                .auth().basic(LOGIN, PASS)
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .body("goods.isEmpty()", org.hamcrest.Matchers.is(false));
    }

    @Step("Удалить ВСЕ товары из админки")
    public void deleteAllGoods() {
        List<Integer> ids = given()
                .spec(spec)
                .filter(new AllureRestAssured())
                .auth().basic(LOGIN, PASS)
                .queryParam("page", 0)
                .queryParam("size", 1000)
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("goods.id", Integer.class);

        for (int id : ids) {
            deleteGood(id);
        }
    }
}