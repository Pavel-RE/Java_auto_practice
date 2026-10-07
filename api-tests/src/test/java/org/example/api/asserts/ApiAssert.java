package org.example.api.asserts;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class ApiAssert {

    @Step("Проверить, что статус-код = {expected}")
    public static void assertStatusCode(Response response, int expected) {
        Assertions.assertThat(response.getStatusCode())
                .as("Статус-код должен быть " + expected)
                .isEqualTo(expected);
    }

    @Step("Проверить, что поле '{path}' = '{expected}'")
    public static void assertFieldEquals(Response response, String path, Object expected) {
        Object actual = response.jsonPath().get(path);
        Assertions.assertThat(actual)
                .as("Поле '" + path + "' должно быть " + expected)
                .isEqualTo(expected);
    }

    @Step("Проверить, что поле '{path}' не пустое")
    public static void assertFieldNotNull(Response response, String path) {
        Object actual = response.jsonPath().get(path);
        Assertions.assertThat(actual)
                .as("Поле '" + path + "' не должно быть null")
                .isNotNull();
    }
}