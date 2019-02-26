package student.course.scsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.course.scsv.entity.Course;
import student.course.scsv.service.CourseService;
import student.course.scsv.service.LoginService;
import student.course.scsv.service.MenuService;
import student.course.scsv.service.UserService;

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
    private MenuService menuService;

    @Autowired
    private CourseService courseService;

    /**********************  用户相关接口  ***********************/
    /**
     * 用户登录接口
     * @param id        用户id
     * @param password  用户密码
     * @param usertype  用户类型
     * @return  JSON
     */
    @PostMapping(path = "/login")
    public String login(Long id, String password, String usertype){
       return loginService.checkLogin(id, password,usertype);
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
     * 通过用户id获取用户信息
     * @param id 用户id
     * @return JSON
     */
    @GetMapping( value = "/user/{id}")
    public String getInfo(@PathVariable("id") Long id){
        return userService.getUserInfo(id);
    }

    /**
     * 更新用户密码
     * @param id        用户id
     * @param password  用户的新密码，密码的核对在客户端完成
     * @param usertype  用户类型
     * @return  JSON
     */
    @PutMapping( value = "/user/{id}")
    public String updateUserPassword(@PathVariable("id") Long id, String password,String usertype){
        return userService.updatePassword(id, password, usertype);
    }

    /**
     * 根据用户类型获取菜单列表
     * @param userType 用户类型（teacher | student | admin）
     * @return JSON
     */
    @GetMapping( path = "/menu/{userType}")
    public String getTeacherMenuList(@PathVariable("userType") String userType){
        return menuService.getMenuList(userType);
    }

    /**********************  课程相关接口  ***********************/
    /**
     * 获取所有的课表
     * @return JSON
     */
    @GetMapping( path = "/course")
    public String getCourses(){
        return "";
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
    public String addCourse(String name, String tid, String capacity,
                            String time, String data, String space, String duce,
                            String score){
        return courseService.saveCourse(new Course( name,  Integer.parseInt(capacity),  time,  data,  space,  Long.parseLong(tid),  duce, Double.parseDouble(score)));
    }

    /**
     * 删除课程
     * @param cid 需要删除的课程id
     * @return  JSON
     */
    @DeleteMapping( path = "/course")
    public String delCourse(String cid){
        return courseService.delCourse(Long.parseLong(cid));
    }

}
