package student.course.scsv.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Adminisitrator {

    @Id
    private Long id;

    private String password;

    public Adminisitrator() {
    }

    public Adminisitrator(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Adminisitrator(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
