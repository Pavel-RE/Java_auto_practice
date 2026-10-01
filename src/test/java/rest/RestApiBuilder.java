package rest;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
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
}