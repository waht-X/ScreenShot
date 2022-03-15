package ScreenCaptureSoft.Test;

import javax.swing.filechooser.FileSystemView;

public class someTest {
    public static void main(String[] args) {
        FileSystemView systemView = FileSystemView.getFileSystemView();
        System.out.println(systemView.getHomeDirectory());
        System.out.println(systemView.getDefaultDirectory());
    }
}
