package TrenVerdeApp.TrenVerdeApp.service;

import TrenVerdeApp.TrenVerdeApp.entity.Rol;
import TrenVerdeApp.TrenVerdeApp.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IUsuarioService {
    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Rol> roles);

    public List<Usuario> listarUsuarios();
    ResponseEntity<?> guardarUsuario(Usuario usuario, String tipoRol);
    ResponseEntity<?> guardarAdmin(Usuario usuario, String tipoRol);
    public Usuario buscarUsuarioPorId(Integer id);
    public Usuario buscarUsuarioPorUsername(String username);
    public void eliminarUsuario(Long id);
    ResponseEntity<?> actualizarUsuario(Long id, Usuario usuarioActualizado);

}
