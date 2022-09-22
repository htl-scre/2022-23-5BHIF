package at.htlstp.bhif5.persistence;

import at.htlstp.bhif5.domain.SchoolClass;
import at.htlstp.bhif5.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllBySchoolClass_Name(String name);
}
