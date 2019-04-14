package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Course;
import student.course.scsv.entity.SelectedCourse;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.CourseRepository;
import student.course.scsv.repository.SelectCourseRepository;
import student.course.scsv.repository.TeacherRepository;
import student.course.scsv.util.FormatString;

import java.util.List;

@Service
public class SelectCourseService {

    @Autowired
    private SelectCourseRepository selectCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public String saveSelectCourse(SelectedCourse selectedCourse) {
        if (selectCourseRepository.findByCidAndSid(selectedCourse.getCid(), selectedCourse.getSid()) != null){
            return FormatString.infoToJson("400", "the course is selected!");
        }
        Course course = courseRepository.findCourseByCid(selectedCourse.getCid());
        course.setCapacity(course.getCapacity() -1 );
        if (course.getCapacity() > 0){
            if (selectCourseRepository.save(selectedCourse) != null) {
                courseRepository.save(course);
                return FormatString.infoToJson("200", "update success");
            }
        }
        return FormatString.infoToJson("200", "update failed");
    }

    public String queryStudentSelected(Long id) {
        List<SelectedCourse> list = selectCourseRepository.findBySid(id);
        JSONArray jsonArray = new JSONArray();
        for (SelectedCourse sc : list) {
            Course course = courseRepository.findCourseByCid(sc.getCid());
            Teacher teacher = teacherRepository.findTeacherById(course.getTid());
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(course));
            System.out.println(jsonObject);
            jsonObject.remove("tid");
            jsonObject.put("teacher", teacher.getName());
            jsonArray.add(jsonObject);
        }
        return FormatString.infoToJson("200", "query success", jsonArray);
    }
}
