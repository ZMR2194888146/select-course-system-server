package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Student;
import student.course.scsv.repository.StudentRepository;
import student.course.scsv.util.FormatString;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MenuService menuService;

    public boolean existStudent(Long id){
        return studentRepository.existsStudentById(id);
    }

    public String getStudentInfo(Long id){
        if (existStudent(id)){
            Student s = studentRepository.findStudentById(id);
            JSONObject json = menuService.getMenuList("student");
            json.put("userinfo", JSONObject.parseObject(JSON.toJSONString(s)));
            return FormatString.infoToJson("200", "query successful", json);
        }
        return FormatString.infoToJson("200", "query failed");
    }

    /**
     * 获取所有的学生列表，以JSON格式返回
     * @return JSONObject
     */
    public JSONArray getAllStudent(){
        List<Student> students = studentRepository.findAll();
        JSONArray array = new JSONArray();
        for (Student student: students) {
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(student));
            json.remove("password");
            array.add(json);
        }
        return array;
    }

    /**
     * 更新学生密码
     * @param id        学生id
     * @param password  新密码
     * @return  JSON
     */
    public String updatePassword(Long id, String password){
        if (existStudent(id)){
            Student s = studentRepository.findStudentById(id);
            s.setPassword(password);
            studentRepository.save(s);
            return FormatString.infoToJson("200","update successful");
        }
        return FormatString.infoToJson("200","update failed");
    }

    /**
     * 保存一个学生实例
     * @param student   需要保存的学生实例
     */
    public String saveStudent(Student student){
        if (studentRepository.save(student) != null){
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("400", "update failed");
    }

    public String deleteStudent(Long id){
        if (studentRepository.existsStudentById(id)){
            studentRepository.deleteStudentById(id);
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("400", "the man was not found! " );
    }

}
