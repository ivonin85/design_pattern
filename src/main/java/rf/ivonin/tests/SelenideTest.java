package rf.ivonin.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import rf.ivonin.data.dataProvider.SelenideDataProvider;
import rf.ivonin.dto.HubDTO;
import rf.ivonin.steps.IndexSteps;

@Epic("UI Тесты")
public class SelenideTest {

    private final IndexSteps indexSteps = new IndexSteps();

    @BeforeSuite
    public void beforeSuite() {
        Configuration.baseUrl = "https://j17lt.csb.app";
    }

    @Feature("Профиль пользователя")
    @Story("Проверка на соответствие профиля пользователя API + UI")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "helloReqResUsersTest", dataProviderClass = SelenideDataProvider.class)
    public void helloReqResUsersTest(HubDTO data) throws InterruptedException {
        indexSteps
                .open()
                .checkPageTitle("Hello ReqRes users!")
                .checkUserData(data.getSingleUserDTO());


    }


}
