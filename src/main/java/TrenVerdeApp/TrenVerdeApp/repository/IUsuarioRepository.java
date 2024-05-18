package TrenVerdeApp.TrenVerdeApp.repository;

import TrenVerdeApp.TrenVerdeApp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
