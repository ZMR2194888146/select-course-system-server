package student.course.scsv.entity;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @Column(length = 10 )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long teacherID;

    //课程名称
    private String name;

    //课程容量
    private Integer capacity;

    //上课时间
    private String time;

    //周几上课
    private String data;

    //上课地点
    private String space;

    //周学时
    private String duce;

    //课程学分
    private Double score;

    public Course() {
    }

    public Course(String name, Integer capacity, String time, String data, String space, Long teacher, String duce, Double score) {
        this.name = name;
        this.capacity = capacity;
        this.time = time;
        this.data = data;
        this.space = space;
        this.duce = duce;
        this.score = score;
        this.teacherID = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
                "id=" + id +
                ", teacherID=" + teacherID +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", time='" + time + '\'' +
                ", data='" + data + '\'' +
                ", space='" + space + '\'' +
                ", duce='" + duce + '\'' +
                ", score=" + score +
                '}';
    }
}
