package student.course.scsv.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.util.FormatString;

@Service
public class UserService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    public String getUserInfo(Long id){
        if (teacherService.existTeacher(id)){
            return getTeacherInfo(id);
        }else {
            return getStudentInfo(id);
        }
    }

    /**
     * 获取所有的用户信息
     */
    public String getAllUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacher", teacherService.getAllTeacher());
        jsonObject.put("student", studentService.getAllStudent());
        return FormatString.infoToJson("200", "query success", jsonObject);
    }

    public String updatePassword(Long id, String password, String usertype) {
        if ("student".equals(usertype)){
            return studentService.updatePassword(id, password);
        }else {
            return teacherService.updatePassword(id, password);
        }
    }

    private String getTeacherInfo(Long id){
        return teacherService.getTeacherInfo(id);
    }

    private String getStudentInfo(Long id){
        return studentService.getStudentInfo(id);
    }
}
