package ScreenCaptureSoft.SoftBody;

import ScreenCaptureSoft.FunButton.TranslateButton;

public class ProgramStart {
    public void start (){
        //加载截图程序，进行初始化
        ScreenShotProgram.init();
        SoftBody jFrame = new SoftBody();
        //添加翻译按钮
        new TranslateButton(jFrame.getRootContainer(), SoftBody.getLastFunctionButtonInfo());
    }

}
