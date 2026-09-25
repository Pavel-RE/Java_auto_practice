package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;



//rest assured tests 1
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("lesson4Task1")
public class Lesson4Tests1 {

    private RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .log(LogDetail.ALL)
            .addQueryParam("page", 0)
            .build();


    //1. Проверка кода ответа и пустое тело
    @Test
    @Order(1)
    @Tag("API")
    void lesson4Test1(){
        given()
                .baseUri("http://localhost:8080")
                .log().all()
                .queryParam("page", 0)
                .queryParam("size", 100)
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.isEmpty()", is(true));

    }



    //2. Проверка кода ответа и пустого тела с RequestSpecification
    @Test
    @Order(2)
    @Tag("API")
    void lesson4Test2(){
        given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .auth()
                .basic("admin", "secret123")
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.isEmpty()", is(true));
    }

    //3. Проверка, что метод GET возращает добавленный через POST товар (встроенные проверки)
    @Test
    @Order(3)
    @Tag("API")
    void lesson4Test3(){

        given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .body("""
                    {
                        "name": "Bread",
                        "price": 1.50
                        }
                """)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);
        given()
                .spec(basicRQ)
                .queryParam("size", 10)
                .auth()
                .basic("admin", "secret123")
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.name", hasItem("Bread"));
    }

    //4. Проверка, что метод GET возращает добавленный через POST товар (Проверка AssertJ)
    @Test
    @Order(4)
    @Tag("API")
    void lesson4Test4(){

        given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .body("""
                    {
                        "name": "Cream",
                        "price": 5.75
                        }
                """)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 10)
                .auth()
                .basic("admin", "secret123")
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        Assertions.assertThat(response.jsonPath().getString("goods.name"))
                .as ("Поле должно быть Cream")
                .contains ("Cream");

    }

}
