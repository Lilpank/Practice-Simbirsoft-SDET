package org.example.apiTesting.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.example.apiTesting.helpers.Endpoints;
import org.example.apiTesting.helpers.StatusCode;
import org.example.apiTesting.pojo.EntityPojo;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class StepsEntity extends EntityTest {
    private static final RequestSpecification requestSpec = instanceRequestSpec();

    private static final SoftAssert softAssert = new SoftAssert();

    /**
     * Check POST method create request
     *
     * @param entityPojo Create Entity by pojo body
     * @return id of created entity
     */
    @Step("Create Entity by pojo body")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public static int testCreateEntityRequest(EntityPojo entityPojo) {
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
     * @return response entityPojo by id
     */
    @Step("Get Entity by ID is {idEntity}")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public static EntityPojo testGetEntityRequest(int idEntity) {
        return given()
                .spec(requestSpec)
                .pathParam("id", idEntity)
                .get(Endpoints.getEntity)
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract()
                .as(EntityPojo.class, ObjectMapperType.GSON);
    }

    /**
     * Compares Entities request and response
     *
     * @param entityPojo   request EntityPojo
     * @param responsePojo response EntityPojo
     */
    @Step("Compares Entities request and response")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("DanGor")
    public static void testEqualsRequestAndResponseEntity(EntityPojo entityPojo, EntityPojo responsePojo) {
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
    @Step("Delete Entity by ID is {idEntity}")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public static void testDeleteRequestEntityById(int idEntity) {
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
    @Step("Update Entity by ID is {idEntity}")
    @Severity(SeverityLevel.NORMAL)
    @Owner("DanGor")
    public static void testPatchRequestEntityById(int idEntity, EntityPojo entityPojo) {
        given()
                .spec(requestSpec)
                .body(entityPojo)
                .pathParam("id", idEntity)
                .patch(Endpoints.updateEntity)
                .then()
                .statusCode(StatusCode.NO_CONTENT.getCode());
    }
}
