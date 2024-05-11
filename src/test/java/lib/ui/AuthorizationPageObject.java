package lib.ui;

import io.qameta.allure.Step;
import lib.ui.factories.NavigationUiFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "xpath://span[contains(text(),'Log in')]",
    LOGIN_INPUT = "css:#wpName1",
    PASSWORD_INPUT = "css:#wpPassword1",
    SUBMIT_BUTTON = "css:#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Clicking on auth button")
    public void clickAuthButton() {
        NavigationUI NavigationUI = NavigationUiFactory.get(driver);
        NavigationUI.openNavigation();
        this.tryClickElementWithFewAttempts(
                LOGIN_BUTTON,
                "Cannot find navigation button to my list",
                15
        );
//        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button",15);
//        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button",15);
    }

    @Step("Entering login '{login}' and password '{password}'")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 15);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 15);
    }

    @Step("Clicking on submit button")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }
}
