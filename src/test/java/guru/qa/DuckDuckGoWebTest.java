package guru.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckGoWebTest {

    @BeforeEach
    void setUp() {
        open("https://www.duckduckgo.com/");
    }

    @Test
    @Tag("BLOCKER")
    @DisplayName("Not empty cards list should be shown for search request 'selenide")
    void successfulSearchTest() {
        $("#searchbox_input").setValue("selenide").pressEnter();

        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }

    @ValueSource(strings = {
            "selenide", "junit 5"
    })
    @ParameterizedTest(name = "Not empty cards list should be shown for search request {0}")
    @Tag("BLOCKER")
    void successfulSearchJunitTest(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();

        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }

//    @CsvSource(value = {
//            "Selenide , https://selenide.org",
//            "JUnit 5 , https://junit.org"
//    })
    @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedUrl.csv")
    @ParameterizedTest(name = "For search request {0} link {1} should be in the first card")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(expectedLink));
    }

    @Test
    @Tag("BLOCKER")
    @DisplayName("Not empty photos list should be shown for search request 'selenide")
    void successfulSearchPhotoTest() {
        $("#searchbox_input").setValue("selenide").pressEnter();
        $("#react-duckbar").$(byText("Images")).click();

        $$("img.tile--img__img").shouldBe(sizeGreaterThan(0));
    }
}
