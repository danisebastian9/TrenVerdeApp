//package TrenVerdeApp.TrenVerdeApp.configuration;
//
//import TrenVerdeApp.TrenVerdeApp.entity.Rol;
//import TrenVerdeApp.TrenVerdeApp.entity.Usuario;
//import TrenVerdeApp.TrenVerdeApp.repository.IRolRepository;
//import TrenVerdeApp.TrenVerdeApp.service.IUsuarioService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class DataInitializer {
//
//    private final IUsuarioService usuarioService;
//    private final IRolRepository rolRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DataInitializer(IUsuarioService usuarioService, IRolRepository rolRepository, PasswordEncoder passwordEncoder) {
//        this.usuarioService = usuarioService;
//        this.rolRepository = rolRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void init() {
//        try {
//            // Verificar si ya existe un usuario con el nombre de usuario "admin"
//            Usuario usuarioExistente = usuarioService.buscarUsuarioPorUsername("admin");
//            if (usuarioExistente == null) {
//                // Crear un rol de administrador si no existe
//                Rol rolAdmin = new Rol("SUPERADMIN");
//                // Guardar el rol en la base de datos
//                Rol rolGuardado = rolRepository.save(rolAdmin);
//
//                // Si el rol se guardó correctamente, crear el usuario y asignarle el rol
//                if (rolGuardado != null) {
//                    // Creamos un nuevo usuario con rol de administrador
//                    Usuario admin = new Usuario("admin", passwordEncoder.encode("adminpassword"));
//                    // Asignar el rol de administrador al usuario
//                    Set<Rol> roles = new HashSet<>();
//                    roles.add(rolGuardado);
//                    admin.setRoles(roles);
//                    // Guardar el usuario en la base de datos
//                    usuarioService.guardarUsuario(admin);
//                } else {
//                    // Manejar el caso en el que no se pudo guardar el rol
//                    System.err.println("No se pudo guardar el rol de administrador");
//                }
//            }
//        } catch (Exception e) {
//            // Capturar y registrar cualquier excepción que ocurra durante la inicialización
//            e.printStackTrace();
//        }
//    }
//}
