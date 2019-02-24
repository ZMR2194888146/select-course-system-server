package student.course.scsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.course.scsv.entity.Adminisitrator;

public interface AdministatorRepository extends JpaRepository<Adminisitrator, Long> {
    Adminisitrator findAdminisitratorById(Long id);
}
