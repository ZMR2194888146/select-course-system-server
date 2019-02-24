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
            Student t = studentRepository.findStudentById(id);
            return FormatString.userInfoToJson("200", "query successful", JSON.toJSONString(t));
        }
        return FormatString.userInfoToJson("200", "query failed", null);
    }
}
