package tests;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class MyListsTests extends CoreTestCase  {
    @Test
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

//        if (Platform.getInstance().isMw()) {
//            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
//            Auth.clickAuthButton();
//            Auth.enterLoginData(login, password);
//            Auth.submitForm();

                ArticlePageObject.waitForTitleElement("Java (programming language)");
                assertEquals("We are not on the same page after login",
                        article_title,
                        ArticlePageObject.getArticleTitle("Java (programming language)"));
                ArticlePageObject.addArticlesToMySaved();
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
            } else {
                MyListsPageObject.swipeByArticleToDelete(article_title);
            }
    }


    @Test
    public void testSaveTwoArticlesToMyList() {

        String name_of_folder = "Learning programming";
        String articleTitle = "Appium";
        String articleDescription = "Automation for Apps";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
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
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement("Appium");
        if (Platform.getInstance().isAndroid()) {
        ArticlePageObject.addArticleToCreatedFolder(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.clickCancel();
        }

        NavigationUI.clickMyList();
        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        SearchPageObject.waitForElementByTitleAndDescription(articleTitle, articleDescription);

//        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
//        ArticlePageObject.waitForTitleElement("Appium");

    }
}
