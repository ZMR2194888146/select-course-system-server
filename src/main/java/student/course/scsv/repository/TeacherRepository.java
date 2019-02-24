package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.Teacher;


public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
    /*
    同StudentRepository
     */
    Teacher findTeacherById(Long id);
    boolean existsTeacherById(Long id);
}
