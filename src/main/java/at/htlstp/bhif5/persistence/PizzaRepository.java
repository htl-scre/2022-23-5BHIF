package at.htlstp.bhif5.persistence;

import at.htlstp.bhif5.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
