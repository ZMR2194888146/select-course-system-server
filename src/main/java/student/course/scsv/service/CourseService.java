package student.course.scsv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Course;
import student.course.scsv.repository.CourseRepository;
import student.course.scsv.util.FormatString;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public String saveCourse(Course course){
        if (courseRepository.save(course) != null){
            return FormatString.infoToJson("200", "update success", "");
        }
        return FormatString.infoToJson("200", "update failed", "");
    }

    public String delCourse(Long cid){
        if (courseRepository.deleteById(cid)){
            return FormatString.infoToJson("200", "update success", "");
        }
        return FormatString.infoToJson("200", "update failed", "");
    }

}
