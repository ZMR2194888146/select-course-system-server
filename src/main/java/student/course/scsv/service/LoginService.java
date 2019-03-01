package student.course.scsv.service;

import com.alibaba.fastjson.JSONObject;
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
        Map<String, Object> data = new HashMap<>();
        Object userinfo;
        if ((userinfo = doCheckLogin(userName, password, userType)) != null){
            data.put("succ", true);
            data.put("userinfo", userinfo);
            return FormatString.infoToJson("200", "login success", data);
        }
        data.put("succ", false);
        return FormatString.infoToJson("200", "account or password error", data);
    }

    //根据用户的类型调用不同的Repository接口
    private JSONObject doCheckLogin(String userName,String password, String userType){
        if ("teacher".equals(userType)){
            return teacherLogin(userName, password);
        }else if ("student".equals(userType)){
            return studentLogin(userName, password);
        }else {
            return administratorLogin(userName, password);
        }
    }

    private JSONObject teacherLogin(String userName, String password){
        Teacher t;
        if ((t = teacherRepository.findTeacherByUsername(userName)) != null){
            if (password.equals(t.getPassword())){
                JSONObject teacher = new JSONObject();
                teacher.put("id", t.getId());
                teacher.put("usertype", "teacher");
                return teacher;
            }
        }
        return null;
    }

    private JSONObject studentLogin(String userName, String password){
        Student s;
        if ((s = studentRepository.findStudentByUsername(userName)) != null){
            if (password.equals(s.getPassword())){
                JSONObject json = new JSONObject();
                json.put("id", s.getId());
                json.put("usertype", "student");
                return json;
            }
        }
        return null;
    }

    private JSONObject administratorLogin(String userName, String password){
        Administrator a;
        if ((a = administatorRepository.findAdministratorByUsername(userName)) != null){
            if (password.equals(a.getPassword())){
                JSONObject json = new JSONObject();
                json.put("id", a.getId());
                json.put("usertype", "admin");
                return json;
            }
        }
        return null;
    }
}
