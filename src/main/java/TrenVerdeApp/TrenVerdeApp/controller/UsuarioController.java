package TrenVerdeApp.TrenVerdeApp.controller;

import TrenVerdeApp.TrenVerdeApp.entity.Usuario;
import TrenVerdeApp.TrenVerdeApp.service.IUsuarioService;
import jakarta.validation.Valid;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Permitir solicitudes desde el frontend
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/registro_usuario")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(Objects.requireNonNull(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        try {
            ResponseEntity<?> nuevoUsuario = usuarioService.guardarUsuario(usuario, "USER");
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registro_administrador")
    public ResponseEntity<?> guardarAdmin(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(Objects.requireNonNull(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        try {
            ResponseEntity<?> nuevoUsuario = usuarioService.guardarAdmin(usuario, "ADMIN");
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer idUsuario) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Usuario> buscarUsuarioPorUsername(@PathVariable String username) {
        Usuario usuario = usuarioService.buscarUsuarioPorUsername(username);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{idUsuario}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer idUsuario, @Valid @RequestBody Usuario usuarioActualizado, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(Objects.requireNonNull(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        try {
            ResponseEntity<?> usuario = usuarioService.actualizarUsuario(Long.valueOf(idUsuario), usuarioActualizado);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long idUsuario) {
        Usuario usuarioExistente = usuarioService.buscarUsuarioPorId(Math.toIntExact(idUsuario));

        if (usuarioExistente != null) {
            usuarioService.eliminarUsuario(idUsuario);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario con el ID: " + idUsuario);
        }
    }
}