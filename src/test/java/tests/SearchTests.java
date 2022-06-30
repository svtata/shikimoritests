package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("Тестирование поиска")
public class SearchTests extends TestBase {

    @ValueSource(strings = {"Сила сердца", "Шёпот сердца"})
    @ParameterizedTest(name = "Поиск по слову {0}")
    void searchTest(String itemName) {
        Selenide.open(Configuration.baseUrl);
        mainPage.inputSearchValue(itemName)
                .clickButtonSearch()
                .checkSearchItem(itemName);
    }

    @CsvSource(value = {"Мой сосед Тоторо, Сверхъестественное",
            "Шёпот сердца, Романтика"})
    @ParameterizedTest(name = "Аниме {0} содержит тэг жанра {1}")
    void checkGenre(String itemName, String genre) {
        Selenide.open(Configuration.baseUrl);
        mainPage.inputSearchValue(itemName)
                .clickButtonSearch()
                .openItem(itemName)
                .checkGenre(genre);
    }

    @CsvFileSource(resources = "/test_data/1.csv")
    @ParameterizedTest(name = "Аниме {0} содержит тэг жанра {1}")
    void checkGenreWithFile(String itemName, String genre) {
        Selenide.open(Configuration.baseUrl);
        mainPage.inputSearchValue(itemName)
                .clickButtonSearch()
                .openItem(itemName)
                .checkGenre(genre);
    }

    static Stream<Arguments> checkGenreWithMethodSourse() {
        return Stream.of(
                Arguments.of("Мой сосед Тоторо", List.of("Приключения", "Сверхъестественное")),
                Arguments.of("Шёпот сердца", List.of("Сёдзё", "Драма", "Романтика", "Повседневность"))
        );
    }

    @MethodSource(value = "checkGenreWithMethodSourse")
    @ParameterizedTest(name = "Аниме {0} содержит тэги жанров {1}")
    void checkGenreWithMethodSourse(String itemName, List<String> genre) {
        Selenide.open(Configuration.baseUrl);
        mainPage.inputSearchValue(itemName)
                .clickButtonSearch()
                .openItem(itemName)
                .checkGenreList(genre);
    }

    @EnumSource(Anime.class)
    @ParameterizedTest(name = "Поиск по слову {0}")
    void searchTestEnum(Anime anime) {
        Selenide.open(Configuration.baseUrl);
        mainPage.inputSearchValue(anime.desc)
                .clickButtonSearch()
                .checkSearchItem(anime.desc);
    }
}
