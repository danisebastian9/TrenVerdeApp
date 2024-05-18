package TrenVerdeApp.TrenVerdeApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(nullable = false, length = 20)
    @NotNull(message = "El nombre de usuario es obligatorio")
    @Size(min = 8, message = "El nombre de usuario debe tener al menos 8 caracteres")
    private String username;
    @NotNull(message = "La contraseña es obligatoria")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioRol",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();


    public Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return idUsuario;
    }

    public void setId(Long id) {
        this.idUsuario = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}

