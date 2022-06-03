package rf.ivonin.rest_assured;

import io.restassured.response.Response;
import lombok.NonNull;

import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Request {

    public Response get(@NonNull String basePath, @NonNull Integer statusCode) throws Exception {
        return Optional.ofNullable(given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(statusCode)).extract().response())
                .orElseThrow(Exception::new);
    }

    public <T> T get(@NonNull String basePath, @NonNull Class<T> dtoClass) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .when()
                .get()
                .then()
                .spec(Specification.responseSpecification(200))
                .extract().body().as(dtoClass);
    }

    public <T> T get(@NonNull String basePath, @NonNull String JSONSchema, @NonNull Class<T> dtoClass) {
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

    public <T> T get(@NonNull String basePath, @NonNull Map<String, ?> params, @NonNull Class<T> dtoClass) {
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

    public Response post(@NonNull String basePath, @NonNull Object bodyPayload, @NonNull Integer statusCode) {
        return given()
                .spec(Specification.requestSpecification())
                .basePath(basePath)
                .body(bodyPayload)
                .when()
                .post()
                .then()
                .spec(Specification.responseSpecification(statusCode)).extract().response();
    }

    public <T> T post(@NonNull String basePath, @NonNull Object bodyPayload, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
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

    public <T> T put(@NonNull String basePath, @NonNull Object bodyPayload, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
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

    public <T> T patch(@NonNull String basePath, @NonNull Object bodyPayload, @NonNull Class<T> dtoClass, @NonNull Integer statusCode) {
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

    public void delete(@NonNull String basePath, @NonNull Integer statusCode) {
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
