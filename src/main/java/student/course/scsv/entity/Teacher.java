package student.course.scsv.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teacher extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JSONField(name = "id")
    private Long id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "college")
    private String college;

    //用户登录名
    @JSONField(name = "username")
    private String username;

    private String password;

    public Teacher() {
    }

    /**
     * @param name      教师真实姓名
     * @param college   教师所属学院
     * @param userName  教师登录名
     * @param password  初始密码
     */
    public Teacher(String name, String college, String userName, String password) {
        this.name = name;
        this.college = college;
        this.username = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
