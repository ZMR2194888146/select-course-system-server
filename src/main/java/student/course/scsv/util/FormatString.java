package student.course.scsv.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import student.course.scsv.entity.Course;
import student.course.scsv.entity.User;

import java.util.Map;
import java.util.Set;

/**
 * 格式化输出消息
 */
public class FormatString {

    private static JSONObject getJSONObject(String code, String message){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        return jsonObject;
    }

    public static String infoToJson(String code, String message, String data){
        JSONObject jsonObject = getJSONObject(code, message);
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

    public static String infoToJson(String code, String message, User user){
        JSONObject jsonObject = getJSONObject(code, message);
        JSONObject u = JSONObject.parseObject(JSON.toJSONString(user));
        u.remove("password");
        jsonObject.put("data", u);
        return jsonObject.toJSONString();
    }

    public static String infoToJson(String code, String message, Course course){
        JSONObject jsonObject = getJSONObject(code, message);
        jsonObject.put("data", course);
        return jsonObject.toJSONString();
    }

    public static String infoToJson(String code, String message, JSONArray jsonArray){
        JSONObject jsonObject = getJSONObject(code, message);
        jsonObject.put("data", jsonArray);
        return jsonObject.toJSONString();
    }

    public static String infoToJson(String code, String message, JSONObject data){
        JSONObject jsonObject = getJSONObject(code, message);
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

    public static String infoToJson(String code, String message, Map data){
        return infoToJson(code, message, mapToJSONObject(data));
    }

    public static String infoToJson(String code, String message){
        return infoToJson(code, message, "");
    }


    private static JSONObject mapToJSONObject(Map map){
        JSONObject jsonObject = new JSONObject();
        Set<Map.Entry<String, Object>> list = map.entrySet();
        for (Map.Entry<String, Object> e: list) {
            if (e.getValue() instanceof Map){
                jsonObject.put(e.getKey(), mapToJSONObject((Map) e.getValue()));
            }else {
                jsonObject.put(e.getKey(),e.getValue());
            }
        }
        return jsonObject;
    }

     static String getString(JSONObject json, String s, User a) {
        JSONObject admin = JSONObject.parseObject(s);
        admin.remove("password");
        json.put("userinfo", admin);
        return FormatString.infoToJson("200","query successful", json);
    }

}
