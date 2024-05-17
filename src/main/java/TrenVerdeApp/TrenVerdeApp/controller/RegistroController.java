package TrenVerdeApp.TrenVerdeApp.controller;

import TrenVerdeApp.TrenVerdeApp.entity.Registro;
import TrenVerdeApp.TrenVerdeApp.service.IRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private IRegistroService registroService;

    @GetMapping()
    public ResponseEntity<List<Registro>> listarRegistro(){
        List<Registro> registros = registroService.listarRegistro();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Registro> agregarRegistro(@RequestBody Registro registro){
        if (registro.getTipoDocumentoEnum() == null || registro.getTipoPersonaEnum() == null || registro.getGeneroEnum() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Registro nuevoRegistro = registroService.guardarRegistro(registro);
        return new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idRegistro}")
    public ResponseEntity<Registro> buscarRegistroPorId(@PathVariable Long idRegistro){
        Registro registro = registroService.buscarRegistroPorId(idRegistro);
        if (registro != null){
            return new ResponseEntity<>(registro, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editar/{idRegistro}")
    public ResponseEntity<String> editarRegistro(@PathVariable Long idRegistro, @RequestBody Registro registro){
        if (registro.getTipoDocumentoEnum() == null || registro.getTipoPersonaEnum() == null || registro.getGeneroEnum() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo documento, genero y tipo persona son obligatorios.");
        }

        Registro registroExistente = registroService.actualizarRegistro(idRegistro, registro);

        if (registroExistente != null) {
            return ResponseEntity.ok("Registro actualizado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
    }

    @DeleteMapping("/eliminar/{idRegistro}")
    public ResponseEntity<Registro> eliminarRegistro(@PathVariable Long idRegistro){
        Registro registroExistente = registroService.buscarRegistroPorId(idRegistro);

        if (registroExistente != null){
            registroService.eliminarRegistro(idRegistro);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
