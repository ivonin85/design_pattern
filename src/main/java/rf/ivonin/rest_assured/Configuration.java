package rf.ivonin.rest_assured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import static rf.ivonin.rest_assured.Specification.requestSpecification;

public class Configuration {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        //RestAssured.basePath = "basePath";

        // можно задать одну спецификацию для всех запросов:
        RestAssured.requestSpecification = requestSpecification();


    }
}
