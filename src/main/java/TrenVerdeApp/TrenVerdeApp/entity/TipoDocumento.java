package TrenVerdeApp.TrenVerdeApp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "tipo_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipoDocumento")
    private Long idTipoDocumento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumentoEnum tipoDocumento;

    public TipoDocumento() {
    }

    public TipoDocumento(TipoDocumentoEnum tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumentoEnum getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public enum TipoDocumentoEnum {
        CEDULA_CIUDADANIA,
        TARJETA_IDENTIDAD,
        CEDULA_DE_EXTRANJERIA,
        PASAPORTE,
        NIT,
        NUMERO_UNICO_DE_IDENTIFICACION_PERSONAL
    }
}
