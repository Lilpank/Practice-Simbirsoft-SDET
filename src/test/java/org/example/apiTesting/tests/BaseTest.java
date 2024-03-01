package org.example.apiTesting.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.config.PropertyProvider;

public class BaseTest {
    /**
     * Generates a request specification for the given base path.
     *
     * @return          the request specification instance
     */
    public static RequestSpecification instanceRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(PropertyProvider.getProperty("api.url"))
                .setPort(8081)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }
}
