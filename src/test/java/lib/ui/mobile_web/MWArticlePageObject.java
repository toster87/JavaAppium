package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        DESCRIPTION = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_BUTTON = "css:#ca-watch";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:.minerva-icon.minerva-icon--unStar-progressive";
        ADD_ARTICLE_TO_MY_LIST_BUTTON = "css:.minerva-icon.minerva-icon--star-base20";
        ADD_SECOND_ARTICLE_TO_MY_LIST_BUTTON = "id:Add “Appium” to a reading list?";
        CREATE_NEW_LIST_BUTTON = "id:Create a new list";
        MY_LIST_NAME_INPUT = "id:Reading list name";
        CREATE_READING_LIST_BUTTON = "id:Create reading list";
        NAVIGATE_UP_BUTTON = "id:Back";
        CANCEL_BUTTON = "id:Cancel";
        CREATED_FOLDER = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
