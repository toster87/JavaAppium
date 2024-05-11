package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
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
    @Step("Get result search element by substring")
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    @Step("Get title and description search element by substring")
    private static String getTitleAndDescriptionOfSearchElement(String substring) {
        return SEARCH_TITLE_AND_DESCRIPTION_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /*TEMPLATES METHODS*/
    @Step("Initializing the search filed")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 30);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 10);
    }

    @Step("Waiting for search result by substring")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 20);
    }

    @Step("Waiting for element by title '{substringTitle}' and description '{substringDescription}' by substring in article title")
    public void waitForElementByTitleAndDescription(String substringTitle, String substringDescription) {
        String search_title_xpath = getTitleAndDescriptionOfSearchElement(substringTitle);
        String search_description_xpath = getTitleAndDescriptionOfSearchElement(substringDescription);
        this.waitForElementPresent(search_title_xpath, "Cannot find title in search result with substring " + substringTitle, 20);
        this.waitForElementPresent(search_description_xpath, "Cannot find description in search result with substring " + substringDescription, 20);
    }


    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 45);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfSearchArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty results label")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result label by the request", 15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results by request");
    }

    @Step("Waiting for appear of results of search")
    public void waitForAppearOfResultsOfSearch() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "We supposed to find any results by request", 30);
    }

    @Step("Waiting for disappear of results of search")
    public void waitForDisappearOfResultsOfSearch() {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "Search results are still present on page", 10);
    }

    @Step("Waiting for search input text")
    public WebElement waitForSearchInputText() {
        return this.waitForElementPresent(SEARCH_INPUT_TEXT, "Cannot find search input text", 10);
    }

    @Step("Clearing search input and typing search line '{search_line}'")
    public void clearSearchInputAndTypeSearchLine(String search_line) {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
        this.waitForElementAndClear(SEARCH_INPUT_TEXT, "Cannot find and clear search input text", 10);
        this.waitForElementAndSendKeys(SEARCH_INPUT_TEXT, search_line, "Cannot find and type into search input", 5);
    } else {
            System.out.println("Method clearSearchInputAndTypeSearchLine() does nothing for platform " + Platform.getInstance().getPlatformVar());}
    }

    @Step("Asserting search input has text '{value}'")
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

    @Step("Asserting search results have text '{text}'")
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

    @Step("Clicking on skip button")
    public void clickSkip() {
        this.waitForElementAndClick(SEARCH_SKIP_BUTTON, "Cannot find and click skip button", 5);
    }
}
