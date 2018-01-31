package com.var.software.dxf.threads;

import com.var.software.dxf.ui.MainPanel;

import java.awt.*;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Refresh java draw view thread.
 */
public class RefreshThread extends Thread {

    private MainPanel panel;

    /**
     * Init object(s).
     *
     * @param panel draw view object.
     */
    public RefreshThread(MainPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            if (panel != null) {
                Graphics2D g2d = (Graphics2D) panel.getGraphics();
                if (g2d != null) {
                    panel.update(g2d);
                    g2d.dispose();
                }
            }
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
