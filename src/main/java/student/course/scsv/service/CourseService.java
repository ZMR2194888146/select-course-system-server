package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Course;
import student.course.scsv.repository.CourseRepository;
import student.course.scsv.util.FormatString;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * 保存一门课程
     * @param course    新创建的课程
     * @return  JSON
     */
    public String saveCourse(Course course){
        if (courseRepository.save(course) != null){
            return FormatString.infoToJson("200", "update success", "");
        }
        return FormatString.infoToJson("200", "update failed", "");
    }

    /**
     * 删除一门课程
     * @param cid   需要删除的课程id
     * @return JSON
     */
    public String delCourse(Long cid){
        courseRepository.removeCourseById(cid);
        return FormatString.infoToJson("200", "update success");
    }

    /**
     * 获取所有的课程
     * @return
     */
    public String getAllCourse(){
        List<Course> list = courseRepository.findAll();
        JSONArray ja = new JSONArray();
        for (Course course : list){
            ja.add(JSONObject.parseObject(JSON.toJSONString(course)));
        }
        return FormatString.infoToJson("200", "query success", ja);
//        return ja.toJSONString();
    }
}
