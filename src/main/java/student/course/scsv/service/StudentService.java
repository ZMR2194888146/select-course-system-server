package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Student;
import student.course.scsv.repository.StudentRepository;
import student.course.scsv.util.FormatString;

@Service
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
            return FormatString.infoToJson("200", "query successful", s);
        }
        return FormatString.infoToJson("200", "query failed");
    }

    /**
     * 获取所有的学生列表，以JSON格式返回
     * @return JSONObject
     */
    public JSONObject getAllStudent(){
        return JSONObject.parseObject(JSON.toJSONString(studentRepository.findAll()));
    }

    public String updatePassword(Long id, String password){
        if (existStudent(id)){
            Student s = studentRepository.findStudentById(id);
            s.setPassword(password);
            studentRepository.save(s);
            return FormatString.infoToJson("200","update successful");
        }
        return FormatString.infoToJson("200","update failed");
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

}
