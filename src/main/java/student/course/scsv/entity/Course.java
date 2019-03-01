package student.course.scsv.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

/**
 * 配置上JSON域，方便将实例转成JSON对象
 */
@Entity
public class Course {

    @Id
    @Column(length = 10 )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JSONField(name = "cid")
    private Long cid;

    @JSONField(name = "tid")
    private Long tid;

    //课程名称
    @JSONField(name = "cname")
    private String name;

    //课程容量
    @JSONField(name = "capacity")
    private Integer capacity;

    //上课时间
    @JSONField(name = "time")
    private String time;

    //周几上课
    @JSONField(name = "date")
    private String date;

    //上课地点
    @JSONField(name = "space")
    private String space;

    //周学时
    @JSONField(name = "duce")
    private String duce;

    //课程学分
    @JSONField(name = "score")
    private Double score;

    public Course() {
    }

    public Course(Long teacherID, String name, Integer capacity, String time, String date, String space, String duce, Double score) {
        this.tid = teacherID;
        this.name = name;
        this.capacity = capacity;
        this.time = time;
        this.date = date;
        this.space = space;
        this.duce = duce;
        this.score = score;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getDuce() {
        return duce;
    }

    public void setDuce(String duce) {
        this.duce = duce;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", tid=" + tid +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", space='" + space + '\'' +
                ", duce='" + duce + '\'' +
                ", score=" + score +
                '}';
    }
}
