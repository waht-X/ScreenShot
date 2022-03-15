package ScreenCapture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * 对指定的坐标内的区域进行截图并保存
 */
public class ScreenShot {

    private BufferedImage image;
    private String format = "png";
    /**
     * 默认路径，为本工程的路径
     */
    public static String defaultPath;

    public ScreenShot() {
        File file = new File("");
        defaultPath = file.getAbsolutePath();
        init();
    }

    /**
     * 将指定的图片保存到指定位置
     * @param saveFilePath 需要保存的图片的位置
     * @param fileName  文件名
     * @param startPoint  图片的左上坐标
     * @param endPoint  图片的右下坐标
     */
    public void saveImage(String saveFilePath, String fileName, Point startPoint, Point endPoint) {
        File file = new File(saveFilePath + File.separator + fileName + "." + format);
        if (!file.exists()) {
            file.mkdir();
        }
        BufferedImage bufferedImage = image.getSubimage(startPoint.x, startPoint.y,
                endPoint.x - startPoint.x, endPoint.y - startPoint.y);
        try {
            ImageIO.write(bufferedImage, format, file);
            System.out.println("-----------------saved photo-----------------");
            System.out.println("the path:" + saveFilePath);
            System.out.println("file name:" + fileName);
            System.out.println("------------------over-----------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //对参数初始化
    private void init() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rectangle = new Rectangle(dimension);
        try {
            Robot robot = new Robot();
            image = robot.createScreenCapture(rectangle);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置图片保存的格式
     * @param format 默认格式png(jpg/png/...)
     */
    public void setFormat(String format) {
        this.format = format;
    }
}
