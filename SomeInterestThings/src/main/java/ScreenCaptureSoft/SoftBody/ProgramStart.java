package ScreenCaptureSoft.SoftBody;

import javax.swing.*;

public class ProgramStart {
    public static void main(String[] args) {
        //加载截图程序，进行初始化
        ScreenShotProgram.init();
        JFrame jFrame = new SoftBody();
    }

}
