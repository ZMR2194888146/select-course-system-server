package student.course.scsv.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;
import java.util.Set;

public class MessageObject {

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "data")
    private String data;

    public MessageObject(String code, String message, Map data) {
        this.code = code;
        this.message = message;
        this.data = MessageObject.mapToJson(data);
    }

    public MessageObject(String code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //将Map集合装成JSON字符串
    private static String mapToJson(Map data){
        int size = data.size();
        JSONArray jsonArray = new JSONArray();
        Set<Map.Entry<String, String>> map = data.entrySet();
        for (Map.Entry<String, String> e : map) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(e.getKey(), e.getValue());
            if (size < 2){
                return jsonObject.toJSONString();
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = MessageObject.mapToJson(data);
    }
}
