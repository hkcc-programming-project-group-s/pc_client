package gamemonitor.gui.content;

import gamemonitor.gui.content.deviceinfo.DeviceInfoJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DevicePairJPanel extends JPanel {

    public static final Color clicked_color = new Color(227, 242, 255);
    public static final Color unclicked_color = new Color(143, 202, 255);
    public final DeviceInfoJPanel controllerJPanel;
    public final DeviceInfoJPanel robotJPanel;
    public boolean isClicked = false;
    public boolean isSelected = false;

    /**
     * Create the panel.
     */
    public DevicePairJPanel(DeviceInfoJPanel controllerJPanel, DeviceInfoJPanel robotJPanel) {
        this.controllerJPanel = controllerJPanel;
        this.robotJPanel = robotJPanel;
        controllerJPanel.setBackground(new Color(0, 0, 0, 0));
        while (controllerJPanel.getMouseListeners().length > 0)
            controllerJPanel.removeMouseListener(controllerJPanel.getMouseListeners()[0]);
        robotJPanel.setBackground(new Color(0, 0, 0, 0));
        while (robotJPanel.getMouseListeners().length > 0)
            robotJPanel.removeMouseListener(robotJPanel.getMouseListeners()[0]);
        setLayout(new BorderLayout(0, 0));

        add(controllerJPanel, BorderLayout.WEST);
        add(robotJPanel, BorderLayout.EAST);

//        Box verticalBox = Box.createVerticalBox();
//        add(verticalBox, BorderLayout.CENTER);
//        verticalBox.setBackground(new Color(71, 101, 128));

        LineBox verticalBox = new LineBox();
        add(verticalBox, BorderLayout.CENTER);

        Component rigidArea = Box.createRigidArea(new Dimension(40, 40));
        verticalBox.add(rigidArea);

        unclick();
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

    public PairControllerRobotFrame getMaster() {
        if (controllerJPanel.master_pair.equals(robotJPanel.master_pair))
            throw new IllegalComponentStateException();
        return controllerJPanel.master_pair;
    }

    public void click() {
        isClicked = true;
        setBackground(clicked_color);
        PairControllerRobotFrame.onPairedDeviceInfoPanelClicked(this);
        //ControllerRobotPairFrame.onUnpairedDeviceInfoPanelClicked(this);
    }

    public void unclick() {
        isClicked = false;
        setBackground(unclicked_color);
    }

    static class LineBox extends Box {
        public LineBox(int i) {
            super(i);
            setBackground(new Color(0, 0, 0, 0));
        }

        public LineBox() {
            this(1);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            //super.paintComponent(graphics);
            graphics.setColor(new Color(71, 101, 128));
            graphics.fillRoundRect(0, getHeight() * 2 / 5, getWidth(), getHeight() / 5, 10, 10);
        }
    }
}
