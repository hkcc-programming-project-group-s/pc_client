package gamemonitor.gui;

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
    public static final Color unclicked_color = new Color(227, 242, 255);
    public static final Color clicked_color = new Color(143, 202, 255);
    public static boolean setup = false;
    public static ImageIcon ROBOT_UNCLASSED;
    public static ImageIcon ROBOT_STUDENT;
    public static ImageIcon ROBOT_DEADLINE;
    public static ImageIcon ROBOT_ASSIGNMENT;
    // TODO </use DeviceInfo class from project>
    public final ControllerRobotPairFrame master_pair;
    public final SetDeviceInfo master_set;
    public final PositionSetting master_position;
    public DeviceInfoJPanel current = this;
    public byte deviceType;
    public String ip;
    public String name;
    public boolean isClicked = false;

    public DeviceInfoJPanel(ControllerRobotPairFrame controllerRobotPairFrame,
                            byte deviceType, String ip, String name)
            throws MalformedURLException, IOException {
        setup();
        this.master_set = null;
        this.master_pair = controllerRobotPairFrame;
        this.deviceType = deviceType;
        this.ip = ip;
        this.name = name;
        this.master_position=null;
        // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
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

    public DeviceInfoJPanel(SetDeviceInfo setDeviceInfo,byte deviceType, String ip, String name)throws MalformedURLException, IOException{
        this.deviceType = deviceType;
        this.ip = ip;
        this.name = name;
        this.master_set =setDeviceInfo;
        this.master_pair=null;
        this.master_position=null;

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

    public DeviceInfoJPanel(PositionSetting positionsetting,byte deviceType, String ip, String name)throws MalformedURLException, IOException{
        this.deviceType = deviceType;
        this.ip = ip;
        this.name = name;
        this.master_set =null;
        this.master_pair=null;
        this.master_position=positionsetting;

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
        System.out.println("click");
        if(master_pair!=null)
         master_pair.onUnpairedDeviceInfoPanelClicked(this);
        else if(master_set!=null)
            master_set.onControllerJPPanelsclick(this);
        else if(master_position!=null)
            master_position.onRobotSettingJPPanelsclick(this);
    }

    public void unclick() {
        //System.out.println("un-click");
        isClicked = false;
        setBackground(unclicked_color);
    }

    public boolean isSelected = false;

    public String toString() {
        return deviceType + "," + name + "," + ip;
    }

    public void unPair() {
        isSelected=false;
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
}
