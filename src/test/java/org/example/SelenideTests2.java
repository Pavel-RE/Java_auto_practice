package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("selenideTests")

public class SelenideTests2 {
    String goodName = "Test good";
    @BeforeEach
    void setup(){
        Selenide.open("http://localhost:8080/");
    }

    @Test
    @Order(1)
    void selenideTest1() {
        given().auth().basic("admin", "secret123")
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "name": "%s",
                        "price": 1.0
                        """.formatted(goodName))
                .post("/goods/add");
    }


}
