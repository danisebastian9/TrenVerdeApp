package TrenVerdeApp.TrenVerdeApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Long idRegistro;
    @Column(nullable = false, length = 20)
    @NotNull(message = "El número de documento es obligatorio")
    @Size(min = 8, message = "El número de documento debe tener al menos 8 caracteres")
    private Long numeroDocumento;
    @Column(nullable = false, length = 50)
    @NotNull(message = "El Nombre es obligatorio")
    @Size(min = 2, message = "El Nombre debe tener al menos 2 caracteres")
    private String primerNombre;
    @Column(length = 50)
    private String segundoNombre;
    @Column(nullable = false, length = 50)
    @NotNull(message = "El Apellido es obligatorio")
    @Size(min = 2, message = "El Apellido debe tener al menos 2 caracteres")
    private String primerApellido;
    @Column(length = 50)
    private String segundoApellido;
    @Column(length = 30)
    private String email;
    @Column(nullable = false, length = 30)
    @NotNull(message = "El número de teléfono es obligatorio")
    @Size(min = 10, message = "El número de teléfono debe tener al menos 10 caracteres")
    private Long telefono;
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
    @Column(length = 50)
    private String direccion;
    @Column(nullable = false, length = 25)
    @NotNull(message = "El nombre de usuario es obligatorio")
    @Size(min = 5, message = "El nombre de usuario debe tener al menos 5 caracteres")
    private String userName;
    @Column(nullable = false, length = 25)
    @NotNull(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    // ! Creación de relación entre tablas

    @ManyToOne
    @JoinColumn(name = "fk_id_tipoDocumento", referencedColumnName = "id_tipoDocumento")
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "fk_id_tipoPersona", referencedColumnName = "id_tipoPersona")
    private TipoPersona tipoPersona;

    @ManyToOne
    @JoinColumn(name = "fk_id_genero", referencedColumnName = "id_genero")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;


    // ! Enumeraciones
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento.TipoDocumentoEnum tipoDocumentoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_persona")
    private TipoPersona.TipoPersonaEnum tipoPersonaEnum;
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Genero.GeneroEnum generoEnum;

    // ! Constructor vacio
    public Registro() {
    }

    // ! Constructor con parametros
    public Registro(Long registroId, Long numeroDocumento, String primerNombre,
                    String segundoNombre, String primerApellido, String segundoApellido,
                    String email, Long telefono, LocalDate fechaNacimiento, String direccion,
                    Long usuarioId, Usuario usuario, TipoDocumento tipoDocumento,
                    TipoPersona tipoPersona, Genero genero, String userName, String password) {
        this.idRegistro = registroId;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.usuario = usuario;
        this.tipoDocumento = tipoDocumento;
        this.tipoPersona = tipoPersona;
        this.genero = genero;
        this.userName = userName;
        this.password = password;
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public TipoDocumento.TipoDocumentoEnum getTipoDocumentoEnum() {
        return tipoDocumentoEnum;
    }

    public void setTipoDocumentoEnum(TipoDocumento.TipoDocumentoEnum tipoDocumentoEnum) {
        this.tipoDocumentoEnum = tipoDocumentoEnum;
    }

    public TipoPersona.TipoPersonaEnum getTipoPersonaEnum() {
        return tipoPersonaEnum;
    }

    public void setTipoPersonaEnum(TipoPersona.TipoPersonaEnum tipoPersonaEnum) {
        this.tipoPersonaEnum = tipoPersonaEnum;
    }

    public Genero.GeneroEnum getGeneroEnum() {
        return generoEnum;
    }

    public void setGeneroEnum(Genero.GeneroEnum generoEnum) {
        this.generoEnum = generoEnum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

