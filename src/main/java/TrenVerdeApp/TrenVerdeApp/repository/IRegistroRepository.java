package TrenVerdeApp.TrenVerdeApp.repository;

import TrenVerdeApp.TrenVerdeApp.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegistroRepository extends JpaRepository<Registro, Long> {
}
