package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Course;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.CourseRepository;
import student.course.scsv.repository.TeacherRepository;
import student.course.scsv.util.FormatString;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * 保存一门课程
     * @param course    新创建的课程
     * @return  JSON
     */
    public String saveCourse(Course course){
        if (courseRepository.save(course) != null){
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("200", "update failed");
    }

    /**
     * 删除一门课程
     * @param cid   需要删除的课程id
     * @return JSON
     */
    public String delCourse(Long cid){
        if (courseRepository.exists(cid)){
            courseRepository.deleteByCid(cid);
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("400", "this course was not found");
    }

    /**
     * 获取所有的课程
     * @return
     */
    public String getAllCourse(){
        List<Course> list = courseRepository.findAll();
        JSONArray ja = new JSONArray();
        for (Course course : list){
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(course));
            Teacher t = teacherRepository.findTeacherById(course.getTid());
            json.put("teacher", t.getName());
            ja.add(json);
        }
        return FormatString.infoToJson("200", "query success", ja);
    }

    /**
     * 通过课程id查询该课程的信息
     * @param id    要查询课程的id
     * @return  JSON
     */
    public String getCourseById(Long id){
        Course course = courseRepository.findCourseByCid(id);
        if (course != null) return FormatString.infoToJson("200", "query success", course);
        return FormatString.infoToJson("200", "query failed");
    }

    /**
     * 通过教师id查询其创建的所有课程
     * @param tid   教师id
     * @return  JSON
     */
    public String getCourseByTid(Long tid){
        JSONArray array = new JSONArray();
        List<Course> courses = courseRepository.findCoursesByTid(tid);
        for (Course c: courses ) {
            array.add(JSONObject.parseObject(JSON.toJSONString(c)));
        }
        return FormatString.infoToJson("200", "query success", array);
    }
}
