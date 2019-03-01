package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    void deleteByCid(Long cid);
    List<Course> findCoursesByTid(Long tid);
    Course findCourseByCid(Long id);
}
