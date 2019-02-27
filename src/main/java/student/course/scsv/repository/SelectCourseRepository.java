package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.SelectCourseKey;
import student.course.scsv.entity.SelectedCourse;

import java.util.List;

public interface SelectCourseRepository extends JpaRepository<SelectedCourse, SelectCourseKey> {
    List<SelectedCourse> findBySid(Long sid);
}
