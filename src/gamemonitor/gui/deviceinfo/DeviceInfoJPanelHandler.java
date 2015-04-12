package gamemonitor.gui.deviceinfo;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.Vector;

/**
 * Created by beenotung on 4/12/15.
 */
public interface DeviceInfoJPanelHandler {
    @NotNull
    void onDeviceInfoJPanelClicked(DeviceInfoJPanel deviceInfoJPanel);

    Vector<DeviceInfoContainer> getDeviceInfoContainers();

    Component add(DeviceInfoJPanel deviceInfoJPanel);

    void remove(DeviceInfoJPanel deviceInfoJPanel);


}
