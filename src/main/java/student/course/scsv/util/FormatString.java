package student.course.scsv.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class FormatString {


    public static String infoToJson(String code, String message, Map data){
        return (JSONObject.toJSONString(new MessageObject(code, message, data)));
    }

    public static String userInfoToJson(String code, String message, String teacherInfo) {
        return (JSONObject.toJSONString(new MessageObject(code,message, teacherInfo)));
    }
}
