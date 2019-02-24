package student.course.scsv.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.TeacherRepository;

public class LoginServiceTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testLogin(){
        Teacher teacher = teacherRepository.findTeacherById(545454L);
        System.out.println(teacher);
    }


}