package ScreenCaptureSoft.JavaHook;

import ScreenCaptureSoft.SoftBody.ScreenShotProgram;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import java.awt.*;

public class GlobalMouseListener implements NativeMouseListener {

    private Point startPoint;
    private Point endPoint;

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        if (ScreenShotProgram.START_SPECIFY_SCREEN_SHOT) {
            startPoint = nativeMouseEvent.getPoint();
        }

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        if (ScreenShotProgram.START_SPECIFY_SCREEN_SHOT) {
            endPoint = nativeMouseEvent.getPoint();
            int w = endPoint.x - startPoint.x;
            int h = endPoint.y - startPoint.y;
            ScreenShotProgram.getImage(startPoint.x, startPoint.y, w, h);
            ScreenShotProgram.START_SPECIFY_SCREEN_SHOT = false;
        }
    }
}
