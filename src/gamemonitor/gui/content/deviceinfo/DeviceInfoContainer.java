package gamemonitor.gui.content.deviceinfo;

import com.sun.istack.internal.NotNull;
import gamemonitor.gui.frame.GameMonitorJFrame;
import utils.WrapLayout;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.util.Vector;

/**
 * Created by beenotung on 4/12/15.
 */
public class DeviceInfoContainer extends JScrollPane {
    public Vector<DeviceInfoJPanel> deviceInfoJPanels;

    public DeviceInfoContainer(Vector<DeviceInfoJPanel> deviceInfoJPanels) {
        //setLayout(new ScrollPaneLayout());


        getViewport().setSize(1, 321654);
        this.deviceInfoJPanels = deviceInfoJPanels;
        //WrapLayout wrapLayout = new WrapLayout();
        //setLayout(wrapLayout);
        setBackground(new Color(0, 0, 0, 0));
        setFocusable(false);

        contentPanel.setBackground(new Color(198, 228, 255));
        contentPanel.setLayout(new WrapLayout());

        getViewport().add(contentPanel);
    }

    public JPanel contentPanel = new JPanel();

    public DeviceInfoContainer() {
        this(new Vector<>());
    }

    @NotNull
    public Component add(DeviceInfoJPanel comp) {
        if (comp.deviceInfoContainer != null)
            comp.deviceInfoContainer.remove(comp);
        deviceInfoJPanels.add(comp);
        comp.deviceInfoContainer = this;
        Dimension preferedSize=comp.getPreferredSize();
        Component result = contentPanel.add(comp);
        comp.setPreferredSize(preferedSize);
        contentPanel.revalidate();
        contentPanel.updateUI();
        revalidate();
        updateUI();
        return result;
    }

    @NotNull
    public void remove(DeviceInfoJPanel comp) {
        deviceInfoJPanels.remove(comp);
        comp.deviceInfoContainer = null;
        contentPanel.remove(comp);
        contentPanel.revalidate();
        contentPanel.updateUI();
        revalidate();
        updateUI();
    }
}
