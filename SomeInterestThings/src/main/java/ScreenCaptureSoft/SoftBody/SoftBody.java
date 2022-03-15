package ScreenCaptureSoft.SoftBody;

import ScreenCaptureSoft.JavaSwingListener.MinimizeMouseListener;
import ScreenCaptureSoft.JavaSwingListener.MoveWinListener;
import ScreenCaptureSoft.JavaSwingListener.ScreenShotListener;
import ScreenCaptureSoft.Utils.ButtonInfo;
import ScreenCaptureSoft.Utils.Diplomatic;
import ScreenCaptureSoft.Utils.MyMouseListener;
import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class SoftBody extends JFrame {

    private int x = 1500;
    private int y = 20;
    public static int winInitWidth = 80;
    public static int winInitHeight = 600;
    private Container rootContainer;
    //最后一位功能按钮的保存信息
    private static ButtonInfo lastFuncButtonInfo;

    public SoftBody() {

        setBounds(x, y, winInitWidth, winInitHeight);
        setResizable(false);    //大小不可变
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //去除窗口的标题栏和最小最大化，注意要先使用setUndecorated然后再使用setVisible（JDK8）
        setUndecorated(true);
        setVisible(true);
        init();

    }

    /**
     *
     */
    void init() {

        rootContainer = new Container();
        rootContainer.setBounds(0, 0, winInitWidth, winInitHeight);
        add(rootContainer);
        rootContainer.setLayout(null);

        createMoveBar();
        addMinimize();
        addExit();
        screenShot();
        fullScreenShot();

        rootContainer.revalidate();
    }

    /**
     * 由于去除了标题栏，所以应用无法移动，该方法创建一个区域，在这个区域内可以使用鼠标移动窗口
     */
    void createMoveBar() {

        JButton button = new JButton("move");
        button.setBounds(0, 0, ButtonInfo.buttonWide, ButtonInfo.buttonHeight);
        setLastFunctionButtonInfo(new ButtonInfo(new Point(0, 0),
                new Size(ButtonInfo.buttonWide, ButtonInfo.buttonHeight)));
        rootContainer.add(button);
        MoveWinListener moveWinListener = new MoveWinListener();
        moveWinListener.setView(this);
        button.addMouseListener(moveWinListener);
        button.addMouseMotionListener(moveWinListener);

    }

    /**
     * 创建最小化按钮
     */
    void addMinimize() {

        JButton jButton = new JButton("最小化");
        rootContainer.add(jButton);
        //监听按键最小化命令
        MouseListener mouseListener = new MinimizeMouseListener(this);
        jButton.addMouseListener(mouseListener);
        int y = lastFuncButtonInfo.location.y + lastFuncButtonInfo.buttonSize.height + 10;
        jButton.setBounds(0, y, ButtonInfo.buttonWide, ButtonInfo.buttonHeight);
        setLastFunctionButtonInfo(new ButtonInfo(new Point(0, y), lastFuncButtonInfo.buttonSize));

    }

    /**
     * 创建应用退出按钮
     */
    void addExit() {
        JButton jButton = new JButton("退出");
        rootContainer.add(jButton);
        int y = lastFuncButtonInfo.location.y + lastFuncButtonInfo.buttonSize.height + 10;
        jButton.setBounds(0, y, ButtonInfo.buttonWide, ButtonInfo.buttonHeight);
        setLastFunctionButtonInfo(new ButtonInfo(new Point(0, y), lastFuncButtonInfo.buttonSize));

        jButton.addMouseListener(new MyMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

    }

    /**
     * 创建截图按钮
     */
    void screenShot() {
        ImageIcon icon = new ImageIcon(Diplomatic.ARTIFACT_PHOTO_PATH + File.separator + "screenShot.png");
        FunctionJButton functionJButton = new FunctionJButton(lastFuncButtonInfo);
        JButton jButton = functionJButton.createFunctionJButton("", "指定截图", icon, rootContainer);
        jButton.addMouseListener(new ScreenShotListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenShotProgram.specifyScreenShot();
            }
        });
    }

    /**
     * 创建全屏截图按钮
     */
    void fullScreenShot() {

        ImageIcon icon = new ImageIcon(Diplomatic.ARTIFACT_PHOTO_PATH + File.separator + "fullScreen.png");
        FunctionJButton functionJButton = new FunctionJButton(lastFuncButtonInfo);
        JButton jButton = functionJButton.createFunctionJButton("","全屏截图", icon, rootContainer);
        jButton.addMouseListener(new ScreenShotListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenShotProgram.fullScreenShot();
            }
        });
    }

    /**
     * 获得在窗口内的功能按钮的位于容器的位置<br>
     * ！！！<br>
     * 要求在使用该方法获得按钮信息并**设置新按钮后**需要使用setLastFunctionButtonInfo方法
     * 保存新的按钮的相关大小和位置信息
     */
    public static ButtonInfo getLastFunctionButtonInfo() {
        return lastFuncButtonInfo == null ? new ButtonInfo() : lastFuncButtonInfo;
    }
    /**
     * 保存在窗口内的最后一个功能按钮的位于容器的位置信息
     */
    public static void setLastFunctionButtonInfo(ButtonInfo buttonInfo) {
        lastFuncButtonInfo = buttonInfo;
    }

    public static ButtonInfo getLastFuncButtonInfo() {
        return lastFuncButtonInfo;
    }


}
