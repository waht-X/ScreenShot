package ScreenCapture;

import ScreenCapture.JavaHook.GlobalKeyboardListener;
import ScreenCapture.JavaHook.GlobalMouseListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenCapture {

    public static String saveFilePath;

    public static void main(String[] args) {

        saveFilePath = "C:\\Users\\Administrator\\Desktop\\MyMarkdown\\photo";

        try {

            // Get the logger for "com.github.kwhat.jnativehook" and set the level to warning.
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);

            logger.setUseParentHandlers(false);

            //注册NativeHook
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new GlobalKeyboardListener());
            GlobalScreen.addNativeMouseListener(new GlobalMouseListener());

        } catch (NativeHookException e) {
            e.printStackTrace();
        }

    }
}
