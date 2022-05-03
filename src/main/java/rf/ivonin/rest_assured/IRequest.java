package rf.ivonin.rest_assured;

import io.restassured.response.Response;

import java.util.Map;

public interface IRequest {
    <T> T get(String locationHeader, Class<T> dtoClass);

    <T> T get(String path, String JSONSchema, Class<T> dtoClass);

    <T> T get(String locationHeader, Map<String, ?> params, Class<T> dtoClass);

    <T> Response post(String path, Object bodyPayload, int statusCode);

    <T> T post(String path, Object bodyPayload, Class<T> dtoClass, int statusCode);

    void delete(String path, int statusCode);
}
