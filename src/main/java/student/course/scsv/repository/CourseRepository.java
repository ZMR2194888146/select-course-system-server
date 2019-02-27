package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    void removeCourseById(Long id);
    Course findCourseById(Long id);
}
