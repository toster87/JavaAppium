package tests;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests of changing app conditions")
public class ChangeAppConditionTests extends CoreTestCase  {

    @Test
    @Features(value = {@Feature(value = "Article"),@Feature(value = "Search"),@Feature(value = "Rotation")})
    @DisplayName("Testing Title of Article after changing screen orientation")
    @Description("We open an article and change screen orientation and make sure that Title of Article have not been changed after rotation")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearchResults() {
        if (Platform.getInstance().isMw()) {
            return;
        }

        String search_line = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String title_before_rotation = ArticlePageObject.getArticleTitle("Java (programming language)");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle("Java (programming language)");

        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle("Java (programming language)");

        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    @Features(value = {@Feature(value = "Article"),@Feature(value = "Search"),@Feature(value = "Background")})
    @DisplayName("Testing search results after going to Background")
    @Description("We initiate search of articles and wait for search results and send mobile app to background")
    @Step("Starting test testCheckSearchArticleInBackground")
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMw()) {
            return;
        }

        String search_line = "Java";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Java");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Java");
    }
}
