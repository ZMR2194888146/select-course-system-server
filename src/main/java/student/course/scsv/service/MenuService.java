package student.course.scsv.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import student.course.scsv.util.FormatString;

@Service
public class MenuService {

    @Value("${menu.student}")
    private String studentMenuList;

    @Value("${menu.teacher}")
    private String teacherMenuList;

    @Value("${menu.admin}")
    private String adminMenuList;

    private String getStudentMenuList() {
        return studentMenuList.substring(1, studentMenuList.length() - 1);
    }

    private String getTeacherMenuList() {
        return teacherMenuList.substring(1, teacherMenuList.length()-1);
    }

    private String getAdministratorMenuList(){
        return adminMenuList.substring(1, adminMenuList.length() - 1);
    }

    public String getMenuList(String userType){
        if ("teacher".equals(userType)){
            return FormatString.infoToJson("200", "query successful", getTeacherMenuList()) ;
        }else if ("student".equals(userType)){
            return FormatString.infoToJson("200", "query successful", getStudentMenuList()) ;
        }else {
            return FormatString.infoToJson("200", "query successful", getAdministratorMenuList());
        }
    }
}
