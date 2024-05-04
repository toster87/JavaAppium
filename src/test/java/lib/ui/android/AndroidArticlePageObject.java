package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
        DESCRIPTION = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
        FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']/android.widget.TextView";
        OPTIONS_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.Button[@text='Add to list']";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        CREATED_FOLDER = "xpath://*[@text='{SUBSTRING}']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
