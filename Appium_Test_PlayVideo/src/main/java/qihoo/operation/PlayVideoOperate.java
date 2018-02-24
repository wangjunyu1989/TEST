package qihoo.operation;


import io.appium.java_client.android.AndroidDriver;
import qihoo.appium.OperateAppium;
import qihoo.pages.PlayVideoPage;

public class PlayVideoOperate extends OperateAppium {

    private PlayVideoPage playVideoPage;

    AndroidDriver driver;

    public PlayVideoOperate(AndroidDriver driver){
        super(driver);
        playVideoPage = new PlayVideoPage(driver);
        this.driver = driver;
    }

    public void clickOnBack() {


        clickView(playVideoPage.getBack());

    }


}
