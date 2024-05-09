package tests;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        String search_line = "Linkin Park discography";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfSearchArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {

        String search_line = "zxcvafqwer";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchInputText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchInputHasText("Search Wikipedia");
    }

    @Test
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
    public void testSearchResultsHaveText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForAppearOfResultsOfSearch();
        SearchPageObject.assertSearchResultsHaveText("Java (programming language)");
    }

    @Test
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
    public void testAmountOfSearchResults() {

        String search_line = "Linkin Park discography";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfSearchArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 3);
    }
}
