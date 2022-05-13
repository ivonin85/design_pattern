package rf.ivonin.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;

public class Helper {

    public static SelenideElement findByText(ElementsCollection elementsCollection, String text){
        return elementsCollection.find(text(text));
    }

}
