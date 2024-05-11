package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import static lib.ui.SearchPageObject.SEARCH_SKIP_BUTTON;

public class WelcomePageObject extends MainPageObject {

    private final static String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON = "id:Get started";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for learn more link")
    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 15);
    }

    @Step("Waiting for 'new way to explore' text")
    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' text", 15);
    }

    @Step("Waiting for 'add oк edit preferred lang' text")
    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT, "Cannot find 'Add or edit preferred languages' text", 15);
    }

    @Step("Waiting for 'learn more about data collected' text")
    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT, "Cannot find 'Learn more about data collected' text", 15);
    }

    @Step("Clicking on get started button")
    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click Get started button", 15);
    }

    @Step("Clicking on next button")
    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click Next button", 15);
    }
}
