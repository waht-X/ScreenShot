package ScreenCaptureSoft.Utils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class MyMouseListener implements MouseListener {

    public JFrame jFrame;

    public MyMouseListener() {

    }

    public MyMouseListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
