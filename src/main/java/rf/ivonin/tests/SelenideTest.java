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

    private final IndexSteps indexPageSteps = new IndexSteps();

    @BeforeSuite
    public void beforeSuite() {
        Configuration.baseUrl = "https://j17lt.csb.app";
        Configuration.browser = "firefox";

    }

    @Feature("Профиль пользователя")
    @Story("Проверка на соответствие профиля пользователя API + UI")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "helloReqResUsersTest", dataProviderClass = SelenideDataProvider.class)
    public void helloReqResUsersTest(HubDTO data) {
        indexPageSteps
                .openPage()
                .checkPageTitle(data.getBaseUIDTO().getPageTitle())
                .checkUserData(data.getSingleUserDTO());


    }


}
