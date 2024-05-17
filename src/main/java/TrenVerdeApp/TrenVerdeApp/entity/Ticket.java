package TrenVerdeApp.TrenVerdeApp.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long ticketId;
    @Column(nullable = false, length = 80)
    @NotNull(message = "El campo no puede estar vacio")
    private String origen;
    @Column(nullable = false, length = 80)
    @NotNull(message = "El campo no puede estar vacio")
    private String destino;
    @Column(length = 80)
    private String clase;
    @Column(length = 80)
    @NotNull(message = "El campo no puede estar vacio")
    private double precio;
    @Column(length = 80)
    private  String estado;

    // ! Creación de relación entre tablas
    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "id_usuario")
    // Nombre de la columna en la tabla 'ticket' que hace referencia al usuario
    private Usuario usuario;

    // ! Constructor vacio
    public Ticket() {
    }

    public Ticket(Long ticketId, String origen, String destino, String clase, double precio, String estado, Usuario usuario) {
        this.ticketId = ticketId;
        this.origen = origen;
        this.destino = destino;
        this.clase = clase;
        this.precio = precio;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}