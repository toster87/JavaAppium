package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUi extends NavigationUI {
    static {
    MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='Saved']";
    NOT_NOW_BUTTON = "xpath://android.widget.Button[@text='Not now']";
    }
    public AndroidNavigationUi(AppiumDriver driver) {
        super(driver);
    }
}
