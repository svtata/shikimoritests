package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.MainPage;

public class TestBase {
    protected MainPage mainPage = new MainPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://shikimori.one/";
        Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true;
    }
}
