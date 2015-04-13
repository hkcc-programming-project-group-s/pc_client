package gamemonitor.gui.content.deviceinfo;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by beenotung on 4/12/15.
 */
public class DeviceInfoContainer extends JPanel {
    Vector<DeviceInfoJPanel> deviceInfoJPanels;

    public DeviceInfoContainer(Vector<DeviceInfoJPanel> deviceInfoJPanels) {
        this.deviceInfoJPanels = deviceInfoJPanels;
        setBackground(new Color(198, 228, 255));
    }

    public DeviceInfoContainer() {
        this(new Vector<>());
    }

    @NotNull
    public Component add(DeviceInfoJPanel comp) {
        if (comp.deviceInfoContainer != null)
            comp.deviceInfoContainer.remove(comp);
        deviceInfoJPanels.add(comp);
        comp.deviceInfoContainer = this;
        Component result = super.add(comp);
        revalidate();
        updateUI();
        return result;
    }

    @NotNull
    public void remove(DeviceInfoJPanel comp) {
        deviceInfoJPanels.remove(comp);
        comp.deviceInfoContainer = null;
        super.remove(comp);
        revalidate();
        updateUI();
    }
}
