package TrenVerdeApp.TrenVerdeApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Rol")
@JsonIgnoreProperties({"usuarios"})
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_rol")
    private TipoRol tipoRol;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<Usuario> usuarios = new HashSet<>();

    public enum TipoRol {
        SUPERADMIN,
        ADMIN,
        USER
    }

    public Rol() {
    }

    public Rol(TipoRol tipoRol) {
        this.tipoRol = tipoRol;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public TipoRol getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(TipoRol tipoRol) {
        this.tipoRol = tipoRol;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}