package base;


import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;

public class VisualGridConfig {
    public static Configuration getGrid(String appName, String testName){
        //https://applitools.com/docs/topics/overview/using-the-visual-grid.html
        Configuration vgConfig = new Configuration();
        vgConfig.setAppName(appName);
        vgConfig.setTestName(testName);

        //Browsers
        vgConfig.addBrowser(800, 600, BrowserType.FIREFOX);
        vgConfig.addBrowser(800, 600, BrowserType.IE_11);
        vgConfig.addBrowser(800, 600, BrowserType.EDGE);
        vgConfig.addBrowser(800, 600, BrowserType.CHROME);

        //Devices
        vgConfig.addDeviceEmulation(DeviceName.iPhone_X);
        vgConfig.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.LANDSCAPE);
        vgConfig.addDeviceEmulation(DeviceName.Galaxy_Note_2);
        vgConfig.addDeviceEmulation(DeviceName.Galaxy_Note_2, ScreenOrientation.LANDSCAPE);
        vgConfig.addDeviceEmulation(DeviceName.iPad);

        return vgConfig;
    }
}
