package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        DESCRIPTION = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_BUTTON = "id:Save for later";
        ADD_ARTICLE_TO_MY_LIST_BUTTON = "id:Add “Java (programming language)” to a reading list?";
        ADD_SECOND_ARTICLE_TO_MY_LIST_BUTTON = "id:Add “Appium” to a reading list?";
        CREATE_NEW_LIST_BUTTON = "id:Create a new list";
        MY_LIST_NAME_INPUT = "id:Reading list name";
        CREATE_READING_LIST_BUTTON = "id:Create reading list";
        NAVIGATE_UP_BUTTON = "id:Back";
        CANCEL_BUTTON = "id:Cancel";
        CREATED_FOLDER = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
    }
    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
