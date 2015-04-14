package gamemonitor.gui.content;

import java.io.IOException;

/**
 * Created by 13058456a on 4/13/2015.
 */
public interface GameMonitorContent {
    boolean onLeave();

    void onEnter() throws IOException;
}
