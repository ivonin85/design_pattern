package rf.ivonin.steps;

import rf.ivonin.dto.generalDTO.UserDataDTO;
import rf.ivonin.dto.userDTO.SingleUserDTO;
import rf.ivonin.pages.IndexPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.assertThat;
import static rf.ivonin.utils.Helpers.findElementByText;

public class IndexSteps {

    private final IndexPage indexPage = new IndexPage();

    public IndexSteps checkPageTitle(String text) {
        indexPage.getPageTitle().shouldBe(be(visible), Duration.ofSeconds(10)).shouldHave(text(text));
        return this;
    }

    public IndexSteps checkUserData(SingleUserDTO data) {

        UserDataDTO userCard = new IndexPage.UserCard(
                findElementByText(indexPage.getUserNameCollection(), data.getData().getFirstName()))
                .getUser();

        assertThat(data.getData()).usingRecursiveComparison()
                .ignoringFields("id", "lastName")
                .isEqualTo(userCard);

        return this;
    }
}
