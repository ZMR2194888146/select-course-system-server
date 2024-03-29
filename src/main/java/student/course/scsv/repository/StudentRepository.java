package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //通过学号查找学生
    Student findStudentById(Long id);
    //通过用户名查找学生
    Student findStudentByUsername(String userName);
    //通过学生判断学号是否存在
    boolean existsStudentById(Long id);
    //通过学生ID删除学生
    void deleteStudentById(Long id);
}
