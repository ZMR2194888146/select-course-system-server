package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Course;
import student.course.scsv.entity.SelectedCourse;
import student.course.scsv.entity.Student;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.CourseRepository;
import student.course.scsv.repository.SelectCourseRepository;
import student.course.scsv.repository.StudentRepository;
import student.course.scsv.repository.TeacherRepository;
import student.course.scsv.util.FormatString;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SelectCourseRepository selectCourseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public boolean existStudent(Long id) {
        return studentRepository.existsStudentById(id);
    }

    public String getStudentInfo(Long id) {
        if (existStudent(id)) {
            Student s = studentRepository.findStudentById(id);
            JSONObject json = menuService.getMenuList("student");
            json.put("userinfo", JSONObject.parseObject(JSON.toJSONString(s)));
            return FormatString.infoToJson("200", "query successful", json);
        }
        return FormatString.infoToJson("200", "query failed");
    }

    /**
     * 获取所有的学生列表，以JSON格式返回
     *
     * @return JSONObject
     */
    public JSONArray getAllStudent() {
        List<Student> students = studentRepository.findAll();
        JSONArray array = new JSONArray();
        for (Student student : students) {
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(student));
            json.remove("password");
            array.add(json);
        }
        return array;
    }

    /**
     * 修改学生密码
     *
     * @param id    需要修改的学生的id
     * @param nPass 新的密码
     * @param oPass 旧的密码
     * @return JOSN
     */
    public String updatePassword(Long id, String nPass, String oPass) {
        if (existStudent(id)) {
            Student s = studentRepository.findStudentById(id);
            if (oPass.equals(s.getPassword())) {
                s.setPassword(nPass);
                studentRepository.save(s);
                return FormatString.infoToJson("200", "update successful");
            }
        }
        return FormatString.infoToJson("400", "enter info have error !");
    }

    /**
     * 添加一个学生实例
     *
     * @param student 需要保存的学生实例
     */
    public String addStudent(Student student) {
        if (studentRepository.save(student) != null) {
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("400", "update failed");
    }

    /**
     * 根据学生id删除学生
     *
     * @param id 要删除的学生id
     * @return JSON
     */
    public String deleteStudent(Long id) {
        if (studentRepository.existsStudentById(id)) {
            studentRepository.deleteStudentById(id);
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("400", "the man was not found! ");
    }

    /**
     * 根据学生id组织学生的课程信息并返回
     * @param sid   需要查询的课表的学生id
     * @return  重新组织过的额学生课表的JSON字符串
     */
    public String getSchedule(Long sid) {
        List<SelectedCourse> list = selectCourseRepository.findBySid(sid);
        if (list.size() > 0) {
            JSONArray array = new JSONArray();
            for (SelectedCourse sc : list) {
                Course course = courseRepository.findCourseByCid(sc.getCid());
                JSONObject info = new JSONObject();
                info.put("cname", course.getName());
                info.put("date", course.getDate());
                info.put("count", course.getCount());
                info.put("teacher", teacherRepository.findTeacherById(course.getTid()).getName());
                info.put("room", course.getSpace());
                info.put("duce", course.getDuce());
                info.put("time", course.getTime());
                array.add(info);
            }
            return FormatString.infoToJson("200", "query success", array);
        }
        return FormatString.infoToJson("400", "no data");
    }
}
