package student.course.scsv.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

public class MessageObject {

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "data")
    private JSONObject data;

    public MessageObject() {
    }

    public MessageObject(String code, String message, Map data) {
        this.code = code;
        this.message = message;
        this.data = JSONObject.parseObject(JSON.toJSONString(data));
    }

    public MessageObject(String code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = JSONObject.parseObject(data);
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

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
