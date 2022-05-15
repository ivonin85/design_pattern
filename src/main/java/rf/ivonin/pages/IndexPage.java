package rf.ivonin.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class IndexPage {

    public SelenideElement getPageTitle() {
        return $(By.tagName("h1"));
    }

    public ElementsCollection getUserNameCollection() { return $$(byXpath("//div[@class='flex']/div/p[1]")); }

    public ElementsCollection getUserEmailCollection() {
        return $$(byXpath("//div[@class='flex']/div/p[2]"));
    }

    public ElementsCollection getImageCollection() { return $$(".flex img"); }

    @Getter
    public static class UserCard {
        private final SelenideElement rootElement;
        private final String name;
        private final String email;
        private final String avatar;

        public UserCard(SelenideElement rootElement) {
            this.rootElement = rootElement;
            this.name = rootElement.parent().$(By.xpath("p[1]")).text();
            this.email = rootElement.parent().$(By.xpath("p[2]")).text();
            this.avatar = rootElement.parent().$(byTagName("img")).attr("src");

        }
    }


}
