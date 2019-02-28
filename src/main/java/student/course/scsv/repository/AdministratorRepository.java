package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findAdministratorById(Long id);

    /**
     * 通过用户名查找管理员
     * @param userName 管理员登录名
     * @return Adminisitrator
     */
    Administrator findAdministratorByUsername(String userName);
}
