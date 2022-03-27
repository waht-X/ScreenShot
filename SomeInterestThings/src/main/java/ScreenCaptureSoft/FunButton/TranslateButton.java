package ScreenCaptureSoft.FunButton;

import ScreenCaptureSoft.API.YouDao.Translate.TranslateTextAPI;
import ScreenCaptureSoft.JavaSwingListener.TranslateMenuBarMouseListener;
import ScreenCaptureSoft.SoftBody.FunctionJButton;
import ScreenCaptureSoft.Utils.ButtonInfo;
import ScreenCaptureSoft.Utils.Diplomatic;
import ScreenCaptureSoft.Utils.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TranslateButton {
    private JFrame translateWin;
    private TextArea originalText;
    private TextArea translatedText;
    private TranslateTextAPI translateTextAPI;

    public TranslateButton(Container container, ButtonInfo buttonInfo) {
        //获取粘贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        FunctionJButton functionJButton = new FunctionJButton(buttonInfo);
        ImageIcon image = new ImageIcon(Diplomatic.ARTIFACT_PHOTO_PATH + File.separator + "translate.png");
        JButton button = functionJButton.createFunctionJButton("", "online translate", image, container);
        button.addMouseListener(new MyMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (translateWin == null) {
                    createTranslateWin();
                }

                try {
                    String data = "";
                    if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor))
                        //获取粘贴板内容
                         data = (String) clipboard.getData(DataFlavor.stringFlavor);
                    //通过指定的翻译接口返回翻译后的结果
                    Map<String, String> info = translate(data);
                    String errorCode = info.get("errorCode");
                    String translation = info.get("translation");
                    if (!errorCode.equals("0")) {
                        translation = "错误码：" + errorCode;
                    }
                    setText(data, translation);
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置翻译文本和原文,并显示在翻译窗口中
     */
    private void setText(String originalData, String translateData) {
        translateWin.setVisible(true);
        originalText.setText(originalData);
        translatedText.setText(translateData);
    }

    /**
     * 通过data数据创建一个翻译窗口
     */
    private void createTranslateWin() {
        translateWin = new JFrame("翻译(auto<=>auto)");
        //设置窗口的基本参数
        translateWin.setBounds(20, 20, 500, 300);
        translateWin.setVisible(false);
        translateWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        translateWin.setResizable(false);
        translateWin.setLayout(null);

        //窗口的主要文本显示
        originalText = new TextArea();
        originalText.setBounds(10, 10, translateWin.getWidth() - 40, 100);
        originalText.setVisible(true);
        translateWin.add(originalText);

        translatedText = new TextArea();
        translatedText.setBounds(originalText.getX(), originalText.getHeight() + originalText.getY() + 20,
                originalText.getWidth(), originalText.getHeight());
        translatedText.setVisible(true);
        translateWin.add(translatedText);

        //添加菜单条
        JMenuBar jMenuBar = new JMenuBar();
        translateWin.setJMenuBar(jMenuBar);

        JMenu originalMenu = new JMenu("original");
        JMenu translationMenu = new JMenu("translation");
        jMenuBar.add(originalMenu);
        jMenuBar.add(translationMenu);

        JMenuItem originalZH = new JMenuItem("O_Chinese");
        JMenuItem originalEN = new JMenuItem("O_English");
        JMenuItem originalFR = new JMenuItem("O_FRENCH");
        JMenuItem originalRU = new JMenuItem("O_RUSSIA");
        JMenuItem originalJA = new JMenuItem("O_JAPAN");
        JMenuItem originalAU = new JMenuItem("O_AUTO");
        originalMenu.add(originalZH);
        originalMenu.add(originalEN);
        originalMenu.add(originalFR);
        originalMenu.add(originalRU);
        originalMenu.add(originalJA);
        originalMenu.add(originalAU);

        JMenuItem translationZH = new JMenuItem("T_Chinese");
        JMenuItem translationEN = new JMenuItem("T_English");
        JMenuItem translationFR = new JMenuItem("T_FRENCH");
        JMenuItem translationRU = new JMenuItem("T_RUSSIA");
        JMenuItem translationJA = new JMenuItem("T_JAPAN");
        JMenuItem translationAU = new JMenuItem("T_AUTO");
        translationMenu.add(translationZH);
        translationMenu.add(translationEN);
        translationMenu.add(translationFR);
        translationMenu.add(translationRU);
        translationMenu.add(translationJA);
        translationMenu.add(translationAU);

        //为每个menuItem添加监听器
        if (translateTextAPI == null) {
            translateTextAPI = new TranslateTextAPI();
        }
        int originalItemCount = originalMenu.getItemCount();
        for (int i = 0; i < originalItemCount; i++) {
            JMenuItem jMenuItem = originalMenu.getItem(i);
            jMenuItem.addActionListener(new TranslateMenuBarMouseListener(jMenuItem, translateWin, translateTextAPI));
        }

        int translateItemCount = translationMenu.getItemCount();
        for (int i = 0; i < translateItemCount; i++) {
            JMenuItem jMenuItem = translationMenu.getItem(i);
            jMenuItem.addActionListener(new TranslateMenuBarMouseListener(jMenuItem, translateWin, translateTextAPI));
        }

    }

    /**
     * 讲msg对应的文本通过指定的翻译api翻译并返回翻译结果
     * @return 返回值为一个Map<String,String><br>
     * Map中存储两个键值对<br>
     * translation -> 翻译后的结果
     * errorCode -> 返回代码，为0的时候表示正常返回
     */
    private Map<String, String> translate(String msg) throws IOException {
        if (translateTextAPI == null) {
            translateTextAPI = new TranslateTextAPI();
        }
        Map<String, String> params = translateTextAPI.setText(msg);
        String json = translateTextAPI.getTranslateText(params);
        Map<String, String> info = translateTextAPI.getInfo(json);
        return info;
    }

}
