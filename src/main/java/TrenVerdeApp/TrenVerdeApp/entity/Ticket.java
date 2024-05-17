package TrenVerdeApp.TrenVerdeApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long ticketId;
    @Column(length = 80)
    private String origen;
    @Column(length = 80)
    private String destino;
    @Column(length = 80)
    private String clase;
    @Column(length = 80)
    private double precio;
    @Column(length = 80)
    private String estado;
    @Column(length = 80)
    private Long usuarioId; // ! Foranea

    // ! Creación de relación entre tablas
    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    // ! Constructor vacio
    public Ticket() {
    }

    public Ticket(Long ticketId, String origen, String destino, String clase,
                  double precio, String estado, Long usuarioId, Usuario usuario) {
        this.ticketId = ticketId;
        this.origen = origen;
        this.destino = destino;
        this.clase = clase;
        this.precio = precio;
        this.estado = estado;
        this.usuarioId = usuarioId;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}