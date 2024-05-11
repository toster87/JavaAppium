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
import org.openqa.selenium.By;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase  {

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Java (programming language)' article and make sure the sure is expected")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

//        ArticlePageObject.takeScreenShot("article_page");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement("Appium");

        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testArticleHasTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement("Appium");
        String title = "Appium";
        ArticlePageObject.assertArticleHasTitle("Appium", title);
    }
}
