package rf.ivonin.data.dataProvider;

import org.testng.annotations.DataProvider;
import rf.ivonin.dto.BaseAPIDTO;
import rf.ivonin.dto.HubDTO;
import rf.ivonin.dto.createDTO.CreateUserDTO;
import rf.ivonin.dto.generalDTO.ResourceDataDTO;
import rf.ivonin.dto.generalDTO.SupportDTO;
import rf.ivonin.dto.generalDTO.UserDataDTO;
import rf.ivonin.dto.registerDTO.RegisterDTO;
import rf.ivonin.dto.registerDTO.RegisterResponseDTO;
import rf.ivonin.dto.resourceDTO.SingleResourceDTO;
import rf.ivonin.dto.userDTO.SingleUserDTO;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static rf.ivonin.data.constants.Endpoints.*;

public class ReqResDataProvider {
    @DataProvider(name = "registerTest")
    public static Iterator<Object[]> registerTest() {
        List<RegisterDTO> data = List.of(
                new RegisterDTO()
                        .setEmail("eve.holt@reqres.in")
                        .setPassword("pistol"),
                new RegisterDTO()
                        .setEmail("holt@reqres.in")
                        .setPassword("eve.holt")
        );
        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }


    @DataProvider(name = "registerSuccessfulTest")
    public static Iterator<Object[]> registerSuccessfulTest() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setBaseAPIDTO(new BaseAPIDTO().setRoute(REGISTER).setStatusCode(200))
                        .setRegisterDTO(new RegisterDTO().setEmail("eve.holt@reqres.in").setPassword("pistol"))
                        .setRegisterResponseDTO(new RegisterResponseDTO().setToken("QpwL5tke4Pnpja7X4").setId(4))

        );
        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }

    @DataProvider(name = "loginSuccessfulTest")
    public static Iterator<Object[]> loginSuccessfulTest() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setBaseAPIDTO(new BaseAPIDTO().setRoute(LOGIN).setStatusCode(200))
                        .setRegisterDTO(new RegisterDTO().setEmail("eve.holt@reqres.in").setPassword("cityslicka"))
                        .setRegisterResponseDTO(new RegisterResponseDTO().setToken("QpwL5tke4Pnpja7X4"))

        );
        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }

    @DataProvider(name = "singleUserTest")
    public static Iterator<Object[]> singleUserTest() {
        List<SingleUserDTO> data = List.of(
                new SingleUserDTO()
                        .setData(
                                new UserDataDTO()
                                        .setId(5)
                                        .setEmail("charles.morris@reqres.in")
                                        .setFirstName("Charles")
                                        .setLastName("Morris")
                                        .setAvatar("https://reqres.in/img/faces/5-image.jpg"))
                        .setSupport(new SupportDTO()
                                .setUrl("https://reqres.in/#support-heading")
                                .setText("To keep ReqRes free, contributions towards server costs are appreciated!")));

        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }


    @DataProvider(name = "singleResourceTest")
    public static Iterator<Object[]> singleResourceTest() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setBaseAPIDTO(new BaseAPIDTO().setRoute(RESOURCE + "/2"))
                        .setSingleResourceDTO(new SingleResourceDTO()
                                .setData(new ResourceDataDTO()
                                        .setId(2)
                                        .setColor("#C74375")
                                        .setName("fuchsia rose")
                                        .setYear(2001)
                                        .setPantoneValue("17-2031"))
                                .setSupport(new SupportDTO()
                                        .setUrl("https://reqres.in/#support-heading")
                                        .setText("To keep ReqRes free, contributions towards server costs are appreciated!"))));

        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }

    @DataProvider(name = "createTest")
    public static Iterator<Object[]> createTest() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setBaseAPIDTO(new BaseAPIDTO().setRoute(RESOURCE + "/2").setStatusCode(201))
                        .setCreateUserDTO(new CreateUserDTO()
                                .setJob("leader")
                                .setName("morpheus")));

        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }

    @DataProvider(name = "putUpdateTest")
    public static Iterator<Object[]> putUpdateTest() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setBaseAPIDTO(new BaseAPIDTO().setRoute(RESOURCE + "/2").setStatusCode(200))
                        .setCreateUserDTO(new CreateUserDTO()
                                .setName("morpheus")
                                .setJob("zion resident")));

        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }

    @DataProvider(name = "patchUpdateTest")
    public static Iterator<Object[]> patchUpdateTest() {
        List<HubDTO> data = List.of(
                new HubDTO()
                        .setBaseAPIDTO(new BaseAPIDTO().setRoute(USERS + "/2").setStatusCode(200))
                        .setCreateUserDTO(new CreateUserDTO()
                                .setName("morpheus")
                                .setJob("zion resident"))
                        .setCreateUserDTO(new CreateUserDTO()));

        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }

    @DataProvider(name = "notFoundTest")
    public static Iterator<Object[]> notFoundTest() {
        List<BaseAPIDTO> data = List.of(
                new BaseAPIDTO().setRoute(USERS + "/23").setStatusCode(404),
                new BaseAPIDTO().setRoute(RESOURCE + "/23").setStatusCode(404));

        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }
}
