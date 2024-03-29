package org.example.apiTesting.tests;

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
    public static int testCreateEntityRequestStep(EntityPojo entityPojo) {
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
    public static EntityPojo testGetEntityRequestStep(int idEntity) {
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
    public static void testEqualsRequestAndResponseEntityStep(EntityPojo entityPojo, EntityPojo responsePojo) {
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
    public static void testDeleteRequestEntityByIdStep(int idEntity) {
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
    public static void testPatchRequestEntityByIdStep(int idEntity, EntityPojo entityPojo) {
        given()
                .spec(requestSpec)
                .body(entityPojo)
                .pathParam("id", idEntity)
                .patch(Endpoints.updateEntity)
                .then()
                .statusCode(StatusCode.NO_CONTENT.getCode());
    }
}
