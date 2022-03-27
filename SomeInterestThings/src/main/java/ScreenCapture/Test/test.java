package ScreenCapture.Test;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class test {
    public static void main(String[] args) throws NativeHookException {

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeMouseMotionListener(new NativeMouseMotionAdapter() {
            @Override
            public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
                System.out.println(nativeMouseEvent.paramString());
            }
        });
    }
}
