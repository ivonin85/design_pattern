package rf.ivonin.rest_assured;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Request {

    public Response get(String basePath, int statusCode) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(statusCode)).extract().response();
    }

    public <T> T get(String basePath, Class<T> dtoClass) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(200))
                .extract().body().as(dtoClass);
    }

    public <T> T get(String basePath, String JSONSchema, Class<T> dtoClass) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSONSchema))
                .spec(Specification.responseSpecification(200))
                .extract().body().as(dtoClass);
    }

    public <T> T get(String basePath, Map<String, ?> params, Class<T> dtoClass) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .queryParams(params)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(200))
                .extract().body().as(dtoClass);
    }

    public Response post(String basePath, Object bodyPayload, int statusCode) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .body(bodyPayload)
                .when()
                .post()
                .then()
                .spec(Specification.responseSpecification(statusCode)).extract().response();
    }

    public <T> T post(String basePath, Object bodyPayload, Class<T> dtoClass, int statusCode) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .body(bodyPayload)
                .when()
                .post()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }

    public <T> T put(String basePath, Object bodyPayload, Class<T> dtoClass, int statusCode) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .body(bodyPayload)
                .when()
                .put()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }

    public <T> T patch(String basePath, Object bodyPayload, Class<T> dtoClass, int statusCode) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .body(bodyPayload)
                .when()
                .patch()
                .then()
                .spec(Specification.responseSpecification(statusCode))
                .extract()
                .body().as(dtoClass);
    }

    public void delete(String basePath, int statusCode) {
        given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .delete()
                .then()
                .spec(Specification.responseSpecification(statusCode))
        ;
    }
}
