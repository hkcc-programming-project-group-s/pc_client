package pacmanrobot;

import gamemonitor.gui.content.deviceinfo.DeviceInfo;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by 13058536A on 4/8/2015.
 */
public class MyImages {
    public static final String ROBOT_UNCLASSED = "https://dl.dropboxusercontent.com/u/13757442/htm/robocode-tank.png";
    //TODO apply new triangle
    public static final String CONTROLLER = "https://dl.dropboxusercontent.com/u/13757442/htm/robocode-tank.png";
    public static final String ROBOT_STUDENT = "https://dl.dropboxusercontent.com/u/13757442/htm/robocode-tank.png";
    public static final String ROBOT_DEADLINE = "https://dl.dropboxusercontent.com/u/13757442/htm/robocode-tank.png";
    public static final String ROBOT_ASSIGNMENT = "https://dl.dropboxusercontent.com/u/13757442/htm/robocode-tank.png";
    public static ImageIcon ICON_CONTROLLER = null;
    public static ImageIcon ICON_ROBOT_UNCLASSED = null;

    public static ImageIcon getIconByDeviceType(byte deviceType) throws MalformedURLException, IOException {
        //TODO add other types
        switch (deviceType) {
            case DeviceInfo.ROBOT_UNCLASSED:
                if (ICON_ROBOT_UNCLASSED == null)
                    ICON_ROBOT_UNCLASSED = Utils.getImageIcon(ROBOT_UNCLASSED);
                return ICON_ROBOT_UNCLASSED;
            case DeviceInfo.CONTROLLER:
                if (ICON_CONTROLLER == null)
                    ICON_CONTROLLER = Utils.getImageIcon(CONTROLLER);
                return ICON_CONTROLLER;
            default:
                return null;
        }
    }
}
