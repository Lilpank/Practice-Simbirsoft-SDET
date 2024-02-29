package org.example.apiTesting.tests;

import io.qameta.allure.*;
import io.restassured.specification.RequestSpecification;
import org.example.apiTesting.helpers.Endpoints;
import org.example.apiTesting.helpers.StatusCode;
import org.example.apiTesting.pojo.EntityPojo;
import org.example.config.SuiteDataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.apiTesting.tests.StepsEntity.*;

public class EntityTest extends BaseTest {
    private final RequestSpecification requestSpec = instanceRequestSpec();

    /**
     * Test method for cycle of create, get and delete methods of entity
     *
     * @param entityPojo is entityPojo
     */
    @Test(dataProvider = "entitiesData", dataProviderClass = SuiteDataProvider.class)
    @Epic("Entities management")
    @Feature("POST, DELETE, GET request of entity")
    @Description("Test check create, get and delete methods of entity")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("DanGor")
    public void testCycleOfCreateGetDeleteEntityRequest(EntityPojo entityPojo) {
        var entity_id = testCreateEntityRequest(entityPojo);
        var responsePojo = testGetEntityRequest(entity_id);
        testEqualsRequestAndResponseEntity(entityPojo, responsePojo);
        testPatchRequestEntityById(entity_id, responsePojo);
        testDeleteRequestEntityById(entity_id);
    }

    /**
     * Test method for getting entities.
     */
    @Test
    @Epic("Entities management")
    @Description("Check GET method request")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public void testGetEntities() {
        given()
                .spec(requestSpec)
                .get(Endpoints.getEntities)
                .then()
                .statusCode(StatusCode.OK.getCode());
    }
}
