package hkccpacman.gamemonitor.gui.core.deviceinfo;

/**
 * Created by 13058456a on 4/10/2015.
 */
public class DeviceInfo {
    // TODO <use DeviceInfo class from project>
    public static final byte CONTROLLER = 0x01;
    public static final byte ROBOT_UNCLASSED = 0x02;
    public static final byte ROBOT_STUDENT = 0x03;
    public static final byte ROBOT_DEADLINE = 0x04;
    public static final byte ROBOT_ASSIGNMENT = 0x05;
    public byte deviceType;
    public String ip;
    public String name;

    public DeviceInfo(byte deviceType, String ip, String name) {
        this.deviceType = deviceType;
        this.ip = ip;
        this.name = name;
    }

    public static boolean isRobot(byte deviceType) {
        switch (deviceType) {
            case ROBOT_ASSIGNMENT:
            case ROBOT_STUDENT:
            case ROBOT_DEADLINE:
            case ROBOT_UNCLASSED:
                return true;
        }
        return false;
    }
}
