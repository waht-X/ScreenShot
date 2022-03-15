package ScreenCaptureSoft.JavaSwingListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 该类用于实现使用鼠标移动应该窗口
 */
public class MoveWinListener implements MouseListener, MouseMotionListener {

    private JFrame jFrame;
    private Point startPoint;
    private Point endPoint;
    //判断是否开始拖动窗口
    public boolean moved = false;

    public void setView(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        endPoint = e.getPoint();
//        moveTheWin();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * 实现拖动移动应用窗口
     */
    private void moveTheWin() {
        //计算x，y的偏移量
        int offsetX = startPoint.x - endPoint.x;
        int offsetY = startPoint.y - endPoint.y;
        if (jFrame != null ) {
            jFrame.setLocation(jFrame.getX() - offsetX, jFrame.getY() - offsetY);
        }
    }

    /**
     * 实现拖动移动应用窗口
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        endPoint = e.getPoint();
        moveTheWin();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
