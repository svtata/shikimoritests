package pages;

import com.codeborne.selenide.CollectionCondition;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public MainPage inputSearchValue(String value) {
        $("[type=\"text\"]").setValue(value);
        return this;
    }

    public MainPage clickButtonSearch() {
        $("[type=\"text\"]").pressEnter();
        return this;
    }

    public MainPage openItem(String value) {
        $(".c-column").$(byText(value)).click();
        return this;
    }

    public MainPage checkSearchItem(String value) {
        $(".c-column").shouldHave(text(value));
        return this;
    }

    public MainPage checkGenreList(List<String> value) {
        $$(".genre-ru").shouldHave(CollectionCondition.texts(value));
        return this;
    }

    public MainPage checkGenre(String value) {
        $(".b-entry-info  > :nth-child(4)").shouldHave(text(value));
        return this;
    }

}
