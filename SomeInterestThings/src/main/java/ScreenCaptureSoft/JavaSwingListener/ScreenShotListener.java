package ScreenCaptureSoft.JavaSwingListener;

import ScreenCaptureSoft.Utils.MyMouseListener;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ScreenShotListener extends MyMouseListener {

    public ScreenShotListener() {
        super();
    }

    public ScreenShotListener(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
