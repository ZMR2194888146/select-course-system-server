package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.TeacherRepository;
import student.course.scsv.util.FormatString;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public boolean existTeacher(Long id){
        return teacherRepository.existsTeacherById(id);
    }

    public String getTeacherInfo(Long id){
        if (existTeacher(id)){
            Teacher t = teacherRepository.findTeacherById(id);
            return FormatString.userInfoToJson("200", "query successful",JSON.toJSONString(t));
        }
        return FormatString.userInfoToJson("200", "query failed", null);
    }
}
