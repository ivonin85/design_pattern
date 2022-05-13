package rf.ivonin.steps;

import com.codeborne.selenide.Selenide;
import rf.ivonin.dto.userDTO.SingleUserDTO;
import rf.ivonin.pages.IndexPage;
import rf.ivonin.rest_assured.Request;

import static com.codeborne.selenide.Condition.text;
import static org.assertj.core.api.Assertions.assertThat;
import static rf.ivonin.utils.Helper.findByText;

public class IndexSteps {

    private final IndexPage indexPage = new IndexPage();
    private final Request request = new Request();

    public IndexSteps open() {
        Selenide.open("/");
        return this;
    }

    public IndexSteps checkPageTitle(String text) {
        indexPage.getPageTitle().shouldHave(text(text));
        return this;
    }

    public IndexSteps checkUserData(SingleUserDTO data) {

        var userCard = new IndexPage.UserCard(findByText(indexPage.getUsersNameCollection(), data.getData().getFirstName()));

        assertThat(data.getData().getEmail()).isEqualTo(userCard.getEmail());
        assertThat(data.getData().getFirstName()).isEqualTo(userCard.getName());
        assertThat(data.getData().getAvatar()).isEqualTo(userCard.getAvatar());


        return this;
    }
}
