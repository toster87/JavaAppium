package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUi extends NavigationUI {
    static {
        MY_LIST_LINK = "css:a[data-event-name='menu.watchlist']";
        NOT_NOW_BUTTON = "id:Close";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }
    public MWNavigationUi (RemoteWebDriver driver) {
        super (driver);
    }
}
