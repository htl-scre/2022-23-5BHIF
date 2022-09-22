package at.htlstp.bhif5.persistence;

import at.htlstp.bhif5.domain.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<SchoolClass, String> {

}
