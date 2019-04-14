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
    private Integer time;

    //课程时长
    @JSONField(name = "acount")
    private Integer count;

    //周几上课
    @JSONField(name = "date")
    private Integer date;

    //上课地点
    @JSONField(name = "space")
    private String space;

    //周学时
    @JSONField(name = "duce")
    private int duce;

    //课程学分
    @JSONField(name = "score")
    private Double score;

    public Course() {
    }

    public Course(Long tid, String name, Integer capacity, Integer time, Integer count, Integer date, String space, int duce, Double score) {
        this.tid = tid;
        this.name = name;
        this.capacity = capacity;
        this.time = time;
        this.count = count;
        this.date = date;
        this.space = space;
        this.duce = duce;
        this.score = score;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public int getDuce() {
        return duce;
    }

    public void setDuce(int duce) {
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
