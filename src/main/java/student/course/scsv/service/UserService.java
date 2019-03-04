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

    @Autowired
    private AdminService adminService;

    public String getUserInfo(Long id, String userType){
        if ("teacher".equals(userType)){
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


    private String getTeacherInfo(Long id){
        return teacherService.getTeacherInfoById(id);
    }

    private String getStudentInfo(Long id){
        return studentService.getStudentInfo(id);
    }

    /**
     * 修改用户的密码
     * @param usertype  修改密码的用户类型
     * @param id        需要修改密码的用户id
     * @param nPass     用户的新密码
     * @param oPass     用户的旧密码
     * @return  JSON
     */
    public String updatePassword(String usertype,Long id, String nPass, String oPass) {
        if ("teacher".equals(usertype)){
           return teacherService.updatePassword(id, nPass, oPass);
        }else if ("student".equals(usertype)){
           return studentService.updatePassword(id, nPass, oPass);
        }else {
           return adminService.updatePassword(id, nPass, oPass);
        }
    }
}
