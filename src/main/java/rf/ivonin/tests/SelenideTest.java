package rf.ivonin.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import rf.ivonin.data.dataProvider.SelenideDataProvider;
import rf.ivonin.dto.HubDTO;
import rf.ivonin.steps.IndexSteps;

import static com.codeborne.selenide.Selenide.open;

@Epic("UI Тесты")
public class SelenideTest {
    private static final Logger logger = LoggerFactory.getLogger(SelenideTest.class);

    @BeforeClass
    @Parameters({"browser", "baseUrl"})
    public void beforeSuite(@Optional() String browser, @Optional() String baseUrl) {
        if (browser != null) {
            Configuration.browser = browser;
        }

        if (baseUrl != null) {
            Configuration.baseUrl = baseUrl;
        } else {
            Configuration.baseUrl = "https://j17lt.csb.app";
        }

        Configuration.remote = "http://172.22.0.2:4444/wd/hub";



    }

    @Feature("Профиль пользователя")
    @Story("Проверка на соответствие профиля пользователя API + UI")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "helloReqResUsersTest", dataProviderClass = SelenideDataProvider.class)
    public void helloReqResUsersTest(HubDTO data) {

        open("/", IndexSteps.class)
                .checkPageTitle(data.getBaseUIDTO().getPageTitle())
                .checkUserData(data.getSingleUserDTO());
    }
}
