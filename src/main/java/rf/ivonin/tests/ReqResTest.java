package rf.ivonin.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import rf.ivonin.data.dataProvider.TestDataProvider;
import rf.ivonin.data.dto.BaseDTO;
import rf.ivonin.data.dto.HubDTO;
import rf.ivonin.data.dto.createDTO.CreateResponseDTO;
import rf.ivonin.data.dto.registerDTO.RegisterDTO;
import rf.ivonin.data.dto.registerDTO.RegisterResponseDTO;
import rf.ivonin.data.dto.resourceDTO.SingleResourceDTO;
import rf.ivonin.data.dto.resourceListDTO.ResourceListDTO;
import rf.ivonin.data.dto.userDTO.SingleUserDTO;
import rf.ivonin.data.dto.userListDTO.UserListDTO;
import rf.ivonin.rest_assured.Request;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static rf.ivonin.data.constants.Endpoints.*;


public class ReqResTest {

    /**
     * TODO JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath);
     * TODO статус коды вынести в датапровайдеры
     **/

    Request request = new Request();

    @Test(description = "GET LIST USERS")
    public void userListTest() {

        var params = new HashMap<String, Integer>() {{
            put("page", 2);
        }};

        var userList = request.get(USERS, params, UserListDTO.class);
    }

    @Test(description = "GET SINGLE USER",
            dataProvider = "singleUserTest", dataProviderClass = TestDataProvider.class)
    public void singleUserTest(SingleUserDTO data) {

        var singleUser = request.get(USERS + "/5", SingleUserDTO.class);
        assertThat(data).isEqualTo(singleUser);
    }

    @Test(description = "GET SINGLE USER NOT FOUND + GET SINGLE <RESOURCE> NOT FOUND",
            dataProvider = "notFoundTest", dataProviderClass = TestDataProvider.class)
    public void notFoundTest(BaseDTO data) {

        var userList = request.get(data.getRoute(), data.getStatusCode());

    }

    @Test(description = "GET LIST <RESOURCE>")
    public void resourceListTest() {

        var userList = request.get(RESOURCE, ResourceListDTO.class);
    }

    @Test(description = "GET SINGLE USER JSON Schema Test")
    public void singleUserJSONSchemaTest() {
        var singleUser = request.get(
                USERS + "/5",
                "json-schema/single-user-schema.json",
                SingleUserDTO.class);
    }

    @Test(description = "GET SINGLE <RESOURCE>",
            dataProvider = "singleResourceTest", dataProviderClass = TestDataProvider.class)
    public void singleResourceTest(HubDTO data) {

        var singleResource = request.get(
                data.getBaseDTO().getRoute(),
                SingleResourceDTO.class);

        assertThat(data.getSingleResourceDTO()).isEqualTo(singleResource);

    }

    @Test(description = "POST CREATE",
            dataProvider = "createTest", dataProviderClass = TestDataProvider.class)
    public void createTest(HubDTO data) {

        var createResponse = request.post(
                data.getBaseDTO().getRoute(),
                data.getCreateRequestDTO(),
                CreateResponseDTO.class,
                data.getBaseDTO().getStatusCode());

        assertThat(data.getCreateRequestDTO()).usingRecursiveComparison()
                .ignoringFields("id", "createdAt")
                .isEqualTo(createResponse);
    }

    @Test(description = "PUT UPDATE",
            dataProvider = "putUpdateTest", dataProviderClass = TestDataProvider.class)
    public void putUpdateTest(HubDTO data) {

        var createResponse = request.put(
                data.getBaseDTO().getRoute(),
                data.getCreateRequestDTO(),
                CreateResponseDTO.class,
                data.getBaseDTO().getStatusCode());

        assertThat(data.getCreateRequestDTO()).usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(createResponse);
    }

    @Test(description = "PATCH UPDATE",
            dataProvider = "patchUpdateTest", dataProviderClass = TestDataProvider.class)
    public void patchUpdateTest(HubDTO data) {

        var createResponse = request.patch(
                data.getBaseDTO().getRoute(),
                data.getCreateRequestDTO(),
                CreateResponseDTO.class,
                data.getBaseDTO().getStatusCode());

        assertThat(data.getCreateRequestDTO()).usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(createResponse);
    }

    @Test(description = "DELETE DELETE")
    public void deleteSingleUserTest() {

        request.delete(USERS + "/2", 204);

    }

    @Test(description = "POST REGISTER - SUCCESSFUL",
            dataProvider = "registerSuccessfulTest", dataProviderClass = TestDataProvider.class)
    public void registerSuccessfulTest(HubDTO data) {

        var registerResponse = request.post(
                data.getBaseDTO().getRoute(),
                data.getRegisterDTO(),
                RegisterResponseDTO.class,
                data.getBaseDTO().getStatusCode());

        assertThat(data.getRegisterResponseDTO()).isEqualTo(registerResponse);
    }

    @Test(description = "POST LOGIN - SUCCESSFUL",
            dataProvider = "loginSuccessfulTest", dataProviderClass = TestDataProvider.class)
    public void loginSuccessfulTest(HubDTO data) {

        var registerResponse = request.post(
                data.getBaseDTO().getRoute(),
                data.getRegisterDTO(),
                RegisterResponseDTO.class,
                data.getBaseDTO().getStatusCode());

        assertThat(data.getRegisterResponseDTO().getToken()).isEqualTo(registerResponse.getToken());
    }

    @Test(description = "POST REGISTER - UNSUCCESSFUL")
    public void registerUnsuccessfulTest() {

        var data = new RegisterDTO().setEmail("sydney@fife");
        var errorMessage = "Missing password";
        var response = request.post(REGISTER, data, 400);

        assertThat(response.jsonPath().getString("error")).isEqualTo(errorMessage);
    }

    @Test(description = "DELAYED RESPONSE")
    public void delayTest() {
        long millis = RestAssured.get("https://reqres.in/api/users?delay=3").time();
        assertThat(millis < 3000).as("DELAYED RESPONSE").isTrue();
    }
}
