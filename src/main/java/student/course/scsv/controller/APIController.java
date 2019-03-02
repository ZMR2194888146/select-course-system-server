package student.course.scsv.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.course.scsv.entity.Course;
import student.course.scsv.entity.SelectedCourse;
import student.course.scsv.entity.Student;
import student.course.scsv.entity.Teacher;
import student.course.scsv.service.*;

/**
 * 系统向客户端提供的唯一的数据交互通道
 */
@RestController
@RequestMapping(path = "/api")
public class APIController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private   CourseService courseService;

    @Autowired
    private SelectCourseService selectCourseService;

    @Autowired
    private AdminService adminService;

    /**********************  用户相关接口  ***********************/
    /**
     * 用户登录接口
     * @param jsonObject 包含用户信息的json对象
     * @return JSONObject
     */
    @PostMapping(path = "/login")
    public String login(@RequestBody JSONObject jsonObject){
        String re = loginService.checkLogin(jsonObject.get("username").toString(), jsonObject.get("password").toString(), jsonObject.get("usertype").toString());
        return re;
    }

    /**
     * 获取所有的用户，仅包括学生和教师
     * @return JSON
     */
    @GetMapping( path = "/user")
    public String getAllUser(){
        return userService.getAllUser();
    }

    /**
     * 根据教师id获取教师的信息
     * @param id    教师信息
     * @return  JSON
     */
    @GetMapping(path = "/teacher/{id}")
    public String getTeacherInfo(@PathVariable("id")  Long id){
        return teacherService.getTeacherInfoById(id);
    }

    /**
     * 通过学生id获取用户信息
     * @param id 用户id
     * @return JSON
     */
    @GetMapping( value = "/student/{id}")
    public String getInfo(@PathVariable("id") Long id){
        return studentService.getStudentInfo(id);
    }

    /**
     * 更学生用户密码
     * @param id        用户id
     * @param password  用户的新密码，密码的核对在客户端完成
     *  @return  JSON
     */
    @PutMapping( value = "/student/{id}")
    public String updateStudentPassword(@PathVariable("id") Long id, String password){
        return studentService.updatePassword(id, password);
    }

    /**
     * 更新教师用户密码
     * @param id        用户id
     * @param password  用户的新密码，密码的核对在客户端完成
     *  @return  JSON
     */
    @PutMapping( value = "/teacher/{id}")
    public String updateTeacherPassword(@PathVariable("id") Long id, String password){
        return teacherService.updatePassword(id, password);
    }


    /**********************  课程相关接口  ***********************/
    /**
     * 获取所有的课表
     * @return JSON
     */
    @GetMapping( path = "/course")
    public String getCourses(){
        return courseService.getAllCourse();
    }

    /**
     * 通过教师id查询该教师创建的课表
     * @param id 教师id
     * @return JSON
     */
    @GetMapping(path = "/course/teacher/{id}")
    public String getCourseByTid(@PathVariable("id") Long id){
        return courseService.getCourseByTid(id);
    }

    @GetMapping(path = "/course/{id}")
    public String getCourseInfo(@PathVariable("id") Long id){
        return courseService.getCourseById(id);
    }

    /**
     * 添加一门课程
     * @param name      课程名称
     * @param tid       教师编号
     * @param capacity  课程容量
     * @param time      上课时间（一天中的具体时间）
     * @param data      上课日期（一周中的一天，使用阿拉伯数字）
     * @param space     上课地点（教室编号或场地）
     * @param duce      周学时（上几周）
     * @param score     学分
     * @return JSON
     */
    @PostMapping( path = "/course")
    public String addCourse(String name, Long tid, Integer capacity,
                            String time, String data, String space, String duce,
                            Double score){
        return courseService.saveCourse(new Course(tid, name, capacity, time, data, space, duce, score));
    }

    /**
     * 通过指定的课程id删除一门课程
     * @param cid   课程id
     * @return  JOSN
     */
    @DeleteMapping( path = "/course/{id}")
    public String delCourse(@PathVariable("id") Long cid){
        return courseService.delCourse(cid);
    }

    /**************************************************
     //                      选课相关接口
     **************************************************/
    /**
     * 添加一个学生选的一门课
     * @param sid   学生学号
     * @param cid   学生选的课程号
     * @return  JSON
     */
    @PostMapping(path = "/sc")
    public String addSelectCourse(String sid, String cid){
        return selectCourseService.saveSelectCourse(new SelectedCourse(Long.parseLong(sid), Long.parseLong(cid)));
    }

    /**
     * 查询一个学生所选的所有课程
     * @param id    学生学号
     * @return  JSON
     */
    @GetMapping(path = "/sc/{id}")
    public String showSelectedCourse(@PathVariable("id") Long id){
        return selectCourseService.queryStudentSelected(id);
    }

    /*******************************************************
     //                   管理员相关接口
     *******************************************************/
    /**
     * 添加一个教师用户，默认密码为8个8
     * @param name      用户真实姓名
     * @param college   用户所属学院
     * @param username  用户登录名
     * @return  JSON
     */
    @PostMapping(path = "/admin/teacher")
    public String addTeacherUser(String name, String college,String username){
        return teacherService.addTeacher(new Teacher(name, college, username, "88888888"));
    }

    @PostMapping(path = "/admin/student")
    public String addStudentUser(String username, String className, String name, String college, String major){
        return studentService.saveStudent(new Student(username, "88888888", className, name, college, major));
    }

    @GetMapping(path = "/admin/{id}")
    public String getAdminInfo(@PathVariable("id") Long id){
        return adminService.getAdminUserInfo(id);
    }

    /**
     * 根据指定的学生ID删除一个学生
     * @param id 学生ID，不是学号
     * @return  JSON
     */
    @DeleteMapping(path = "/admin/student/{id}")
    public String deletStudent(@PathVariable("id") Long id){
        return studentService.deleteStudent(id);
    }

    /**
     * 根据指定的id删除一位教师
     * @param id    教师id，不是职工号
     * @return  JSON
     */
    @DeleteMapping(path = "/admin/teacher/{id}")
    public String deleteTeacher(@PathVariable("id") Long id){
        System.out.println("id ================================================> " + id);
        return teacherService.deleteTeacher(id);
    }
}
