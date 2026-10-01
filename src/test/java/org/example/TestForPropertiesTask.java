package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rest.config.TaskConfig;

import static com.codeborne.selenide.Selenide.*;

public class TestForPropertiesTask {
    @BeforeAll
    static void printConfig() {
        System.out.println("URL: " + TaskConfig.getUrl());
        System.out.println("TIMEOUT: " + TaskConfig.getTimeout());
        System.out.println("LOG_MODE: " + TaskConfig.getLogMode());
        System.out.println("BASE_GOOD_NAME: " + TaskConfig.getBaseGoodName());
        System.out.println("BASE_GOOD_PRICE: " + TaskConfig.getBaseGoodPrice());
        Configuration.baseUrl = TaskConfig.getUrl();
        Configuration.timeout = TaskConfig.getTimeout() * 1000L;
    }

    @BeforeEach
    void openPage() {
        open("/");
    }

    @Test
    void TestForPropertiesTaskTest() {
        $x("//a[contains(text(), 'Администрирование')]").click();
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $x("//button[contains(text(), 'Sign in')]").click();
    }
}
