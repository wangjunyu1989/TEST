package qihoo.pages;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import qihoo.appium.PageAppium;


public class PlayVideoPage extends PageAppium {


    public final String CURR_TIME = "com.qihoo.livecloud.plugin:id/curr_time";

    public final String LEFT_ICON = "com.qihoo.livecloud.plugin:id/headerLeftIcon";

    public PlayVideoPage(AndroidDriver driver){
        super(driver);
    }

    public AndroidElement getCurrTime(){
        return findById(CURR_TIME);
    }

    public AndroidElement getBack(){
        return findById(LEFT_ICON);
    }


}
