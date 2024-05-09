package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUi extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        NOT_NOW_BUTTON = "id:Close";
    }
    public iOSNavigationUi(RemoteWebDriver driver) {
        super (driver);
    }
}
