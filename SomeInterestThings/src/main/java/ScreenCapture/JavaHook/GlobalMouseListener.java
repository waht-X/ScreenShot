package ScreenCapture.JavaHook;

import ScreenCapture.ScreenCapture;
import ScreenCapture.ScreenShot;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalMouseListener implements NativeMouseListener {

    private Point startPoint;
    private Point endPoint;

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        //记录此时的坐标
        if (GlobalKeyboardListener.mouseStartMoveRecord) {
            System.out.println(nativeMouseEvent.paramString());
            startPoint = nativeMouseEvent.getPoint();
            System.out.println("i am recorded start point");
        }

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        //记录此时的坐标同时将mouseStartMoveRecord置为false
        if (GlobalKeyboardListener.mouseStartMoveRecord) {
            endPoint = nativeMouseEvent.getPoint();
            System.out.println(nativeMouseEvent.paramString());
            GlobalKeyboardListener.mouseStartMoveRecord = false;
            //利用坐标进行截图
            System.out.println("->already to save photo");
            ScreenShot screenShot = new ScreenShot();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = simpleDateFormat.format(new Date());
            screenShot.saveImage(ScreenCapture.saveFilePath, fileName, startPoint, endPoint);
        }
    }
}
