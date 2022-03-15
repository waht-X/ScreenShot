package ScreenCaptureSoft.SoftBody;

import ScreenCaptureSoft.JavaHook.GlobalKeyListener;
import ScreenCaptureSoft.JavaHook.GlobalMouseListener;
import ScreenCaptureSoft.Utils.Diplomatic;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 进行截图
 */
public class ScreenShotProgram {

    private static BufferedImage image;
    private static Rectangle rectangle;
    private static Robot robot;

    //判断全局Hook是否注册
    private static boolean GLOBAL_MOUSE_REGISTER = false;
    private static boolean GLOBAL_KEYBOARD_REGISTER = false;
    private static boolean GLOBAL_HOOK_REGISTER = false;

    private static GlobalMouseListener globalMouseListener;

    /**
     * 判断是否开始进行SpecifyScreenShot
     */
    public static boolean START_SPECIFY_SCREEN_SHOT = false;

    /**
     * 初始化一些参数
     */
    public static void init() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        rectangle = new Rectangle(dimension);
        rectangle.width -= 2;
        rectangle.height -= 2;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //如果没有注册全局Hook，进行注册
        if (!GLOBAL_HOOK_REGISTER) {
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            logger.setUseParentHandlers(false);
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }

        //如果没有添加全局键盘监听则进行添加
        if (!GLOBAL_KEYBOARD_REGISTER) {
            GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
        }
    }


    /**
     * 进行全屏截屏
     */
    public static void fullScreenShot() {
        getImage(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1);

    }

    /**
     * 对指定的位置进行截屏，所指定的位置为启动截屏后鼠标框选的区域
     */
    public static void specifyScreenShot() {
        if (!GLOBAL_MOUSE_REGISTER) {
            globalMouseListener = new GlobalMouseListener();
            GlobalScreen.addNativeMouseListener(globalMouseListener);
        }
        START_SPECIFY_SCREEN_SHOT = true;


    }

    /**
     * 对（x，y）宽w，高h的图像进行存储
     *
     * @param x the X coordinate of the upper-left corner of the specified rectangular region
     * @param y the Y coordinate of the upper-left corner of the specified rectangular region
     * @param w the width of the specified rectangular region
     * @param h the height of the specified rectangular region
     */
    public static void getImage(int x, int y, int w, int h) {
        image = robot.createScreenCapture(rectangle);
        BufferedImage bufferedImage = image.getSubimage(x, y, w + 1, h + 1);
        File file = new File(Diplomatic.DEFAULT_PHOTO_STORE_PATH + File.separator +
                Diplomatic.getPhotoName() + "." + Diplomatic.PHOTO_FORMAT);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            ImageIO.write(bufferedImage, Diplomatic.PHOTO_FORMAT, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
