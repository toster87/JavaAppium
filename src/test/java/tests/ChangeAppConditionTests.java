package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase  {

    @Test
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
