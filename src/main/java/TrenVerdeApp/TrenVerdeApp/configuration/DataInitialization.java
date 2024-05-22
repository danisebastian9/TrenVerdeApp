package TrenVerdeApp.TrenVerdeApp.configuration;

import TrenVerdeApp.TrenVerdeApp.entity.Rol;
import TrenVerdeApp.TrenVerdeApp.entity.Rol.TipoRol;
import TrenVerdeApp.TrenVerdeApp.repository.IRolRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class DataInitialization {

    @Autowired
    private IRolRepository rolRepository;

    @PostConstruct
    public void initData() {
        // Verifica si los tipos de roles existen en la base de datos
        if (rolRepository.count() == 0) {
            // Si no existen, crea los tipos de roles
            Arrays.stream(Rol.TipoRol.values())
                    .forEach(tipoRol -> rolRepository.save(new Rol(tipoRol)));
        }
    }
}
