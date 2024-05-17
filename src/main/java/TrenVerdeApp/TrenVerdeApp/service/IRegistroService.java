package TrenVerdeApp.TrenVerdeApp.service;

import TrenVerdeApp.TrenVerdeApp.entity.Registro;

import java.util.List;

public interface IRegistroService {
    public List<Registro> listarRegistro();
    public Registro guardarRegistro(Registro registro);
    public Registro buscarRegistroPorId(Long idRegistro);
    public Registro eliminarRegistro(Long idRegistro);
    public Registro actualizarRegistro(Long idRegistro, Registro registroActualizado);
}
