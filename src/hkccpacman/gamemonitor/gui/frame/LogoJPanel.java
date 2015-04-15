package hkccpacman.gamemonitor.gui.frame;


import hkccpacman.utils.Utils;

import javax.swing.*;
import java.io.IOException;

public class LogoJPanel extends JPanel {

    /**
     * Create the panel.
     */
    public static final String LOGO = "https://dl.dropboxusercontent.com/u/13757442/htm/robocode-tank.png";

    public LogoJPanel() {
        JLabel lblLogoimage = null;
        try {
            lblLogoimage = new JLabel(Utils.getImageIcon(LOGO));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(lblLogoimage);
    }

}