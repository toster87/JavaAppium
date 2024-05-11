package tests;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

@Epic("Tests searching articles")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing search of articles")
    @Description("We initiate search by typing 'Java' and wait for search results")
    @Step("Starting test testSearch")
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing cancel search of articles")
    @Description("We initiate search by typing 'Java' and click Cancel and wait for cancel button to disappear")
    @Step("Starting test testCancelSearch")
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing amount of search articles")
    @Description("We initiate search by typing 'Linkin Park discography' and get amount of search articles and make sure there are more than 0 articles on the page")
    @Step("Starting test testAmountOfNotEmptySearch")
    public void testAmountOfNotEmptySearch() {

        String search_line = "Linkin Park discography";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfSearchArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing amount of empty search")
    @Description("We initiate search by typing 'zxcvafqwer' and wait for empty results label and empty search results")
    @Step("Starting test testAmountOfEmptySearch")
    public void testAmountOfEmptySearch() {

        String search_line = "zxcvafqwer";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing search input text")
    @Description("We open wikipedia URL or app and make sure search input has text 'Search Wikipedia'")
    @Step("Starting test testSearchInputText")
    public void testSearchInputText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchInputHasText("Search Wikipedia");
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing clear search results")
    @Description("We initiate search by typing 'Java', wait for results of search, click cancel search and wait for disappear of results of search")
    @Step("Starting test testCLearSearchResults")
    public void testCLearSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForAppearOfResultsOfSearch();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForDisappearOfResultsOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing search result have text")
    @Description("We initiate search by typing 'Java', wait for results of search and make sure search result have text 'Java (programming language)'")
    @Step("Starting test testSearchResultsHaveText")
    public void testSearchResultsHaveText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForAppearOfResultsOfSearch();
        SearchPageObject.assertSearchResultsHaveText("Java (programming language)");
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing title and description of search results")
    @Description("We initiate search by typing 'Java', wait for results of search and make sure search result have title 'Java (programming language)' and description 'Object-oriented programming language'")
    @Step("Starting test testTitleAndDescriptionInSearchResults")
    public void testTitleAndDescriptionInSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String articleTitle = "Java (programming language)";
        String articleDescription = "bject-oriented programming language";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForAppearOfResultsOfSearch();
        SearchPageObject.waitForElementByTitleAndDescription(articleTitle, articleDescription);
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing amount of search results")
    @Description("We initiate search by typing 'May', wait for results of search and make sure there are more than 3 articles on the page'")
    @Step("Starting test testAmountOfSearchResults")
    public void testAmountOfSearchResults() {

        String search_line = "May";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfSearchArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 3);
    }
}
