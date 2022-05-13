package rf.ivonin.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class IndexPage {

    private SelenideElement pageTitle = $(By.tagName("h1"));
    private ElementsCollection usersNameCollection = $$(By.xpath("//div[@class='flex']/div/p[1]"));
    private ElementsCollection usersEmailsCollection = $$(By.xpath("//div[@class='flex']/div/p[2]"));


    @Getter
    @EqualsAndHashCode
    @ToString
    public static class UserCard {
        private SelenideElement rootElement;
        private String name;
        private String email;
        private String avatar;

        public UserCard(SelenideElement rootElement) {
            this.rootElement = rootElement;
            this.name = rootElement.parent().$(By.xpath("p[1]")).text();
            this.email = rootElement.parent().$(By.xpath("p[2]")).text();
            this.avatar = rootElement.parent().$(byTagName("img")).attr("src");

        }
    }


}
