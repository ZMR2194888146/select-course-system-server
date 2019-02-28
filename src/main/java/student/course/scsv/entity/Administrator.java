package student.course.scsv.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Administrator {

    @Id
    private Long id;

    private String username;

    private String password;

    public Administrator() {
    }

    public Administrator(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Administrator(String password) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
