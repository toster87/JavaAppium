package lib.ui;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
    MY_LIST_LINK,
    NOT_NOW_BUTTON,
    OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigationbutton", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyList() {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Cannot find navigation button to my list",
                    5
            );
        } else {
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
}
