package org.example.APITesting.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
    public RequestSpecification requestSpec = null;
    
    /**
     * Generates a request specification for the given base path.
     *
     * @param  basePath   the base path for the request specification
     * @return          the request specification instance
     */
    public RequestSpecification instanceRequestSpec(String basePath) {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(basePath)
                    .setPort(8081)
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }
}
