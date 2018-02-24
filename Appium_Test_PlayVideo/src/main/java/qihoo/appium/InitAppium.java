package qihoo.appium;


import org.apache.http.util.TextUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeSuite;


public class InitAppium {
    //千万不要用小米5x，一直都无法点击控件，这个是华为手机的设备ＩＤ,魅族手机设备ID 80CBCNP2248W
    //public static String deviceName = "PBV5T16530003406";
//    public static String deviceName = "80CBCNP2248W";
//    public static String platformVersion = "6.0";
    //360手机
    public static String deviceName = "MNZDM79L8HLZ45CE";
    public static String platformVersion = "6.0";


    //三星手机
//    public static String deviceName = "d63cc681";
//    public static String platformVersion = "7.0";
    //小米手机
//    public static String deviceName = "83fe9676";
//    public static String platformVersion = "6.0.1";

//    public static String appPath = System.getProperty("user.dir") + "/src/main/java/apps/jokes.apk";
//    public static String appPackage = "chenyu.jokes";
//    public static String appActivity = ".feature.main.MainActivity";
    //demo配置
//    public static String appPath = System.getProperty("user.dir") + "/src/main/java/apps/demo.apk";
//    public static String appPackage = "com.qihoo.livecloud.demo";
//    public static String appActivity = "com.qihoo.videocloud.NavigationActivity";
    //plugin_demo配置
    public static String appPath = System.getProperty("user.dir") + "/Appium_Test_PlayVideo/src/main/java/apps/plugin_demo-debug.apk";
    public static String appPackage = "com.qihoo.livecloud.plugin";
    public static String appActivity = "com.qihoo.videocloud.plugin_demo.main.MainActivity";
    public static String noReset = "True";

    public static String noSign = "True";

    public static String unicodeKeyboard = "True";

    public static String resetKeyboard = "True";

    public   AndroidDriver<AndroidElement> driver = null;


    public InitAppium() {
        this(new Builder());
    }

    public InitAppium(Builder builder) {

        appActivity = builder.appActivity;
        appPackage = builder.appPackage;
        appPath = builder.appPath;
        deviceName = builder.deviceName;
        noReset = builder.noReset;
        noSign = builder.noSign;
        unicodeKeyboard = builder.unicodeKeyboard;
        resetKeyboard = builder.resetKeyboard;
    }


    @Before
    public     void beforeSuite() throws MalformedURLException {


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("app", new File(appPath).getAbsolutePath());
        capabilities.setCapability("appPackage", appPackage);

        capabilities.setCapability("unicodeKeyboard", unicodeKeyboard);
        capabilities.setCapability("resetKeyboard", resetKeyboard);

        capabilities.setCapability("noReset", noReset);

        capabilities.setCapability("noSign", noSign);
        if(!TextUtils.isEmpty(appActivity)){
            capabilities.setCapability("appActivity", appActivity);
        }

        //����Driver
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }


    @After
    public void afterTest() {
        driver.quit();
    }


    public <T> void print(T str) {
        if (!TextUtils.isEmpty(String.valueOf(str))) {
            System.out.println(str);
        } else {
            System.out.println("����˿��ַ�");
        }
    }

}
