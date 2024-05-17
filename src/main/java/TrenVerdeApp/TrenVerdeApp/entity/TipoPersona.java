package TrenVerdeApp.TrenVerdeApp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "tipo_persona")
public class TipoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipoPersona")
    private Long idTipoPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_persona")
    private TipoPersonaEnum tipoPersona;

    public TipoPersona() {
    }

    public TipoPersona(TipoPersonaEnum tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Long getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(Long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public TipoPersonaEnum getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersonaEnum tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public enum TipoPersonaEnum {
        PERSONA_NATURAL,
        PERSONA_JURIDICA
    }
}
