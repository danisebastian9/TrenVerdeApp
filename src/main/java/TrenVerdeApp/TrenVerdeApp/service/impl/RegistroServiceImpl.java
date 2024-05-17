package TrenVerdeApp.TrenVerdeApp.service.impl;

import TrenVerdeApp.TrenVerdeApp.entity.Registro;
import TrenVerdeApp.TrenVerdeApp.repository.IRegistroRepository;
import TrenVerdeApp.TrenVerdeApp.service.IRegistroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class RegistroServiceImpl implements IRegistroService {

    @Autowired
    private IRegistroRepository iRegistroRepository;

    private List<Registro> registro = new ArrayList<>();

    @Override
    public List<Registro> listarRegistro() {
        return registro = iRegistroRepository.findAll();
    }

    @Override
    public Registro guardarRegistro(Registro registro) {
        return iRegistroRepository.save(registro);
    }

    @Override
    public Registro buscarRegistroPorId(Long idRegistro) {
        Registro registro = iRegistroRepository.findById(idRegistro).orElse(null);
        return registro;
    }

    @Override
    public Registro eliminarRegistro(Long idRegistro) {
        iRegistroRepository.deleteById(idRegistro);
        return null;
    }

    public Registro actualizarRegistro(Long idRegistro, Registro registroActualizado) {
        Registro registroExistente = iRegistroRepository.findById(idRegistro).orElse(null);

        if (registroExistente != null) {
            registroExistente.setNumeroDocumento(registroActualizado.getNumeroDocumento());
            registroExistente.setPrimerNombre(registroActualizado.getPrimerNombre());
            registroExistente.setSegundoNombre(registroActualizado.getSegundoNombre());
            registroExistente.setPrimerApellido(registroActualizado.getPrimerApellido());
            registroExistente.setSegundoApellido(registroActualizado.getSegundoApellido());
            registroExistente.setEmail(registroActualizado.getEmail());
            registroExistente.setTelefono(registroActualizado.getTelefono());
            registroExistente.setFechaNacimiento(registroActualizado.getFechaNacimiento());
            registroExistente.setDireccion(registroActualizado.getDireccion());
            registroExistente.setTipoDocumento(registroActualizado.getTipoDocumento());
            registroExistente.setGenero(registroActualizado.getGenero());
            registroExistente.setTipoPersona(registroActualizado.getTipoPersona());
            registroExistente.setTipoDocumentoEnum(registroActualizado.getTipoDocumentoEnum());
            registroExistente.setGeneroEnum(registroActualizado.getGeneroEnum());
            registroExistente.setTipoPersonaEnum(registroActualizado.getTipoPersonaEnum());
            registroExistente.setPassword(registroActualizado.getPassword());
        } else {
            // crear excepcion para cuando no exista registro
            return null;
        }
        return iRegistroRepository.save(registroExistente);
    }
}
