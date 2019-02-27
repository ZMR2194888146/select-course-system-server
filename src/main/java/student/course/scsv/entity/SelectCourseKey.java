package student.course.scsv.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SelectCourseKey implements Serializable {

    private Long sid;

    private Long cid;

    public SelectCourseKey() {
    }

    public SelectCourseKey(Long sid, Long cid) {
        this.sid = sid;
        this.cid = cid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectCourseKey that = (SelectCourseKey) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(cid, that.cid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, cid);
    }
}
