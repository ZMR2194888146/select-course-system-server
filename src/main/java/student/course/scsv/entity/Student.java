package student.course.scsv.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student extends User{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @JSONField(name = "id")
    private Long id;

    //登录名
    @JSONField(name = "username")
    private String username;

    private String password;

    @JSONField(name = "className")
    private String className;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "college")
    private String college;

    @JSONField(name = "major")
    private String major;

    public Student() {
    }

    /**
     * @param username  用户登录名
     * @param password  用户登录密码
     * @param className 班级
     * @param name      用户真实姓名
     * @param college   学院
     * @param major     专业
     */
    public Student(String username, String password, String className, String name, String college, String major) {
        this.username = username;
        this.password = password;
        this.className = className;
        this.name = name;
        this.college = college;
        this.major = major;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
