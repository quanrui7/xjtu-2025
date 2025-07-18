package com.mybatisplusdemo.common.utils;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.speech.AipSpeech;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaiduUtil {

    //设置APPID/AK/SK
    public static final String APP_ID = "40927715";
    public static final String API_KEY = "DPinK7BkM3yI4Qptnr0eFZNH";
    public static final String SECRET_KEY = "wmZbglKccAGMBwzZCyn9IVQfK2ObRAtc";
    private static AipOcr ocrClient = null;

    //根据经纬度获得省市区信息
    public static Map<String, String> getCityByLonLat(String key, String lng, String lat) {
        String location = lat + "," + lng;
        try {
            //拼装url
            String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + key + "&output=json&coordtype=wgs84ll&location=" + location;
            String result = HttpClientUtils.doGet(url);
            JSONObject o = new JSONObject(result);
            Map<String, String> area = new HashMap<>();
            area.put("province", o.getJSONObject("result").getJSONObject("addressComponent").getString("province"));
            area.put("city", o.getJSONObject("result").getJSONObject("addressComponent").getString("city"));
            area.put("district", o.getJSONObject("result").getJSONObject("addressComponent").getString("district"));
            area.put("street", o.getJSONObject("result").getJSONObject("addressComponent").getString("street"));
            return area;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取API访问token
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //返回结果示例
            System.err.println("result:" + result);
            org.json.JSONObject jsonObject = new org.json.JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    //识别图片上的文本内容，转成文字字符串返回
    public static String generalString(String imagePath, boolean isNewline) {
        try {
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("language_type", "CHN_ENG"); //CHN_ENG:中英文混合， ENG:英文
            options.put("detect_direction", "true"); //是否检测图像朝向，默认不检测，即：false
            options.put("detect_language", "true"); //是否检测语言，默认不检测。
            options.put("probability", "false"); //是否返回识别结果中每一行的置信度
            //通用文字识别
            if (ocrClient == null) {
                ocrClient = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
                ocrClient.setConnectionTimeoutInMillis(5000);
                ocrClient.setSocketTimeoutInMillis(60000);
            }
            JSONObject jsonObject = ocrClient.basicAccurateGeneral(imagePath, options);
            String result = mergeString(jsonObject, isNewline);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private static String mergeString(JSONObject jsonObject, boolean isNewline) {
        if (jsonObject == null) {
            return "";
        }

        if (jsonObject.has("words_result") && jsonObject.has("words_result_num")) {
            int wordsResultNum = jsonObject.getInt("words_result_num");
            if (wordsResultNum > 0) {
                StringBuilder sb = new StringBuilder();

                JSONArray jsonArray = jsonObject.getJSONArray("words_result");
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    JSONObject obj = (JSONObject) jsonArray.get(i);
                    if (isNewline) {
                        sb.append(obj.get("words") + "\n");
                    } else {
                        sb.append(obj.get("words"));
                    }
                }
                return sb.toString();
            }
        } else {
            return jsonObject.toString();
        }
        return null;
    }

    public static JSONObject animalDetect(String imgPath) {
        //初始化
        AipImageClassify aic = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        //返回JSON格式的数据
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("baike_num", "1");
        JSONObject res = aic.animalDetect(imgPath, params);
        System.out.println(res.toString(2));
        return res;
    }

    public static JSONObject dishDetect(String imgPath) {
        //初始化
        AipImageClassify aic = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        //返回JSON格式的数据
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("baike_num", "1");
        JSONObject res = aic.dishDetect(imgPath, params);
        System.out.println(res.toString(2));
        return res;
    }

    public static JSONObject plantDetect(String imgPath) {
        //初始化
        AipImageClassify aic = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        //返回JSON格式的数据
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("baike_num", "1");
        JSONObject res = aic.plantDetect(imgPath, params);
        System.out.println(res.toString(2));
        return res;
    }

    public static JSONObject advancedGeneral(String imgPath) {
        //初始化
        AipImageClassify aic = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        //返回JSON格式的数据
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("baike_num", "1");
        JSONObject res = aic.advancedGeneral(imgPath, params);
        System.out.println(res.toString(2));
        return res;
    }

    public static JSONObject carDetect(String imgPath) {
        //初始化
        AipImageClassify aic = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        //返回JSON格式的数据
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("baike_num", "1");
        JSONObject res = aic.carDetect(imgPath, params);
        System.out.println(res.toString(2));
        return res;
    }

    public static JSONObject bodyNum(String imgPath) {
        //初始化
        AipBodyAnalysis aba = new AipBodyAnalysis(APP_ID, API_KEY, SECRET_KEY);
        //返回JSON格式的数据
        HashMap<String, String> params = new HashMap<String, String>();
        JSONObject res = aba.bodyNum(imgPath, params);
        System.out.println(res.toString(2));
        return res;
    }

    public static JSONObject asr(String wavPath) {
        //初始化
        AipSpeech as = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        //返回JSON格式的数据
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("channel", 1);
        JSONObject res = as.asr(wavPath, "pcm", 16000, params);
        System.out.println(res.toString(2));
        return res;
    }
}
