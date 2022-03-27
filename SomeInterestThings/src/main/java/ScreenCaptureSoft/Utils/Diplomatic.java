package ScreenCaptureSoft.Utils;



import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一些标准化的规定
 */
public class Diplomatic {

    /**
     * 屏幕默认截图保存为桌面<br>
     * 调用FileSystemView.getFileSystemView().getHomeDirectory()将获得系统桌面的文件路径
     */
    public static String DEFAULT_PHOTO_STORE_PATH =
            FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();

    /**
     * 工程中图片的相对路径
     */
    public static String ARTIFACT_PHOTO_PATH = "./Photo";

    /**
     * 按钮上的图片默认大小
     */
    public static Size BUTTON_PHOTO_SIZE = new Size(30, 30);

    /**
     * 默认的保存的图片格式
     */
    public static String PHOTO_FORMAT = "png";

    /**
     * 默认时间格式
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 已指定的格式获取照片的命名<br>
     * 照片命名以当前截图时间为准
     */
    public static String getPhotoName() {
        return simpleDateFormat.format(new Date());
    }

    /**
     * 传入一个icon类型的图片，将该图片更改为指定大小的icon,以适应当前JButton<br>
     * 只当大小以Diplomatic的BUTTON_PHOTO_SIZE的大小为准
     */
    public static void getSpecifyIco(ImageIcon icon) {
        icon.setImage(icon.getImage().
                getScaledInstance(Diplomatic.BUTTON_PHOTO_SIZE.width, Diplomatic.BUTTON_PHOTO_SIZE.height, Image.SCALE_DEFAULT));
    }

}
