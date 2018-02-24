package qihoo.appium;

import org.apache.http.util.TextUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


import static qihoo.appium.InitAppium.appPackage;
import static io.appium.java_client.android.AndroidKeyCode.BACKSPACE;
import static io.appium.java_client.android.AndroidKeyCode.KEYCODE_MOVE_END;

public class OperateAppium {

    AndroidDriver driver;

    //��������������
    TouchAction touchAction;

    //�����������ʱ��
    MultiTouchAction multiTouchAction;

    private static int WAIT_TIME = 3;    //Ĭ�ϵĵȴ��ؼ�ʱ��

    private final int SWIPE_DEFAULT_PERCENT = 5;   //Ĭ�ϻ����ٷֱ�

    public final String SWIP_UP = "UP",
            SWIP_DOWN = "DOWN",
            SWIP_LEFT = "LEFT",
            SWIP_RIGHT = "RIGHT";


    public OperateAppium(AndroidDriver androidDriver) {
        this.driver = androidDriver;
    }


    /**
     * ��ӡ�ַ�
     *
     * @param str Ҫ��ӡ���ַ�
     */
    public <T> void print(T str) {
        if (!TextUtils.isEmpty(String.valueOf(str))) {
            System.out.println(str);
        } else {
            System.out.println("����˿��ַ�");
        }
    }

    /**
     * Click����ո��
     *
     * @param ae Ҫ����Ŀؼ�
     * @return �����Ƿ���
     */
    public boolean clickView(AndroidElement ae) {
        return clickView(ae, "");
    }


    /**
     * Click����ؼ�
     *
     * @param ae  �ؼ�
     * @param str �ؼ�������������������ʱ�����
     * @return �����Ƿ���ڿؼ�
     */
    public boolean clickView(AndroidElement ae, String str) {
        if (ae != null) {
            ae.click();
            return true;
        } else {
            print(str + "Ϊ�գ��������");
        }
        return false;
    }


    /**
     * �߳�������������λ��
     *
     * @param s Ҫ���ߵ�����
     */
    public void sleep(long s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ȡ��ǰ��activity,�����ļ���
     *
     * @return
     */
    public String getCurrActivity() {
        String str = driver.currentActivity();
        return str.substring(str.lastIndexOf(".") + 1);
    }

    /**
     * ��ȡ����ʵ��
     *
     * @return
     */
    public TouchAction getTouch() {
        if (driver == null) {
            print("���㴥��ʱ��driverΪ��");
            return null;
        } else {
            if (touchAction == null) {
                return new TouchAction(driver);
            } else {
                return touchAction;
            }

        }
    }


    /**
     * ��ȡ��㴥��ʵ��
     *
     * @return
     */
    public MultiTouchAction getMultiTouch() {
        if (driver == null) {
            print("��㴥��ʱ��driverΪ��");
            return null;
        } else {
            if (multiTouchAction == null) {
                return new MultiTouchAction(driver);
            } else {
                return multiTouchAction;
            }

        }
    }

    /**
     * ���ؼ������ַ���
     *
     * @param ae  Ҫ����Ŀؼ�
     * @param str Ҫ������ַ���
     */
    public void input(AndroidElement ae, String str) {
        if (ae == null) {
            print("�ؼ�Ϊ��,��������ʧ��:" + str);
        } else {
            ae.sendKeys(str);
        }

    }

    public void swipeToUp(int during) {
        swipeToUp(during, SWIPE_DEFAULT_PERCENT);
    }

    /**
     * ���ϻ�����
     *
     * @param during
     */
    public void swipeToUp(int during, int percent) {
        int width = getScreenWidth();
        int height = getScreenHeight();
        driver.swipe(width / 2, height * (percent - 1) / percent, width / 2, height / percent, during);
    }

    public void swipeToDown(int during) {
        swipeToDown(during, SWIPE_DEFAULT_PERCENT);
    }

    /**
     * ���»�����
     *
     * @param during ����ʱ��
     */
    public void swipeToDown(int during, int percent) {
        int width = getScreenWidth();
        int height = getScreenHeight();
        driver.swipe(width / 2, height / percent, width / 2, height * (percent - 1) / percent, during);
    }


    public void swipeToLeft(int during) {
        swipeToLeft(during, SWIPE_DEFAULT_PERCENT);
    }

    /**
     * ���󻬶���
     *
     * @param during  ����ʱ��
     * @param percent λ�õİٷֱȣ�2-10�� ����3���� ��2/3����1/3
     */
    public void swipeToLeft(int during, int percent) {
        int width = getScreenWidth();
        int height = getScreenHeight();
        driver.swipe(width * (percent - 1) / percent, height / 2, width / percent, height / 2, during);
    }


    public void swipeToRight(int during) {
        swipeToRight(during, SWIPE_DEFAULT_PERCENT);
    }

    /**
     * ���һ�����
     *
     * @param during  ����ʱ��
     * @param percent λ�õİٷֱȣ�2-10�� ����3���� ��1/3����2/3
     */
    public void swipeToRight(int during, int percent) {
        int width = getScreenWidth();
        int height = getScreenHeight();
        driver.swipe(width / percent, height / 2, width * (percent - 1) / percent, height / 2, during);
    }


    /**
     * ��ʾ�ȴ����ȴ�Id��Ӧ�Ŀؼ�����time�룬һ�������Ϸ��أ�time�벻����Ҳ����
     */
    public AndroidElement waitAuto(final By by, int time) {
        try {

            return new AndroidDriverWait(driver, time)
                    .until(new qihoo.appium.ExpectedCondition<AndroidElement>() {
                        @Override
                        public AndroidElement apply(AndroidDriver androidDriver) {
                            return (AndroidElement) androidDriver.findElement(by);
                        }
                    });
        } catch (TimeoutException e) {
            print("����Ԫ�س�ʱ!! " + time + " ��֮��û�ҵ�Ԫ�� [" + by.toString() + "]");
            return null;
        }
    }

    public AndroidElement waitAutoById(String id) {
        return waitAutoById(id, WAIT_TIME);
    }

    public AndroidElement waitAutoById(String id, int time) {
        return waitAuto(By.id(id), time);
    }

    public AndroidElement waitAutoByName(String name) {
        return waitAutoByName(name, WAIT_TIME);
    }

    public AndroidElement waitAutoByName(String name, int time) {
        return waitAuto(By.name(name), time);
    }


    public AndroidElement waitAutoByXp(String xPath) {
        return waitAutoByXp(xPath, WAIT_TIME);
    }

    public AndroidElement waitAutoByXp(String xPath, int time) {
        return waitAuto(By.xpath(xPath), time);
    }

    public void waitAuto() {
        waitAuto(WAIT_TIME);
    }

    /**
     * ����ʽ�ȴ��������ָ��ʱ���ڻ����Ҳ����¸�Ԫ����ᱨ��ֹͣ�ű�
     * ȫ���趨�ģ�find�ؼ��Ҳ����ͻᰴ������¼����ȴ�
     *
     * @param time Ҫ�ȴ���ʱ��
     */
    public void waitAuto(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    /**
     * ��Activity
     *
     * @param activityName activity������
     */
    public void startActivity(String activityName) {
        driver.startActivity(appPackage, activityName);
    }


    /**
     * ��ȡ��ǰ���������EditText����������������
     *
     * @param str Ҫ���������
     */
    public void inputManyText(String... str) {
        List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        for (int i = 0; i < str.length; i++) {
            textFieldsList.get(i).click();
            clearText(textFieldsList.get(i));   //�������
            textFieldsList.get(i).sendKeys(str[i]);
        }
    }

    /**
     * �����Ļ�м�
     */
    public void press() {
        press(getScreenWidth() / 2, getScreenHeight() / 2);
    }


    /**
     * ���ĳ���ؼ�
     *
     * @param ae Ҫ����Ŀؼ�
     */
    public void press(AndroidElement ae) {
        try {
            getTouch().tap(ae).perform();
        } catch (Exception e) {
            print("tab���Ԫ�ش���" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * ���ĳ������
     *
     * @param x
     * @param y
     */
    public void press(int x, int y) {
        try {
            driver.tap(1, x, y, 500);
            //getTouch().tap(x, y).perform();
            print("tab���λ��(" + x + "," + y + ")");
        } catch (Exception e) {
            print("tab���Ԫ��λ���쳣" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * ����ĳ���ؼ�
     *
     * @param ae Ҫ����Ŀؼ�
     */
    public void longPress(AndroidElement ae) {
        try {
            getTouch().longPress(ae).release().perform();
        } catch (Exception e) {
            print("�������Ԫ�ش���" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * ����ĳ������
     *
     * @param x
     * @param y
     */
    public void longPress(int x, int y) {
        try {
            getTouch().longPress(x, y).release().perform();
        } catch (Exception e) {
            print("�������Ԫ�ش���" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * �ڿؼ��ϻ���
     *
     * @param element   Ҫ�����Ŀؼ�
     * @param direction �����¼�������Ĭ��1��
     */
    public void swipOnElement(AndroidElement element, String direction) {
        swipOnElement(element, direction, 1000);  //������ʱ���Ϊ2��
    }

    /**
     * ��ĳһ���ؼ��ϻ���
     *
     * @param element   ���Ǹ�Ԫ���ϻ���
     * @param direction ����UP  DOWN LEFT RIGHT
     */
    public void swipOnElement(AndroidElement element, String direction, int duration) {
        //��ȡԪ�ص����xy�������Ͻ�
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        //��ȡԪ�صĿ��
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        switch (direction) {
            case SWIP_UP:
                int startX = x + width / 2;
                //��4/5�ĵײ����м����ϻ���
                driver.swipe(startX, y + height * 4 / 5, startX, y + height / 5, duration);
                break;
            case SWIP_DOWN:
                startX = x + width / 2;
                //��4/5�ĵײ����м����ϻ���
                driver.swipe(startX, y + height / 5, startX, y + height * 4 / 5, duration);
                break;

            case SWIP_LEFT:
                int startY = y + width / 2;
                driver.swipe(x + width * 4 / 5, startY, x + width / 5, startY, duration);
                break;
            case SWIP_RIGHT:
                startY = y + width / 2;
                driver.swipe(x + width / 5, startY, x + width * 4 / 5, startY, duration);
                break;
        }
    }

    /**
     * ��ĳ�������ϻ���
     *
     * @param direction ����UP DOWN LEFT RIGHT
     * @param duration  ����ʱ��
     */
    public void swip(String direction, int duration) {
        switch (direction) {
            case "UP":
                swipeToUp(duration);
                break;
            case "DOWN":
                swipeToDown(duration);
                break;
            case "LEFT":
                swipeToLeft(duration);
                break;
            case "RIGHT":
                swipeToRight(duration);
                break;
        }
    }


    /**
     * ��ָ�������������£�ĳ�����򻬶���ֱ�����Ԫ�س���
     *
     * @param by         �ؼ�
     * @param direction  ����UP DOWN  LEFT RIGHT
     * @param duration   ����һ�γ���ʱ��
     * @param maxSwipNum ��󻬶�����
     */
    public void swipUtilElementAppear(By by, String direction, int duration, int maxSwipNum) {
        int i = maxSwipNum;
        Boolean flag = true;
        while (flag) {
            try {
                if (i <= 0) {
                    flag = false;
                }
                driver.findElement(by);
                flag = false;
            } catch (Exception e) {
                i--;
                swip(direction, duration);
            }
        }
    }

    /**
     * ��ĳ�����򻬶�ֱ�����Ԫ�س���
     *
     * @param by        �ؼ�
     * @param direction ����UP DOWN  LEFT RIGHT
     * @param duration  ����һ�γ���ʱ��
     */
    public void swipUtilElementAppear(By by, String direction, int duration) {
        Boolean flag = true;
        while (flag) {
            try {
                driver.findElement(by);
                flag = false;
            } catch (Exception e) {
                swip(direction, duration);
            }
        }
    }


    /**
     * ��ȡ��Ļ�Ŀ��
     *
     * @return ���ؿ�ߵ�����
     */
    public int[] getScreen() {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        return new int[]{width, height};
    }

    /**
     * ��ȡ��Ļ���
     *
     * @return
     */
    public int getScreenWidth() {
        return driver.manage().window().getSize().getWidth();
    }

    /**
     * ��ȡ��Ļ�߶�
     *
     * @return
     */
    public int getScreenHeight() {
        return driver.manage().window().getSize().getHeight();
    }


    /**
     * ����ɾ���༭���е�����
     *
     * @param element �ı���ܿؼ�
     */
    public void clearText(AndroidElement element) {
        String text = element.getText();
        //�������
        driver.pressKeyCode(KEYCODE_MOVE_END);
        for (int i = 0; i < text.length(); i++) {
            //ѭ������ɾ��
            driver.pressKeyCode(BACKSPACE);
        }

    }



}
