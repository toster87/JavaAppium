package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static String
            TITLE,
            DESCRIPTION ,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            ADD_ARTICLE_TO_MY_LIST_BUTTON,
            ADD_SECOND_ARTICLE_TO_MY_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            NAVIGATE_UP_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            CREATE_READING_LIST_BUTTON,
            CANCEL_BUTTON,
            CREATED_FOLDER;


    public WebElement waitForTitleElement(String substring) {
        String title = getTitleOfArticle(substring);
        return this.waitForElementPresent(title, "Cannot find article title on page", 45);
    }

    public String getArticleTitle(String substring) {
        WebElement title_element = waitForTitleElement(substring);
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }
    private static String getTitleOfArticle(String substring) {
        return TITLE.replace("{SUBSTRING}", substring);
    }

    private static String getDescriptionOfArticle(String substring) {
        return DESCRIPTION.replace("{SUBSTRING}", substring);
    }

    public WebElement waitForDescriptionElement(String substring) {
        String description_of_article = getDescriptionOfArticle(substring);
        return this.waitForElementPresent(description_of_article, "Cannot find description of article on page", 15);
    }

    public String getArticleDescription(String substring) {
        WebElement description_of_article = waitForDescriptionElement(substring);
        return description_of_article.getAttribute("text");
    }

    private static String getNameOfFolderInMyList(String substring) {
        return CREATED_FOLDER.replace("{SUBSTRING}", substring);
    }

    public void waitForNameOfFolderElementAndClick(String substring) {
        String name_of_article = getNameOfFolderInMyList(substring);
        this.waitForElementAndClick(name_of_article, "Cannot find name of article " + name_of_article + " to add to my list", 15);
    }

    private static String getNameOfArticleToAddToMyList(String substring) {
        return ADD_ARTICLE_TO_MY_LIST_BUTTON.replace("{SUBSTRING}", substring);
    }

    public void waitForNameOfArticleToAddToMyListElementAndClick(String substring) {
        String article_xpath = getNameOfArticleToAddToMyList(substring);
        this.waitForElementAndClick(article_xpath, "Cannot find name of article " + article_xpath + " to add to my list", 10);
    }

    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if(already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 5).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }
    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find option button to save article",
                5);


        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to save article",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find OK button",
                10);
    }

    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find option button to save article",
                15);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    ADD_ARTICLE_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }

    public void addArticleToMyListWithNameOfArticle(String name_of_folder, String name_of_article) {
        this.addArticlesToMySaved();

//        this.waitForNameOfArticleToAddToMyListElementAndClick(name_of_article);

        this.waitForElementAndClick(
                ADD_ARTICLE_TO_MY_LIST_BUTTON,
                "Cannot find add article to my list button",
                5);

        this. waitForElementAndClick(
                CREATE_NEW_LIST_BUTTON,
                "Cannot find create new list button",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                CREATE_READING_LIST_BUTTON,
                "Cannot find create reading list button",
                10);
    }

    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    NAVIGATE_UP_BUTTON,
                    "Cannot close article",
                    5);
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    public void clickCancel() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
        this.waitForElementAndClick(
                CANCEL_BUTTON,
                "Cannot find cancel button",
                5);
        } else {
            System.out.println("Method clickCancel() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void addArticleToCreatedFolder(String name_of_folder) {
        this.waitForElementAndClick(
               OPTIONS_BUTTON,
                "Cannot find option button to save article",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to save article",
                5);

        this.waitForNameOfFolderElementAndClick(name_of_folder);
    }

    public void assertArticleHasTitle(String substring, String title) {
        String title_of_article = getTitleOfArticle(substring);
        if (Platform.getInstance().isAndroid()) {
            this.assertElementPresent(title_of_article, "text", title, "Cannot find title " + title + " of article", 10);
        } else {
            this.assertElementPresent(title_of_article, "name", title, "Cannot find title " + title + " of article", 10);
        }
    }
}
