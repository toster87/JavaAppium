package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUi extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        NOT_NOW_BUTTON = "id:Close";
    }
    public iOSNavigationUi(AppiumDriver driver) {
        super (driver);
    }
}
