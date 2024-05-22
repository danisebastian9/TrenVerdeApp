package TrenVerdeApp.TrenVerdeApp.repository;

import TrenVerdeApp.TrenVerdeApp.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByTipoRol(Rol.TipoRol tipoRol);
}
