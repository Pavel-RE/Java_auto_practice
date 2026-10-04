package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

public class StudentTest {
    @Test
    void studentTest(){
        given()
                .body(Student.builder()
                        .setName("John")
                        .setSecondName("Jackson")
                        .build())
                .post();
    }
}
