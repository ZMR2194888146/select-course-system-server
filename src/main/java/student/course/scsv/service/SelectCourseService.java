package student.course.scsv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.SelectedCourse;
import student.course.scsv.repository.SelectCourseRepository;
import student.course.scsv.util.FormatString;

@Service
public class SelectCourseService {

    @Autowired
    private SelectCourseRepository selectCourseRepository;

    public String saveSelectCourse(SelectedCourse selectedCourse){
        if (selectCourseRepository.save(selectedCourse) != null){
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("200", "update failed");
    }
}
