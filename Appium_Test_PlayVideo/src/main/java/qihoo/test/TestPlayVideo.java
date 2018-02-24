package qihoo.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.NumberFormat;

import qihoo.appium.InitAppium;
import qihoo.callback.PlayVideoCallback;
import qihoo.operation.PlayVideoOperate;
import qihoo.pages.VideoListPage;


public class TestPlayVideo extends InitAppium {

    private  VideoListPage videoListPage;
    private  CheckPlay checkPlay;
    private int sum = 100;
    private int errorConut = 0;
    private  PlayVideoOperate playVideoOperate;

    @Before
    public  void initDriver() {
        Assert.assertNotNull(driver);
        videoListPage = new VideoListPage(driver);
        playVideoOperate = new PlayVideoOperate(driver);
        checkPlay = new CheckPlay(driver);
    }

    @Test
    public void LoopPlay() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int result;
        PlayVideoCallback playCallBack = new PlayVideoCallback() {
            @Override
            public void playSuc(int paramInt, String paramString) {

            }

            @Override
            public void playFail(int paramInt, String paramString) {
                errorConut += 1;
                System.out.print("ʧ�ܴ���  " + errorConut);
            }
        };
        for (int i = 0; i < 10; i++) {
            PlayVideoOnXY(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = checkPlay.checkIsPlay(2000, playCallBack);
            playVideoOperate.clickOnBack();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //��Ƶ��570����Ƶ���20
            //driver.swipe(700, 875, 700, 485, 700);
            driver.swipe(700, 875, 700, 395, 700);
//            int width = driver.manage().window().getSize().width;//��ȡ��ǰ��Ļ�Ŀ��
//            int height = driver.manage().window().getSize().height;//��ȡ��ǰ��Ļ�ĸ߶�
//            driver.swipe(width/2, height*3/4, width/2, height/4, 700);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FailRate(errorConut,10);
    }

    private void PlayVideoOnXY(int i) {
        driver.tap(1, 700, 500, 50);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String FailRate(int errorConut,int sum) {
        System.out.print("  ʧ���ܴ���  " + errorConut);
        // ����һ����ֵ��ʽ������
        NumberFormat numberFormat = NumberFormat.getInstance();
        // ���þ�ȷ��С�����5λ
        numberFormat.setMaximumFractionDigits(5);
        String result = numberFormat.format((float) errorConut / (float) sum * 100);
        System.out.print("  ʧ����  " + result+"%");
        return result;
    }


}
