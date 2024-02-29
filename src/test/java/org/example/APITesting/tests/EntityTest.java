package org.example.APITesting.tests;

import io.qameta.allure.*;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.example.APITesting.helpers.ApiDataProvider;
import org.example.APITesting.helpers.Endpoints;
import org.example.APITesting.helpers.StatusCode;
import org.example.APITesting.pojo.EntityPojo;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class EntityTest extends BaseTest {
    private RequestSpecification requestSpec = null;

    private SoftAssert softAssert = null;

    private final String basePath;

    @Factory(dataProvider = "getDataApiUrl", dataProviderClass = ApiDataProvider.class)
    public EntityTest(String basePath) {
        this.basePath = basePath;
    }

    /**
     * A method that initializes the necessary resources before the test.
     */
    @BeforeTest
    public void init() {
        softAssert = new SoftAssert();
        requestSpec = instanceRequestSpec(basePath);
    }

    /**
     * Test method for cycle of create, get and delete methods of entity
     *
     * @param entityPojo is entityPojo
     */
    @Test(dataProvider = "entitiesData", dataProviderClass = ApiDataProvider.class)
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

    /**
     * Check POST method create request
     *
     * @param entityPojo Create Entity by pojo body
     * @return id of created entity
     */
    @Epic("Entities management")
    @Description("Check POST method request")
    @Step("Create Entity by pojo body")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("DanGor")
    public int testCreateEntityRequest(EntityPojo entityPojo) {
        var response = given()
                .spec(requestSpec)
                .body(entityPojo)
                .when()
                .post(Endpoints.createEntity);

        response.then()
                .statusCode(StatusCode.OK.getCode());

        return Integer.parseInt(response.asString());
    }

    /**
     * Check GET method request
     *
     * @param idEntity id of entity
     * @return response entitypojo by id
     */
    @Epic("Entities management")
    @Description("Check GET method request")
    @Step("Get Entity by ID is {idEntity}")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public EntityPojo testGetEntityRequest(int idEntity) {
        var response = given()
                .spec(requestSpec)
                .pathParam("id", idEntity)
                .get(Endpoints.getEntity)
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract()
                .as(EntityPojo.class, ObjectMapperType.GSON);

        return response;
    }

    /**
     * Compares Entities request and response
     *
     * @param entityPojo   request EntityPojo
     * @param responsePojo response EntityPojo
     */
    @Epic("Entities management")
    @Description("Test compares request and response EntityPojo")
    @Step("Compares Entities request and response")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("DanGor")
    public void testEqualsRequestAndResponseEntity(EntityPojo entityPojo, EntityPojo responsePojo) {
        softAssert.assertEquals(entityPojo.getId(), responsePojo.getId());
        softAssert.assertEquals(entityPojo.getAddition(), responsePojo.getAddition());
        softAssert.assertEquals(entityPojo.getTitle(), responsePojo.getTitle());
        softAssert.assertEquals(entityPojo.getVerified(), responsePojo.getVerified());
        softAssert.assertEquals(entityPojo.getImportantNumbers(), responsePojo.getImportantNumbers());
    }

    /**
     * Check DELETE method request
     *
     * @param idEntity id of entity
     */
    @Epic("Entities management")
    @Description("Check DELETE method request")
    @Step("Delete Entity by ID is {idEntity}")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public void testDeleteRequestEntityById(int idEntity) {
        given()
                .spec(requestSpec)
                .pathParam("id", idEntity)
                .delete(Endpoints.deleteEntity)
                .then()
                .statusCode(StatusCode.NO_CONTENT.getCode());
    }

    /**
     * Check PATCH method request
     *
     * @param idEntity id of entity
     */
    @Epic("Entities management")
    @Description("Check PATCH method request")
    @Step("Update Entity by ID is {idEntity}")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
//    @Test(dataProvider = "entityDataPatch", dataProviderClass = ApiDataProvider.class)
    public void testPatchRequestEntityById(int idEntity, EntityPojo entityPojo) {
        given()
                .spec(requestSpec)
                .body(entityPojo)
                .pathParam("id", idEntity)
                .patch(Endpoints.updateEntity)
                .then()
                .statusCode(StatusCode.NO_CONTENT.getCode());
    }
}
