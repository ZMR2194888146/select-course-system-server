package student.course.scsv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Administrator;
import student.course.scsv.entity.Student;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.AdministratorRepository;
import student.course.scsv.repository.StudentRepository;
import student.course.scsv.repository.TeacherRepository;
import student.course.scsv.util.FormatString;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录服务
 */
@Service
public class LoginService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdministratorRepository administatorRepository;

    public String checkLogin(String userName,String password, String userType){
        Map<String, Boolean> message = new HashMap<>();
        if (doCheckLogin(userName, password, userType)){
            message.put("succ", true);
            return FormatString.infoToJson("200", "login success", message);
        }
        message.put("succ", false);
        return FormatString.infoToJson("200", "account or password error", message);
    }

    //根据用户的类型调用不同的Repository接口
    private boolean doCheckLogin(String userName,String password, String userType){
        if ("teacher".equals(userType)){
            return teacherLogin(userName, password);
        }else if ("student".equals(userType)){
            return studentLogin(userName, password);
        }else {
            return administratorLogin(userName, password);
        }
    }

    private boolean teacherLogin(String userName, String password){
        Teacher t;
        if ((t = teacherRepository.findTeacherByUsername(userName)) != null){
            return password.equals(t.getPassword());
        }
        return false;
    }

    private boolean studentLogin(String userName, String password){
        Student s;
        if ((s = studentRepository.findStudentByUsername(userName)) != null){
            return password.equals(s.getPassword());
        }
        return false;
    }

    private boolean administratorLogin(String userName, String password){
        Administrator s;
        if ((s = administatorRepository.findAdministratorByUsername(userName)) != null){
            return password.equals(s.getPassword());
        }
        return false;
    }
}
