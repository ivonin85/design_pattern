package rf.ivonin.tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import rf.ivonin.data.dataProvider.ReqResDataProvider;
import rf.ivonin.dto.BaseAPIDTO;
import rf.ivonin.dto.HubDTO;
import rf.ivonin.dto.createDTO.CreateResponseDTO;
import rf.ivonin.dto.registerDTO.RegisterDTO;
import rf.ivonin.dto.registerDTO.RegisterResponseDTO;
import rf.ivonin.dto.resourceDTO.SingleResourceDTO;
import rf.ivonin.dto.resourceListDTO.ResourceListDTO;
import rf.ivonin.dto.userDTO.SingleUserDTO;
import rf.ivonin.dto.userListDTO.UserListDTO;
import rf.ivonin.rest_assured.Request;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static rf.ivonin.data.constants.Endpoints.*;

@Epic("API Тесты")
public class ReqResTest {

    // TODO JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath);

    private final Request request = new Request();

    @Feature("GET Запросы")
    @Story("GET LIST USERS")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Тестируем параметризованный GET запрос + Список пользователей")
    @Test
    public void userListTest() {

        var params = new HashMap<String, Integer>() {{
            put("page", 2);
        }};

        var userList = request.get(USERS, params, UserListDTO.class);
    }

    @Feature("GET Запросы")
    @Story("GET SINGLE USER")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "singleUserTest", dataProviderClass = ReqResDataProvider.class)
    public void singleUserTest(SingleUserDTO data) {

        var singleUser = request.get(USERS + "/5", SingleUserDTO.class);

        assertThat(data).isEqualTo(singleUser);
    }

    @Feature("GET Запросы")
    @Story("GET SINGLE USER NOT FOUND + GET SINGLE <RESOURCE> NOT FOUND")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "notFoundTest", dataProviderClass = ReqResDataProvider.class)
    public void notFoundTest(BaseAPIDTO data) {

        var userList = request.get(data.getRoute(), data.getStatusCode());

    }

    @Feature("GET Запросы")
    @Story("GET LIST <RESOURCE>")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void resourceListTest() {

        var userList = request.get(RESOURCE, ResourceListDTO.class);
    }

    @Feature("GET Запросы")
    @Story("GET SINGLE USER JSON Schema Test")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void singleUserJSONSchemaTest() {
        var singleUser = request.get(
                USERS + "/5",
                "json-schema/single-user-schema.json",
                SingleUserDTO.class);
    }

    @Feature("GET Запросы")
    @Story("GET SINGLE <RESOURCE>")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "singleResourceTest", dataProviderClass = ReqResDataProvider.class)
    public void singleResourceTest(HubDTO data) {

        var singleResource = request.get(
                data.getBaseAPIDTO().getRoute(),
                SingleResourceDTO.class);

        assertThat(data.getSingleResourceDTO()).isEqualTo(singleResource);

    }

    @Feature("POST Запросы")
    @Story("POST CREATE")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "createTest", dataProviderClass = ReqResDataProvider.class)
    public void createTest(HubDTO data) {

        var createResponse = request.post(
                data.getBaseAPIDTO().getRoute(),
                data.getCreateRequestDTO(),
                CreateResponseDTO.class,
                data.getBaseAPIDTO().getStatusCode());

        assertThat(data.getCreateRequestDTO())
                .usingRecursiveComparison()
                .ignoringFields("id", "createdAt")
                .isEqualTo(createResponse);
    }

    @Feature("PUT Запросы")
    @Story("PUT UPDATE")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "putUpdateTest", dataProviderClass = ReqResDataProvider.class)
    public void putUpdateTest(HubDTO data) {

        var createResponse = request.put(
                data.getBaseAPIDTO().getRoute(),
                data.getCreateRequestDTO(),
                CreateResponseDTO.class,
                data.getBaseAPIDTO().getStatusCode());

        assertThat(data.getCreateRequestDTO()).usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(createResponse);
    }

    @Feature("PATCH Запросы")
    @Story("PATCH UPDATE")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "patchUpdateTest", dataProviderClass = ReqResDataProvider.class)
    public void patchUpdateTest(HubDTO data) {

        var createResponse = request.patch(
                data.getBaseAPIDTO().getRoute(),
                data.getCreateRequestDTO(),
                CreateResponseDTO.class,
                data.getBaseAPIDTO().getStatusCode());

        assertThat(data.getCreateRequestDTO()).usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(createResponse);
    }

    @Feature("DELETE Запросы")
    @Story("DELETE DELETE")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void deleteSingleUserTest() {

        request.delete(USERS + "/2", 204);

    }

    @Feature("POST Запросы")
    @Story("POST REGISTER - SUCCESSFUL")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "registerSuccessfulTest", dataProviderClass = ReqResDataProvider.class)
    public void registerSuccessfulTest(HubDTO data) {

        var registerResponse = request.post(
                data.getBaseAPIDTO().getRoute(),
                data.getRegisterDTO(),
                RegisterResponseDTO.class,
                data.getBaseAPIDTO().getStatusCode());

        assertThat(data.getRegisterResponseDTO()).isEqualTo(registerResponse);
    }

    @Feature("POST Запросы")
    @Story("POST LOGIN - SUCCESSFUL")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "loginSuccessfulTest", dataProviderClass = ReqResDataProvider.class)
    public void loginSuccessfulTest(HubDTO data) {

        var registerResponse = request.post(
                data.getBaseAPIDTO().getRoute(),
                data.getRegisterDTO(),
                RegisterResponseDTO.class,
                data.getBaseAPIDTO().getStatusCode());

        assertThat(data.getRegisterResponseDTO().getToken()).isEqualTo(registerResponse.getToken());
    }

    @Feature("POST Запросы")
    @Story("POST REGISTER - UNSUCCESSFUL")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void registerUnsuccessfulTest() {

        var data = new RegisterDTO().setEmail("sydney@fife");
        var errorMessage = "Missing password";
        var response = request.post(REGISTER, data, 400);

        assertThat(response.jsonPath().getString("error")).isEqualTo(errorMessage);
    }

    @Feature("GET Запросы")
    @Story("DELAYED RESPONSE")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 0)
    public void delayTest() {
        var millis = RestAssured.get("https://reqres.in/api/users?delay=3").time();
        assertThat(millis < 3000).as("DELAYED RESPONSE " + millis).isTrue();
    }
}
