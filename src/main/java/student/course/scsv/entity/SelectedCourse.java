package student.course.scsv.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass( value = SelectCourseKey.class)
public class SelectedCourse {

    @Id
    private Long sid;

    @Id
    private Long cid;

    public SelectedCourse() {
    }

    public SelectedCourse(Long sid, Long cid) {
        this.sid = sid;
        this.cid = cid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "SelectedCourse{" +
                "sid=" + sid +
                ", cid=" + cid +
                '}';
    }
}
