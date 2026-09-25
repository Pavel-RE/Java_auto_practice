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



//rest assured tests 2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("lesson4Task2")
public class Lesson4Tests2 {

    private RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .log(LogDetail.ALL)
            .addQueryParam("page", 0)
            .build();

    private static int id;

    //1. Метод get("/goods/list"). Проверяем код 200.
    @Test
    @Order(1)
    @Tag("API")
    void lesson4Test5(){
        Response response = given()
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
                .extract()
                .response();
        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 200 OK")
                .isEqualTo(200);
    }

    //2. Метод post("/goods/add"). Проверяем код 200.
    @Test
    @Order(2)
    @Tag("API")
    void lesson4Test6(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .body("""
                    {
                        "name": "Cheese",
                        "price": 11.50
                        }
                """)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .extract()
                .response();
        id = response.jsonPath().getInt("data.id"); //понадобится для манипуляций с товаром в тестах ниже

        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 200 OK")
                .isEqualTo(200);
    }

    //3. Метод post("/goods/add"). Проверяем код 400. ЗАПУСКАТЬ ПОСЛЕ lesson4Test6!
    @Test
    @Order(3)
    @Tag("API")
    void lesson4Test7(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .body("""
                    {
                        "name": "Cheese",
                        "price": 11.50
                        }
                """)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 400 Bad request")
                .isEqualTo(400);
    }

    //4. Метод patch("/goods/{id}"). Проверяем код 200.
    @Test
    @Order(4)
    @Tag("API")
    void lesson4Test8(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .body("""
                    {
                        "name": "Cheese",
                        "price": 20.50
                        }
                """)
                .when()
                .patch("/goods/" + id)
                .then()
                .log().all()
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 200 OK")
                .isEqualTo(200);
    }
    //5. Метод patch("/goods/{id}"). Проверяем код 404.
    @Test
    @Order(5)
    @Tag("API")
    void lesson4Test9(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .body("""
                    {
                        "name": "Cheese",
                        "price": 20.50
                        }
                """)
                .when()
                .patch("/goods/" + 0)
                .then()
                .log().all()
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 404 Not found")
                .isEqualTo(404);
    }

    //6. Метод patch("/goods/{id}"). Проверяем код 400.
    @Test
    @Order(6)
    @Tag("API")
    void lesson4Test10(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .body("""
                    {
                        ..
                        }
                """)
                .when()
                .patch("/goods/" + 0)
                .then()
                .log().all()
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 400 Bad request")
                .isEqualTo(400);
    }

    //7. Метод get("/goods/{id}"). Проверяем код 200.
    @Test
    @Order(7)
    @Tag("API")
    void lesson4Test11(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/" + id)
                .then()
                .log().all()
                .extract()
                .response();
        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 200 OK")
                .isEqualTo(200);
    }

    //8. Метод get("/goods/{id}"). Проверяем код 404.
    @Test
    @Order(8)
    @Tag("API")
    void lesson4Test12(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/0")
                .then()
                .log().all()
                .extract()
                .response();
        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 404 Not found")
                .isEqualTo(404);
    }

    //9. Метод delete("/goods/{id}"). Проверяем код 200.
    @Test
    @Order(9)
    @Tag("API")
    void lesson4Test13(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/" + id)
                .then()
                .log().all()
                .extract()
                .response();
        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 200 OK")
                .isEqualTo(200);
    }

    //10. Метод delete("/goods/{id}"). Проверяем код 404.
    @Test
    @Order(10)
    @Tag("API")
    void lesson4Test14(){
        Response response = given()
                .spec(basicRQ)
                .queryParam("size", 1)
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/0")
                .then()
                .log().all()
                .extract()
                .response();
        Assertions.assertThat(response.getStatusCode())
                .as("Статус должен быть 404 Not found")
                .isEqualTo(404);
    }



}
