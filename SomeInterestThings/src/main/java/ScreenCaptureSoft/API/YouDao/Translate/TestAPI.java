package ScreenCaptureSoft.API.YouDao.Translate;

import java.io.IOException;
import java.util.Map;

public class TestAPI {
    public static void main(String[] args) throws IOException {
        TranslateTextAPI api = new TranslateTextAPI();
        String s = "梦想" ;
        System.out.println(s);
//        api.setChange(TranslateTextAPI.ZH_CHS, TranslateTextAPI.RUSSIA);
//        api.setOriginalLau(TranslateTextAPI.EN);
        api.setTranslateLau(TranslateTextAPI.ZH_CHS);
        Map<String, String> tr = api.setText(s);
        String text = api.getTranslateText(tr);
        Map<String, String > info = api.getInfo(text);
        System.out.println(info.get("translation"));
        System.out.println(text);
    }
}
