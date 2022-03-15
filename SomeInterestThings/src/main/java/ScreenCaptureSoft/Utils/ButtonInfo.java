package ScreenCaptureSoft.Utils;

import ScreenCaptureSoft.SoftBody.SoftBody;
import com.sun.glass.ui.Size;

import java.awt.*;

public class ButtonInfo {
    //关于加入到SoftBody内功能按键的一些普通标准
    public static int buttonWide = SoftBody.winInitWidth;
    public static int buttonHeight = 30;

    //按钮的位置
    public Point location;
    //按钮大小
    public Size buttonSize;

    public ButtonInfo() {
        location = new Point();
        buttonSize = new Size();
    }

    public ButtonInfo(Point point, Size size) {
        location = point;
        buttonSize = size;
    }

    /**
     * 获得该按钮的位置信息
     */
    public Point getLocation() {
        return location;
    }

    /**
     * 设置按钮的位置信息
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * 获得该按钮的大小信息
     */
    public Size getButtonSize() {
        return buttonSize;
    }

    /**
     * 设置该按钮的大小信息
     */
    public void setButtonSize(Size buttonSize) {
        this.buttonSize = buttonSize;
    }
}
