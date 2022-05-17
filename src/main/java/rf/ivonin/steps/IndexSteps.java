package rf.ivonin.steps;

import com.codeborne.selenide.Selenide;
import rf.ivonin.dto.userDTO.SingleUserDTO;
import rf.ivonin.pages.IndexPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;
import static rf.ivonin.utils.Actions.findElementByText;

public class IndexSteps {

    private final IndexPage indexPage = new IndexPage();

    public IndexSteps openPage() {
        Selenide.open("/");
        return page(IndexSteps.class);
    }

    public IndexSteps checkPageTitle(String text) {
        indexPage.getPageTitle().should(be(visible), have(text(text)));
        return this;
    }

    public IndexSteps checkUserData(SingleUserDTO data) {


        var userCard = new IndexPage.UserCard(
                findElementByText(indexPage.getUserNameCollection(), data.getData().getFirstName()))
                .getUser();

        assertThat(data.getData()).usingRecursiveComparison()
                .ignoringFields("id", "lastName")
                .isEqualTo(userCard);

        return this;
    }
}
