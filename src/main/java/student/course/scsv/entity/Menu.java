package student.course.scsv.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu {

    @Id
    private String userType;

    private String menuList;

    public Menu() {
    }

    public Menu(String userType, String menuList) {
        this.userType = userType;
        this.menuList = menuList;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMenuList() {
        return menuList;
    }

    public void setMenuList(String menuList) {
        this.menuList = menuList;
    }
}
