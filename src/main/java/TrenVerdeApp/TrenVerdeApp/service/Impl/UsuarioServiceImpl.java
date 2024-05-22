package TrenVerdeApp.TrenVerdeApp.service.Impl;


import TrenVerdeApp.TrenVerdeApp.entity.Rol;
import TrenVerdeApp.TrenVerdeApp.entity.Usuario;
import TrenVerdeApp.TrenVerdeApp.repository.IUsuarioRepository;
import TrenVerdeApp.TrenVerdeApp.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
                usuario.getPassword(), mapRolesToAuthorities(usuario.getRoles()));
    }

   @Override
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTipoRol().name())).collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public ResponseEntity<?> guardarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        try {
            Usuario nuevoUsuario = usuarioRepository.save(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("El nombre de usuario ya está en uso. Por favor, elija otro nombre de usuario.", HttpStatus.CONFLICT);
        }
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
    public ResponseEntity<?> actualizarUsuario(Long idUsuario, Usuario usuarioActualizado) {
        try {
            Usuario usuario = usuarioRepository.findById(Math.toIntExact(idUsuario)).orElse(null);
            if (usuario != null) {
                usuario.setUsername(usuarioActualizado.getUsername());
                usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
                return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontró el usuario con el ID: " + idUsuario + " o el nombre de usuario: " + usuarioActualizado.getUsername() + " ya existe", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("El nombre de usuario ya está en uso. Por favor, elija otro nombre de usuario.", HttpStatus.CONFLICT);
        }
//            Usuario usuarioExistente = usuarioRepository.findById(Math.toIntExact(idUsuario)).orElse(null);
//            if (usuarioExistente != null) {
//                usuarioExistente.setUsername(usuarioActualizado.getUsername());
//                usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
//                return new ResponseEntity<>(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
//            }
//                return new ResponseEntity<>("El nombre de usuario ya está en uso. Por favor, elija otro nombre de usuario.", HttpStatus.CONFLICT);
//
    }
}

