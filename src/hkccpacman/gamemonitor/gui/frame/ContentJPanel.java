package hkccpacman.gamemonitor.gui.frame;

import hkccpacman.gamemonitor.gui.core.PairControllerRobotJPanel;
import hkccpacman.gamemonitor.gui.core.PositionSetting;
import hkccpacman.gamemonitor.gui.core.SetDeviceInfo;
import hkccpacman.gamemonitor.gui.core.SetDeviceName;
import myutils.gui.cardlayout.AbstractCardJPanel;

import javax.swing.*;
import java.util.Vector;

public class ContentJPanel extends AbstractCardJPanel {

    private final GameMonitorJFrame master;

    Vector<JPanel> contents;

    public ContentJPanel(GameMonitorJFrame master) {
        super();
        this.master = master;
    }

    @Override
    protected void myInit() {
        contents = new Vector<>();
        contents.add(new SetDeviceName());
        contents.add(new SetDeviceInfo());
        contents.add(new PairControllerRobotJPanel());
        contents.add(new PositionSetting());

        for (int i = 0; i < contents.size(); i++)
            addToCards(contents.get(i), i + "");
    }

    public boolean canNext() {
        //contents[currentPage].
        return hasNext();
    }

    public void next() {
        if (canNext())
            currentPage++;
        switchToCard(currentPage + "");
    }

    public void prev() {
        if (canPrev())
            currentPage--;
        switchToCard(currentPage + "");
    }

    public boolean canPrev() {
        //check content
        return hasPrev();
    }

    public int currentPage = 0;

    public boolean hasNext() {
        return currentPage < contents.size();
    }

    public boolean hasPrev() {
        return currentPage > 0;
    }
}
