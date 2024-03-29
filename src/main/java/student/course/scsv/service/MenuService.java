package student.course.scsv.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Value("${menu.student}")
    private String studentMenuList;

    @Value("${menu.teacher}")
    private String teacherMenuList;

    @Value("${menu.admin}")
    private String adminMenuList;

    private JSONObject getStudentMenuList() {
        return JSONObject.parseObject(studentMenuList.substring(1, studentMenuList.length()-1));
    }

    private JSONObject getTeacherMenuList() {
        return JSONObject.parseObject(teacherMenuList.substring(1,teacherMenuList.length()-1));
    }

    private JSONObject getAdministratorMenuList(){
        return JSONObject.parseObject(adminMenuList.substring(1, adminMenuList.length() - 1));
    }

    public JSONObject getMenuList(String userType){
        if ("teacher".equals(userType)){
            return getTeacherMenuList() ;
        }else if ("student".equals(userType)){
            return getStudentMenuList() ;
        }else {
            return getAdministratorMenuList();
        }
    }
}
