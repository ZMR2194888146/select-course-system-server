package student.course.scsv.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * 格式化输出消息
 */
public class FormatString {

    public static String infoToJson(String code, String message, String data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("messsage", message);
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

    public static String infoToJson(String code, String message){
        return infoToJson(code, message, "");
    }

    public static String infoToJson(String code, String message, JSONObject data){
        return infoToJson(code,message, JSONObject.toJSONString(data));
    }

    public static String infoToJson(String code, String message, Map data){
        return infoToJson(code, message, mapToJSONObject(data));
    }


    public static String userInfoToJson(String code, String message, String teacherInfo) {
        return infoToJson(code,message, teacherInfo);
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

}
