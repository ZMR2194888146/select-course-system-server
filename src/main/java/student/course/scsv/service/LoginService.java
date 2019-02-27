package student.course.scsv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Adminisitrator;
import student.course.scsv.entity.Student;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.AdministatorRepository;
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
    private AdministatorRepository administatorRepository;

    public String checkLogin(Long id,String password, String userType){
        Map<String, Boolean> message = new HashMap<>();
        if (doCheckLogin(id, password, userType)){
            message.put("succ", true);
            return FormatString.infoToJson("200", "login success", message);
        }
        message.put("succ", false);
        return FormatString.infoToJson("200", "account or password error", message);
    }

    //根据用户的类型调用不同的Repository接口
    private boolean doCheckLogin(Long id,String password, String userType){
        if ("teacher".equals(userType)){
            return teacherLogin(id, password);
        }else if ("student".equals(userType)){
            return studentLogin(id, password);
        }else {
            return administratorLogin(id, password);
        }
    }

    private boolean teacherLogin(Long id, String password){
        Teacher t;
        if ((t = teacherRepository.findTeacherById(id)) != null){
            return password.equals(t.getPassword());
        }
        return false;
    }

    private boolean studentLogin(Long id, String password){
        Student s;
        if ((s = studentRepository.findStudentById(id)) != null){
            return password.equals(s.getPassword());
        }
        return false;
    }

    private boolean administratorLogin(Long id, String password){
        Adminisitrator s;
        if ((s = administatorRepository.findAdminisitratorById(id)) != null){
            return password.equals(s.getPassword());
        }
        return false;
    }
}
