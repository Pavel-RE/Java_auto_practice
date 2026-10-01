package org.example;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import rest.RestApiBuilder;
import rest.config.ApiConfig;
import rest.config.ConfigProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class testsForProperties {
    @Test
    void propsTest() {
        Properties props = new Properties();
        InputStream propsStream = getClass().getResourceAsStream("/config.properties");

        try {
            props.load(propsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(props.getProperty("URL"));
        System.out.println(props.getProperty("MODE"));
        System.out.println(props.getProperty("GOOD_NAME"));
    }

    @Test
    void propsTest2() {
        new RestApiBuilder().getSpec().log().all().get().then().log().all();
    }


    @Test
    void propsTest3() {
        new RestApiBuilder(new ConfigProvider().getProperty("URL"))
                .getSpec()
                .log().all()
                .get()
                .then()
                .log().all();
    }

    @Test
    void propsTest4() {
        ApiConfig config = ConfigFactory.create(ApiConfig.class);
        new RestApiBuilder(config.url())
                .getSpec()
                .log().all()
                .get()
                .then()
                .log().all();
    }
}


