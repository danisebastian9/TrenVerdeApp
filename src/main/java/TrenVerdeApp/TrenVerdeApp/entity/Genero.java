package TrenVerdeApp.TrenVerdeApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Long idGenero;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private GeneroEnum genero;

    public Genero() {
    }

    public Genero(GeneroEnum genero) {
        this.genero = genero;
    }

    public Long getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Long idGenero) {
        this.idGenero = idGenero;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public enum GeneroEnum {
        HOMBRE,
        MUJER,
        OTRO
    }
}
