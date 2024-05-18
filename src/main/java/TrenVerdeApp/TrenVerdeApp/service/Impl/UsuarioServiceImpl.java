package TrenVerdeApp.TrenVerdeApp.service.Impl;


import TrenVerdeApp.TrenVerdeApp.entity.Rol;
import TrenVerdeApp.TrenVerdeApp.entity.Usuario;
import TrenVerdeApp.TrenVerdeApp.repository.IUsuarioRepository;
import TrenVerdeApp.TrenVerdeApp.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
                usuario.getPassword(), mapRolesToAuthorities(usuario.getRoles()));
    }
   @Override
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombreRol())).collect(Collectors.toList());
    }
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        return optionalUsuario.orElse(null);
    }
    @Override
    public Usuario buscarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }
    @Override
    public void eliminarUsuario(Long idUsuario) {
        usuarioRepository.deleteById(Math.toIntExact(idUsuario));
    }
    @Override
    public Usuario actualizarUsuario(Long idUsuario, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(Math.toIntExact(idUsuario)).orElse(null);
        if (usuarioExistente != null) {
            // Actualizar los campos del usuario existente con los del usuario actualizado
            usuarioExistente.setUsername(usuarioActualizado.getUsername());
            usuarioExistente.setPassword(usuarioActualizado.getPassword());
            // Guardar y devolver el usuario actualizado
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }
}

