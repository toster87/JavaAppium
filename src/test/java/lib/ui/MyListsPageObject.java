package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON,
            SAVED_ARTICLES_ELEMENT;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Get name of folder '{name_of_folder}'")
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    @Step("Get save article by title '{article_title}'")
    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    @Step("Get remove button by title '{article_title}'")
    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }


    @Step("Open folder by name '{name_of_folder}'")
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    @Step("Waiting for article '{article_title}' to appear by title")
    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    @Step("Waiting for article '{article_title}' to disappear by title")
    public void waitForArticleToDisappearByTitle(String article_title) {
        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 25);
    }

    @Step("Swiping by article '{article_title}' to delete article from my list")
    public void swipeByArticleToDelete(String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForArticleToAppearByTitle(article_title);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article");
        } else {
                String remove_locator = getRemoveButtonByTitle(article_title);
                this.waitForElementAndClick(
                        remove_locator,
                        "Cannot click button to remove article from saved",
                        30
                );
            driver.navigate().refresh();
            }
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
    }

    @Step("Get amount of saved articles")
    public int getAmountOfSavedArticles() {
        this.waitForElementPresent(SAVED_ARTICLES_ELEMENT, "Cannot find anything in my saved", 25);
        return this.getAmountOfElements(SAVED_ARTICLES_ELEMENT);
    }
}
