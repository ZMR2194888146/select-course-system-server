package student.course.scsv.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class SelectedCourse {

    @EmbeddedId
    private SelectCourseKey key;
}
