package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "asiento_caracteristica", schema = "public")
@NamedQueries({
        @NamedQuery(name = "AsientoCaracteristica.findAll", query = "SELECT a FROM AsientoCaracteristica a"),
        @NamedQuery(name = "AsientoCaracteristica.findByIdAsientoCaracteristica", query = "SELECT a FROM AsientoCaracteristica a WHERE a.idAsientoCaracteristica = :idAsientoCaracteristica"),
        @NamedQuery(name = "AsientoCaracteristica.findByValor", query = "SELECT a FROM AsientoCaracteristica a WHERE a.valor = :valor")})
public class AsientoCaracteristica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asiento_caracteristica")
    private Long idAsientoCaracteristica;
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "id_asiento", referencedColumnName = "id_asiento")
    @ManyToOne
    private Asiento idAsiento;
    @JoinColumn(name = "id_tipo_asiento", referencedColumnName = "id_tipo_asiento")
    @ManyToOne
    private TipoAsiento idTipoAsiento;

    public AsientoCaracteristica() {
    }

    public AsientoCaracteristica(Long idAsientoCaracteristica) {
        this.idAsientoCaracteristica = idAsientoCaracteristica;
    }

    public Long getIdAsientoCaracteristica() {
        return idAsientoCaracteristica;
    }

    public void setIdAsientoCaracteristica(Long idAsientoCaracteristica) {
        this.idAsientoCaracteristica = idAsientoCaracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    public TipoAsiento getIdTipoAsiento() {
        return idTipoAsiento;
    }

    public void setIdTipoAsiento(TipoAsiento idTipoAsiento) {
        this.idTipoAsiento = idTipoAsiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsientoCaracteristica != null ? idAsientoCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsientoCaracteristica)) {
            return false;
        }
        AsientoCaracteristica other = (AsientoCaracteristica) object;
        if ((this.idAsientoCaracteristica == null && other.idAsientoCaracteristica != null) || (this.idAsientoCaracteristica != null && !this.idAsientoCaracteristica.equals(other.idAsientoCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.cine.entity.AsientoCaracteristica[ idAsientoCaracteristica=" + idAsientoCaracteristica + " ]";
    }

}