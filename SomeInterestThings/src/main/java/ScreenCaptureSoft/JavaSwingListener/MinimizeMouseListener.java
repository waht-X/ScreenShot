package ScreenCaptureSoft.JavaSwingListener;

import ScreenCaptureSoft.Utils.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 点击使窗口最小化
 */
public class MinimizeMouseListener extends MyMouseListener {
    public MinimizeMouseListener() {
        super();
    }

    public MinimizeMouseListener(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jFrame.setVisible(false);
        miniTray();
    }

    /**
     * 使应用最小化到托盘
     */
    void miniTray() {
        SystemTray systemTray = SystemTray.getSystemTray();
        ImageIcon trayImg = new ImageIcon("src/main/java/ScreenCaptureSoft/Photo/剪切.png");
        TrayIcon trayIcon = new TrayIcon(trayImg.getImage(), "截屏", new PopupMenu());
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {   //单机 == 1，双击 == 2
                    systemTray.remove(trayIcon);
                    jFrame.setVisible(true);
                    jFrame.setExtendedState(jFrame.NORMAL);
                    jFrame.toFront();
                }
            }
        });
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
