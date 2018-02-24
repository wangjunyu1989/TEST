package qihoo.appium;

import org.apache.http.util.TextUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static qihoo.appium.InitAppium.appPackage;


public class PageAppium {

    AndroidDriver driver;

    private static int WAIT_TIME = 3;    //Ĭ�ϵĵȴ��ؼ�ʱ��



    public PageAppium(AndroidDriver androidDriver) {
        this.driver = androidDriver;
        waitAuto(WAIT_TIME);
    }

    public boolean isIdElementExist(String id) {
        return isIdElementExist(id, 0);
    }

    public boolean isIdElementExist(String id,int timeOut) {
        return isIdElementExist(id,timeOut ,false);
    }

    /**
     * ����id�жϵ�ǰ�����Ƿ���ڲ���ʾ����ؼ�
     *
     * @param id     Ҫ���ҵ�id
     * @param isShow �Ƿ��жϿؼ���ʾ
     * @return ���ض�Ӧ�Ŀؼ�
     */
    public boolean isIdElementExist(String id,int timeOut, boolean isShow) {
        return isElementExist(By.id(appPackage + ":id/" +id),timeOut,isShow);
    }

    /**
     * ѡ��ǰ�������������ֵĿؼ�
     *
     * @param name
     * @return
     */
    public boolean isNameElementExist(String name) {
        return isNameElementExist(name, 0);
    }

    public boolean isNameElementExist(String name, int timeOut) {
        return isNameElementExist(name, timeOut,false);
    }

    public boolean isNameElementExist(String name, int timeOut, boolean isShow) {
        return isElementExist(By.name(name),timeOut, isShow);
    }



    /**
     * �жϵ�ǰ������û������ַ�������
     *
     * @param text Ҫ�жϵ��ַ���
     * @return ���ڷ�����
     */
    public boolean isTextExist(String text) {
        String str = driver.getPageSource();
        print(str);
        return str.contains(text);
    }


    /**
     * �жϵ�ǰ������û�����Xpath�ؼ�����
     *
     * @param text Ҫ�жϵ��ַ���
     * @return ���ڷ�����
     */
    public boolean isXpathExist(String text) {
        return isXpathExist(text,0);
    }

    public boolean isXpathExist(String text,int timeOut) {
        return isXpathExist(text,timeOut, false);
    }


    public boolean isXpathExist(String text,int timeOut,boolean isShow) {
        ////android.widget.TextView[@text='"+text+"']
        return isElementExist(By.xpath(text), timeOut,isShow);

    }



        /**
         * �жϿؼ�ʱ�����
         *
         * @param by      By
         * @param timeout �ȴ����¼�
         * @return
         */
    public boolean isElementExist(By by, int timeout,boolean isShow) {
        try {
            AndroidElement element = waitAuto(by, timeout);
            if(element == null){
                return false;
            }else{
                if(isShow){
                    return element.isDisplayed();
                }
            }

            return true;
        } catch (Exception e) {
            return false;
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
     * ����id��ȡ��ǰ�����һ���ؼ�
     *
     * @param id Ҫ���ҵ�id
     * @return ���ض�Ӧ�Ŀؼ�
     */
    public AndroidElement findById(String id,String desc) {
        return findElementBy(By.id(id),desc);
    }

    public AndroidElement findById(String id) {
        return findElementBy(By.id(id),"");
    }


    public AndroidElement findByFullId(String id) {
        try {
            if (driver != null) {
                return (AndroidElement) driver.findElement(By.id(id));
            } else {
                print("driverΪ��");
            }
        } catch (NoSuchElementException e) {
            print("�Ҳ����ؼ�:" +" �쳣��Ϣ:"+ e.getMessage());

        }

        return null;
    }


    /**
     * ѡ��ǰ�������������ֵĿؼ�
     *
     * @param name ����
     * @return �ҵ��Ŀؼ�
     */
    public AndroidElement findByName(String name,String desc) {
        return findElementBy(By.name(name),desc);
    }
    public AndroidElement findByName(String name) {
        return findByName(name,"");
    }

    /**
     * ����id��ȡ��ǰ�����һ���ؼ�
     *
     * @param name Ҫ���ҵĿؼ�������
     * @return ���ض�Ӧ�Ŀؼ�
     */
    public AndroidElement findByClassName(String name,String desc) {
        return findElementBy(By.className(name),desc);
    }
    public AndroidElement findByClassName(String name) {
        return findByClassName(name,"");
    }


    public AndroidElement findByXpath(String str) {
        return findByXpath(str,"");
    }
    public AndroidElement findByXpath(String str,String desc) {
        return findElementBy(By.xpath(str),desc);
    }


    public AndroidElement findElementBy(By by){
        return findElementBy(by,"");
    }

        /**
         * ��ȡ�ؼ�
         * @param by by
         * @param str ������ʾ��Ϣ
         * @return
         */
    public AndroidElement findElementBy(By by,String str){
        try {
            if (driver != null) {
                return (AndroidElement) driver.findElement(by);
            } else {
                print("driverΪ��");
            }
        } catch (NoSuchElementException e) {
            print("�Ҳ����ؼ�:" +str+" �쳣��Ϣ:"+ e.getMessage());

        }

        return null;
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
     * �߳�������������λ��
     *
     * @param s Ҫ���ߵ�����
     */
    public void sleep(long s) throws InterruptedException {
        Thread.sleep(s);
    }


    /**
     * ��ʾ�ȴ����ȴ�Id��Ӧ�Ŀؼ�����time�룬һ�������Ϸ��أ�time�벻����Ҳ����
     */
    public AndroidElement waitAuto(final By by, int time) {
        try {
            return new AndroidDriverWait(driver, time)
                    .until(new ExpectedCondition<AndroidElement>() {
                        @Override
                        public AndroidElement apply(AndroidDriver androidDriver) {
                            return (AndroidElement) androidDriver.findElement(by);
                        }
                    });
        } catch (TimeoutException e) {
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
     * ����ClassName��ȡ����ؼ�
     *
     * @param className �ؼ��������֣����� android.widget.EditText
     * @param num       ���ص�����
     * @return
     */
    public List<AndroidElement> getManyElementByClassName(String className, int num) {
        List<AndroidElement> textFieldsList = driver.findElementsByClassName(className);
        List<AndroidElement> list = new ArrayList<>();
        try{
            for(int i=0; i<num; i++){
                list.add(textFieldsList.get(i));
            }
            return list;
        }catch (Exception e){
            print("��ȡ����ؼ��쳣"+e.getMessage());
        }
        return null;

    }

    /**
     * ����Id��ȡ����ؼ�
     *
     * @param id �ؼ��������֣����� android.widget.EditText
     * @param num       ���ص�����
     * @return
     */
    public List<AndroidElement> getManyElementById(String id, int num) {
        if(driver != null){
            List<AndroidElement> textFieldsList = driver.findElementsById(id);
            List<AndroidElement> list = new ArrayList<>();
            try{
                for(int i=0; i<num; i++){
                    list.add(textFieldsList.get(i));
                }
                return list;
            }catch (Exception e){
                print("��ȡ����ؼ��쳣"+e.getMessage());
            }
        }else{
            print("��ȡ����ؼ�"+id+"ʱ��driverΪ��");
        }

        return null;

    }

    /**
     * ��ȡͬid��list�Ŀؼ�
     * @param id id
     * @param num ȡ��һ���ؼ�
     * @return
     */
    public AndroidElement getListOneElementById(String id,int num){
        if(driver != null){
            try{
                return (AndroidElement) driver.findElementsById(appPackage+":id/"+id).get(num);
            }catch (Exception e){
                print("getListOneElementById�Ҳ�����"+num+"���ؼ�"+id);
                return null;
            }
        }else{
            print("getListOneElementById:"+id+" ʱ��driverΪ��");
            return null;
        }
    }

}
