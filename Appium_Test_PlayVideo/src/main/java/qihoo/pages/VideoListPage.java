package qihoo.pages;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import qihoo.appium.PageAppium;


public class VideoListPage extends PageAppium {


    public final String CURR_TIME = "com.qihoo.livecloud.plugin:id/curr_time";
    private AndroidDriver driver;
    public VideoListPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
    }

    public AndroidElement getCurrTime(){
        return findById(CURR_TIME);
    }

    public void PlayVideoOnXY(int x,int y){
        driver.tap(1,x,y,50);
    }


}
