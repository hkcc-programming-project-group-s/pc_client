package gamemonitor.gui.deviceinfo;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by beenotung on 4/12/15.
 */
public class ColorReversedDeviceInfoJPanel extends DeviceInfoJPanel {
    public ColorReversedDeviceInfoJPanel(byte deviceType, String ip, String name, DeviceInfoJPanelHandler handler) throws MalformedURLException, IOException {
        super(deviceType, ip, name, handler);
    }

    public static Color unclicked_color = DeviceInfoJPanel.clicked_color;
    public static Color clicked_color = DeviceInfoJPanel.unclicked_color;
}
