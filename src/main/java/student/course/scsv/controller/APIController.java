package student.course.scsv.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.course.scsv.entity.*;
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
        return loginService.checkLogin(jsonObject.get("username").toString(), jsonObject.get("password").toString(), jsonObject.get("usertype").toString());
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
     * 更新学生用户密码
     * @param id        用户id
     *  @return  JSON
     */
    @PutMapping( value = "/{usertype}/{id}")
    public String updateUserPassword(@PathVariable("id") Long id,@PathVariable("usertype") String userType, @RequestBody JSONObject json){
        String oPass = json.getString("oPass");
        String nPass = json.getString("nPass");
        return userService.updatePassword(userType, id, nPass, oPass);
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
     * @param json
     * @return
     */
    @PostMapping( path = "/course")
    public String addCourse(@RequestBody JSONObject json){
        Long tid = Long.parseLong(json.getString("tid"));
        String name = json.getString("name");
        int capacity = Integer.parseInt(json.getString("capacity"));
        int account = Integer.parseInt(json.getString("account"));
        int time = Integer.parseInt(json.getString("time"));
        double score = Double.parseDouble(json.getString("score"));
        int date = Integer.parseInt(json.getString("date"));
        int duce = Integer.parseInt(json.getString("duce"));
        String space = json.getString("space");
        return courseService.saveCourse(
                new Course(tid, name, capacity, time, account, date, space, duce, score));
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
     * 添加一门选课
     * @param json 包含选课的学生id和课程id
     * @return  JSON
     */
    @PostMapping(path = "/sc")
    public String addSelectCourse(@RequestBody JSONObject json){
        String sid = json.getString("sid");
        String cid = json.getString("cid");
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
     * 添加一个教师用户
     * @param json  包含教师信息的JSON实体
     * @return  JSON
     */
    @PostMapping(path = "/admin/teacher")
    public String addTeacherUser(@RequestBody JSONObject json){
        String name = json.getString("name");
        String college = json.getString("college");
        String username = json.getString("username");
        return teacherService.addTeacher(new Teacher(name, college, username, "88888888"));
    }

    /**
     * 添加一名学生
     * @param json 包含学生信息的json请求体
     * @return  JOSN
     */
    @PostMapping(path = "/admin/student")
    public String addStudentUser(@RequestBody JSONObject json){
        String username = json.getString("" +
                "username");
        String className = json.getString("className");
        String name = json.getString("name");
        String college = json.getString("college");
        String major = json.getString("major");
        Student s = new Student(username, "88888888", className, name, college, major);
        return studentService.addStudent(s);
    }

    /**
     * 添加一位管理员
     * @param json  包含管理员数据的JSON对象
     * @return  JSON
     */
    @PostMapping(path = "/admin")
    public String addAdministrator(@RequestBody JSONObject json){
        return adminService.addAdministrator(new Administrator(json.getString("username"),  "88888888"));
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
        return teacherService.deleteTeacher(id);
    }
}
