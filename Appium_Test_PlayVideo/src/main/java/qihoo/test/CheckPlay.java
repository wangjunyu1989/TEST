package qihoo.test;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import qihoo.callback.PlayVideoCallback;
import qihoo.pages.PlayVideoPage;

/**
 * Created by wangjunyu on 2018/2/6.
 */

public class CheckPlay extends PlayVideoPage {
    private int checkTime;
    AndroidDriver driver;
    public CheckPlay(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }
    //超时时间，超过timeout时间还未成功播放视频，认为播放失败
    public int checkIsPlay(int timeout, PlayVideoCallback playVideoCallback) {
        boolean isFinished = false;
        PlayVideoPage playVideoPage =  new PlayVideoPage(driver);
        AndroidElement androidElement = playVideoPage.getCurrTime();
        int countTime = 0;
        while (!isFinished){
            String currTime = androidElement.getText().toString();
            if (getSecond(currTime)>=1){
                isFinished = true;
                playVideoCallback.playSuc(1,"paly video suc");
                System.out.print("paly video suc");
                return 1;
            }
            try {
                Thread.sleep(1000);
                countTime+=1000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (countTime ==timeout){
                playVideoCallback.playFail(0,"paly videofail");
                isFinished = true;
                System.out.print("paly video fail");
                return -1;
            }
        }
        return 0;
    }

    private int getSecond(String time){
        int second=0;
        try {
            second = Integer.parseInt(time.split(":")[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return second;
    }

}
