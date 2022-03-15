package ScreenCaptureSoft.SoftBody;

import ScreenCaptureSoft.Utils.ButtonInfo;
import ScreenCaptureSoft.Utils.Diplomatic;

import javax.swing.*;
import java.awt.*;

import static ScreenCaptureSoft.SoftBody.SoftBody.setLastFunctionButtonInfo;

/**
 * 创建一个功能按键
 */
public class FunctionJButton {

    private final ButtonInfo lastFuncButtonInfo;

    public FunctionJButton(ButtonInfo buttonInfo) {

        lastFuncButtonInfo = buttonInfo;
    }


    /**
     * 以指定参数创建一个JButton，该JButton将添加到Container容器中
     * @param title JButton的文本
     * @param tipText  鼠标悬停在对应的JButton上的时候显示的文本
     * @param icon  JButton上显示的图片，图片的尺寸以Diplomatic内的getSpecifyTco方法决定
     * @param container 需要将JButton添加到Container中的容器，由于先创建JButton并先添加相应参数后再
     *                  再添加到对应的Container中时，JButton可能会出现不显示的情况，因此，这里需要传入一个
     *                  Container，在创建完一个JButton后，立即添加到这个Container中
     * @return
     */
    public JButton createFunctionJButton(String title, String tipText, ImageIcon icon, Container container) {
        JButton jButton = new JButton(title);
        container.add(jButton);
        jButton.setToolTipText(tipText);
        Diplomatic.getSpecifyIco(icon);
        jButton.setIcon(icon);
        int y = lastFuncButtonInfo.location.y + lastFuncButtonInfo.buttonSize.height + 10;
        jButton.setBounds(0, y, ButtonInfo.buttonWide, ButtonInfo.buttonHeight);
        setLastFunctionButtonInfo(new ButtonInfo(new Point(0, y), lastFuncButtonInfo.buttonSize));
        return jButton;
    }

    public JButton createFunction(String title, Container container) {
        JButton jButton = new JButton(title);
        container.add(jButton);
        int y = lastFuncButtonInfo.location.y + lastFuncButtonInfo.buttonSize.height + 10;
        jButton.setBounds(0, y, ButtonInfo.buttonWide, ButtonInfo.buttonHeight);
        setLastFunctionButtonInfo(new ButtonInfo(new Point(0, y), lastFuncButtonInfo.buttonSize));
        return jButton;
    }

}
