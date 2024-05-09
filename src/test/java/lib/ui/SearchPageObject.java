package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_TITLE_AND_DESCRIPTION_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_SKIP_BUTTON,
            SEARCH_INPUT_TEXT;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTitleAndDescriptionOfSearchElement(String substring) {
        return SEARCH_TITLE_AND_DESCRIPTION_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /*TEMPLATES METHODS*/
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 30);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 10);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 20);
    }

    public void waitForElementByTitleAndDescription(String substringTitle, String substringDescription) {
        String search_title_xpath = getTitleAndDescriptionOfSearchElement(substringTitle);
        String search_description_xpath = getTitleAndDescriptionOfSearchElement(substringDescription);
        this.waitForElementPresent(search_title_xpath, "Cannot find title in search result with substring " + substringTitle, 20);
        this.waitForElementPresent(search_description_xpath, "Cannot find description in search result with substring " + substringDescription, 20);
    }


    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 45);
    }

    public int getAmountOfSearchArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result label by the request", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results by request");
    }

    public void waitForAppearOfResultsOfSearch() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "We supposed to find any results by request", 30);
    }

    public void waitForDisappearOfResultsOfSearch() {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "Search results are still present on page", 10);
    }

    public WebElement waitForSearchInputText() {
        return this.waitForElementPresent(SEARCH_INPUT_TEXT, "Cannot find search input text", 10);
    }

    public void clearSearchInputAndTypeSearchLine(String search_line) {
        this.waitForElementAndClear(SEARCH_INPUT_TEXT, "Cannot find and clear search input text", 10);
        this.waitForElementAndSendKeys(SEARCH_INPUT_TEXT, search_line, "Cannot find and type into search input", 5);
    }

    public void assertSearchInputHasText(String value) {
        WebElement text_element = waitForSearchInputText();
        if (Platform.getInstance().isAndroid()) {
            String inputText = text_element.getAttribute("text");
            this.assertElementHasText("Cannot find input text", value, inputText);
        } else {
            if (Platform.getInstance().isMw()) {
                String inputText = text_element.getAttribute("placeholder");
                this.assertElementHasText("Cannot find input text", value, inputText);
            } else {
                String inputText = text_element.getAttribute("name");
                this.assertElementHasText("Cannot find input text", value, inputText);
            }
        }
    }

    public void assertSearchResultsHaveText(String text) {
        boolean found = false;
        List<WebElement> searchResults = findElements(SEARCH_RESULT_ELEMENT);
        for (WebElement webElement : searchResults) {
            if (webElement.getText().contains(text)) {
                found = true;
                break;
            }
        }
        if (!found)
            Assert.fail("Search results have no text " + text);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SEARCH_SKIP_BUTTON, "Cannot find and click skip button", 5);
    }
}
