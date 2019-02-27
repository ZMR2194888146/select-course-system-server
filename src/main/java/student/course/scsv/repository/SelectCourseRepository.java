package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.SelectCourseKey;
import student.course.scsv.entity.SelectedCourse;

public interface SelectCourseRepository extends JpaRepository<SelectedCourse, SelectCourseKey> {
    @Override
    <S extends SelectedCourse> S save(S entity);
}
