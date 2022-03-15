package ScreenCapture.JavaHook;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyboardListener implements NativeKeyListener {

    private boolean VK_CTRL = false;
    private boolean VK_8 = false;
    public static boolean mouseStartMoveRecord = false;

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_8) {
            VK_8 = true;
        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            VK_CTRL = true;
        }
        if (VK_CTRL && VK_8) {
            mouseStartMoveRecord = true;
            VK_8 = false;
            VK_CTRL = false;
            System.out.println("the hot key already reset and tag is already");
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_8) {
            VK_8 = false;
        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            VK_CTRL = false;
        }
    }
}
