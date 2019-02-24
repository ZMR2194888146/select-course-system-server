package student.course.scsv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    public String getUserInfo(Long id){
        if (teacherService.existTeacher(id)){
            return getTeacherInfo(id);
        }else {
            return getStudentInfo(id);
        }
    }

    private String getTeacherInfo(Long id){
        return teacherService.getTeacherInfo(id);
    }

    private String getStudentInfo(Long id){
        return studentService.getStudentInfo(id);
    }
}
