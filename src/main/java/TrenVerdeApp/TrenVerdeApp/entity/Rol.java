package TrenVerdeApp.TrenVerdeApp.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
    private String nombreRol;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<>();

    public Rol() {
    }

    public Rol(Long idRol, String nombreRol, Set<Usuario> usuarios) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.usuarios = usuarios;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.getRoles().add(this);
    }

    public void removeUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        usuario.getRoles().remove(this);
    }
}
