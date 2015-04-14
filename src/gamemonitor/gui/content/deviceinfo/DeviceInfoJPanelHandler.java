package gamemonitor.gui.content.deviceinfo;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by beenotung on 4/12/15.
 */
public interface DeviceInfoJPanelHandler {
    @NotNull
    void onDeviceInfoJPanelClicked(DeviceInfoJPanel deviceInfoJPanel);

    Vector<DeviceInfoContainer> getDeviceInfoContainers();
}
