package ScreenCaptureSoft.JavaSwingListener;

import ScreenCaptureSoft.API.YouDao.Translate.TranslateTextAPI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TranslateMenuBarMouseListener implements ActionListener {

    private JMenuItem jMenu;
    private JFrame jFrame;
    private TranslateTextAPI api;
    private static final int TAG_ORIGINAL = 1;
    private static final int TAG_TRANSLATED = 2;

    public TranslateMenuBarMouseListener(JMenuItem jMenu, JFrame jFrame, TranslateTextAPI api) {
        this.jMenu = jMenu;
        this.jFrame = jFrame;
        this.api = api;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String itemText = jMenu.getText();
        String winTitle = jFrame.getTitle();

        if (api == null) {
            System.out.println("api is null");
        }
        switch (itemText) {
            case "T_Chinese":
                api.setTranslateLau(TranslateTextAPI.ZH_CHS);
                break;
            case "T_English":
                api.setTranslateLau(TranslateTextAPI.EN);
                break;
            case "T_FRENCH":
                api.setTranslateLau(TranslateTextAPI.FRENCH);
                break;
            case "T_RUSSIA":
                api.setTranslateLau(TranslateTextAPI.RUSSIA);
                break;
            case "T_JAPAN" :
                api.setTranslateLau(TranslateTextAPI.JAPAN);
                break;
            case "T_AUTO" :
                api.setTranslateLau(TranslateTextAPI.AUTO);
                break;
            case "O_Chinese":
                api.setOriginalLau(TranslateTextAPI.ZH_CHS);
                break;
            case "O_English":
                api.setOriginalLau(TranslateTextAPI.EN);
                break;
            case "O_FRENCH":
                api.setOriginalLau(TranslateTextAPI.FRENCH);
                break;
            case "O_RUSSIA":
                api.setOriginalLau(TranslateTextAPI.RUSSIA);
                break;
            case "O_JAPAN" :
                api.setOriginalLau(TranslateTextAPI.JAPAN);
                break;
            case "O_AUTO" :
                api.setOriginalLau(TranslateTextAPI.AUTO);
                break;
            default:
                System.out.println("error");
                JOptionPane.showMessageDialog(null,
                        "翻译语言错误出现，TranslateMenuBarMouseListener-46","error", JOptionPane.ERROR_MESSAGE);
        }
        if (itemText.startsWith("O")) {
            setTitle(TAG_ORIGINAL, itemText);
        } else {
            setTitle(TAG_TRANSLATED, itemText);
        }
    }

    private void setTitle(int tag, String lau) {
        lau = lau.substring(2);
        String prefix = "翻译";
        StringBuilder builder = new StringBuilder(jFrame.getTitle().substring(2));
        System.out.println(builder);
        if (tag == TAG_ORIGINAL) {
            int index = builder.indexOf("<");
            builder.delete(1, index);
            builder.insert(1, lau);
        } else if (tag == TAG_TRANSLATED) {
            int index = builder.indexOf(">");
            builder.delete(index + 1, builder.length() - 1);
            builder.insert(builder.length() - 1, lau);
        }
        jFrame.setTitle(prefix + builder);
    }
}
