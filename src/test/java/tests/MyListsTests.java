package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Tests of adding articles to the lists")
public class MyListsTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "My lists"),@Feature(value = "Article")})
    @DisplayName("Testing save one article to my list and deleting it")
    @Description("We search an article, add it to list, go to the list and delete this article")
    @Step("Starting test testSaveFirstArticleToMyList")
    public void testSaveFirstArticleToMyList() {

        String name_of_folder = "Learning programming";
        String login = "Toster87";
        String password = "AppiumTest";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
        }
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.closeArticle();
            } else {
                ArticlePageObject.clickCancel();
            }
            NavigationUI.openNavigation();
            NavigationUI.clickMyList();
            if (Platform.getInstance().isAndroid()) {
                MyListsPageObject.openFolderByName(name_of_folder);
            } else {
                MyListsPageObject.swipeByArticleToDelete(article_title);
                MyListsPageObject.waitForArticleToDisappearByTitle(article_title);
            }
        }
    }


    @Test
    @Features(value = {@Feature(value = "My lists"),@Feature(value = "Article")})
    @DisplayName("Testing save two article to list and delete one of these articles from the list")
    @Description("We search two articles, add it to list, open my lists, delete one of the article and make article was deleted")
    @Step("Starting test testSaveTwoArticlesToMyList")
    public void testSaveTwoArticlesToMyList() {

        String name_of_folder = "Learning programming";
        String login = "Toster87";
        String password = "AppiumTest";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
        }
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");
        ArticlePageObject.waitForTitleElement("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        }

        //Second article
        SearchPageObject.clearSearchInputAndTypeSearchLine("Appium");
        if (Platform.getInstance().isMw()) {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Appium");
        }
        SearchPageObject.clickByArticleWithSubstring("utomation for Apps");
        ArticlePageObject.waitForTitleElement("Appium");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToCreatedFolder(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()) {
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.clickCancel();
        }
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title);

        int amount_of_saved_articles = MyListsPageObject.getAmountOfSavedArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_saved_articles < 2);
    }
//        SearchPageObject.waitForElementByTitleAndDescription(articleTitle, articleDescription);
//        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
//        ArticlePageObject.waitForTitleElement("Appium");

}
