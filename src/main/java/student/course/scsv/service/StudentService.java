package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Student;
import student.course.scsv.repository.StudentRepository;
import student.course.scsv.util.FormatString;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean existStudent(Long id){
        return studentRepository.existsStudentById(id);
    }

    public String getStudentInfo(Long id){
        if (existStudent(id)){
            Student s = studentRepository.findStudentById(id);
            return FormatString.userInfoToJson("200", "query successful", JSON.toJSONString(s));
        }
        return FormatString.userInfoToJson("200", "query failed", null);
    }

    public String updatePassword(Long id, String password){
        if (existStudent(id)){
            Student s = studentRepository.findStudentById(id);
            s.setPassword(password);
            studentRepository.save(s);
            return FormatString.infoToJson("200","update successful", null);
        }
        return FormatString.infoToJson("200","update failed", null);
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }
}
