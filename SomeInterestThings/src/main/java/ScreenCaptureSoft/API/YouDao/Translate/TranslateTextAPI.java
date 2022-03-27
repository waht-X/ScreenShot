package ScreenCaptureSoft.API.YouDao.Translate;

import ScreenCaptureSoft.Utils.Diplomatic;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 使用有道API进行文本翻译，具体demo和完整示例可以查看官网：
 * https://ai.youdao.com/console/#/service-singleton/text-translation
 */
public class TranslateTextAPI {
    private static final String URL = "https://openapi.youdao.com/api";
    private static final String APP_KEY = "2714bb39c574bb8d";
    private static final String APP_SECRET = "RbZvcduA58iXSjrIxPAwbQRWEa5DLwpy";
    private static final String ID = "2714bb39c574bb8d";
    private static String ORIGINAL_LAU = "auto";
    private static String TRANSLATE_LAU = "auto";
    /**
     * 自动识别语言翻译
     */
    public static final String AUTO = "auto";
    /**
     * 中文
     */
    public static final String ZH_CHS = "zh-CHS";
    /**
     * 英文
     */
    public static final String EN = "EN";
    /**
     * 日文
     */
    public static final String JAPAN = "ja";
    /**
     * 法语
     */
    public static final String FRENCH = "ko";
    /**
     * 俄语
     */
    public static final String RUSSIA = "ru";

    public TranslateTextAPI() {

    }

    /**
     * 选择原语言和转换后的语言<br>
     * 该方法应该在使用setText方法前使用才生效
     * @param originalLua 原语言
     * @param translatedLua 转换后的语言
     */
    public void setChange(String originalLua, String translatedLua) {
        ORIGINAL_LAU = originalLua;
        TRANSLATE_LAU = translatedLua;
    }

    /**
     * 设置原语言
     * @param originalLau
     */
    public void setOriginalLau(String originalLau) {
        ORIGINAL_LAU = originalLau;
    }

    /**
     * 设置目标语言
     * @param translateLau
     */
    public void setTranslateLau(String translateLau) {
        TRANSLATE_LAU = translateLau;
    }


    /**
     * 设置需要进行翻译的文本
     */
    public Map<String ,String> setText(String data) {
        Map<String, String> params = new HashMap<>();
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("from", ORIGINAL_LAU);
        params.put("to", TRANSLATE_LAU);
        params.put("signType", "v3");
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = APP_KEY + truncate(data) + salt + curtime + APP_SECRET;
        //sha256(应用ID+input+salt+curtime+应用密钥)
        String sign = getDigest(signStr);
        params.put("appKey", APP_KEY);
        params.put("q", data);
        params.put("salt", salt);
        params.put("sign", sign);
//        params.put("vocabId", ID);
        return params;
    }

    /**
     * 返回翻译后的结果，以json格式显示<br>
     * json对应字段可以查看<br>
     * https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
     */
    public String getTranslateText(Map<String, String> params) throws IOException {

        String json = "";
        //create HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //create httpPost
        HttpPost httpPost = new HttpPost(URL);
        List<NameValuePair> paramsList = new ArrayList<>();
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key, value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            Header[] contentType = httpResponse.getHeaders("Content-Type");
            if ("audio/mp3".equals(contentType[0].getValue())) {
                HttpEntity httpEntity = httpResponse.getEntity();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(baos);
                byte[] result = baos.toByteArray();
                EntityUtils.consume(httpEntity);
                if (result != null) {
                    String file = Diplomatic.DEFAULT_PHOTO_STORE_PATH + File.separator + System.currentTimeMillis() + ".mp3";
                    byte2File(result, file);
                }
            }else {
                /** 响应不是音频流，直接显示结果 */
                HttpEntity httpEntity1 = httpResponse.getEntity();
                json = EntityUtils.toString(httpEntity1, "UTF-8");
                EntityUtils.consume(httpEntity1);
            }
        }finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }

        return json;
    }

    /**
     * 生成加密字段
     */
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     *
     * @param result 音频字节流
     * @param file 存储路径
     */
    private static void byte2File(byte[] result, String file) {
        File audioFile = new File(file);
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(audioFile);
            fos.write(result);
            System.out.println("save the audio");
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private static String truncate(String data) {
        if (data == null) {
            return null;
        }
        int len = data.length();
        return len <= 20 ? data : (data.substring(0, 10) + len + data.substring(len - 10, len));
    }

    /**
     * 解析获得的json数据,获得可用文本
     * @param json 需要进行解析的json数据
     */
    public Map<String, String> getInfo(String json) {

        Map<String, String> info = new HashMap<>();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonObject asJsonObject = jsonParser.parse(json).getAsJsonObject();
        String translation = (asJsonObject.get("translation").getAsJsonArray()).get(0).getAsString();
        String errorCode = asJsonObject.get("errorCode").getAsString();
        info.put("translation", translation);
        info.put("errorCode", errorCode);
        return info;
    }

}
