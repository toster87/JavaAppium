package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {
    protected static String
    MY_LIST_LINK,
    NOT_NOW_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find navigation button to my list",
                5);
        this.waitForElementAndClick(
                NOT_NOW_BUTTON,
                "Cannot find Not now button",
                5);
    }
}
