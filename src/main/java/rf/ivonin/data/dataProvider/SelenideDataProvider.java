package rf.ivonin.data.dataProvider;

import org.testng.annotations.DataProvider;
import rf.ivonin.dto.BaseUIDTO;
import rf.ivonin.dto.HubDTO;
import rf.ivonin.dto.userDTO.SingleUserDTO;
import rf.ivonin.rest_assured.Request;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static rf.ivonin.data.constants.Endpoints.USERS;

public class SelenideDataProvider {

    @DataProvider(name = "helloReqResUsersTest")
    public static Iterator<Object[]> helloReqResUsersTest() {

        var singleUser = new Request().get(USERS + "/5", SingleUserDTO.class);

        List<HubDTO> data = List.of(
                new HubDTO()
                        .setSingleUserDTO(singleUser)
                        .setBaseUIDTO(new BaseUIDTO().setRoute("/").setPageTitle("Hello ReqRes users!"))
        );
        return data.stream().map(val -> new Object[]{val}).collect(Collectors.toList()).iterator();
    }

}
