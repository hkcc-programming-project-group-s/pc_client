package gamemonitor.gui.deviceinfo;

import com.sun.istack.internal.NotNull;
import pacmanrobot.MyImages;
import pacmanrobot.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;

public class DeviceInfoJPanel extends JPanel {
    /**
     * Create the panel.
     *
     * @throws IOException
     * @throws MalformedURLException
     */
    public static  Color unclicked_color = new Color(227, 242, 255);
    public static  Color clicked_color = new Color(143, 202, 255);
    public static boolean setup = false;
    public static ImageIcon ROBOT_UNCLASSED;
    public static ImageIcon ROBOT_STUDENT;
    public static ImageIcon ROBOT_DEADLINE;
    public static ImageIcon ROBOT_ASSIGNMENT;
    public final DeviceInfoJPanelHandler handler;
    // TODO </use DeviceInfo class from project>

    public DeviceInfoContainer deviceInfoContainer;
    public DeviceInfoJPanel current = this;
    public byte deviceType;
    public String ip;
    public String name;
    public boolean isClicked = false;
    public boolean isSelected = false;

    public void init() throws MalformedURLException, IOException {
        setup();
        unPair();
        setLayout(new BorderLayout(0, 0));

        JLabel lblIcon = new JLabel(MyImages.getIconByDeviceType(deviceType));
        add(lblIcon, BorderLayout.WEST);

        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        panel.setBackground(new Color(0, 0, 0, 0));

        JLabel lblIp = new JLabel(ip);
        panel.add(lblIp, BorderLayout.NORTH);

        JLabel lblName = new JLabel(name);
        panel.add(lblName, BorderLayout.SOUTH);

        System.out.println("new DeviceInfoJPanel: " + toString());
    }

    public DeviceInfoJPanel(byte deviceType, String ip, String name, DeviceInfoJPanelHandler handler) throws MalformedURLException, IOException {
        this.deviceType = deviceType;
        this.ip = ip;
        this.name = name;
        this.handler = handler;
        init();
    }

    public static void setup() {
        if (setup)
            return;
        try {
            ROBOT_UNCLASSED = Utils.getImageIcon(MyImages.ROBOT_UNCLASSED);
            ROBOT_STUDENT = Utils.getImageIcon(MyImages.ROBOT_STUDENT);
            ROBOT_DEADLINE = Utils.getImageIcon(MyImages.ROBOT_DEADLINE);
            ROBOT_ASSIGNMENT = Utils.getImageIcon(MyImages.ROBOT_ASSIGNMENT);
        } catch (IOException e) {
            // Cannot load image
            e.printStackTrace();
        }
        setup = true;
    }

    public void click() {
        isClicked = true;
        setBackground(clicked_color);
        System.out.println("clicked DeviceInfoJPanel: " + this);

    }

    public void unclick() {
        //System.out.println("un-click");
        isClicked = false;
        setBackground(unclicked_color);
    }

    public String toString() {
        return deviceType + "," + name + "," + ip;
    }

    public void unPair() {
        isSelected = false;
        unclick();
        setActionListener();
    }

    private void setActionListener() {
        while (getMouseListeners().length > 0)
            removeMouseListener(getMouseListeners()[0]);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                click();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                click();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                unclick();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });
    }

    @NotNull
    public void transfer(DeviceInfoContainer newDeviceInfoContainer) {
        if (deviceInfoJPanelHandler != null)
            deviceInfoJPanelHandler.remove(this);
        newDeviceInfoContainer.add(this);
    }


    public DeviceInfoJPanelHandler deviceInfoJPanelHandler = null;
}
