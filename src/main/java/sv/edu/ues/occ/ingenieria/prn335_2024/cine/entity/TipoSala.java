package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_sala", schema = "public")
@NamedQueries({
        @NamedQuery(name = "TipoSala.findAll", query = "SELECT t FROM TipoSala t"),
        @NamedQuery(name = "TipoSala.findByIdTipoSala", query = "SELECT t FROM TipoSala t WHERE t.idTipoSala = :idTipoSala"),
        @NamedQuery(name = "TipoSala.findByNombre", query = "SELECT t FROM TipoSala t WHERE t.nombre = :nombre"),
        @NamedQuery(name = "TipoSala.findByActivo", query = "SELECT t FROM TipoSala t WHERE t.activo = :activo"),
        @NamedQuery(name = "TipoSala.findByComentarios", query = "SELECT t FROM TipoSala t WHERE t.comentarios = :comentarios"),
        @NamedQuery(name = "TipoSala.findByExpresionRegular", query = "SELECT t FROM TipoSala t WHERE t.expresionRegular = :expresionRegular")})
public class TipoSala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_sala")
    private Integer idTipoSala;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "expresion_regular")
    private String expresionRegular;
    @OneToMany(mappedBy = "idTipoSala")
    private List<SalaCaracteristica> salaCaracteristicaList;

    public TipoSala() {
    }

    public TipoSala(Integer idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public Integer getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(Integer idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public List<SalaCaracteristica> getSalaCaracteristicaList() {
        return salaCaracteristicaList;
    }

    public void setSalaCaracteristicaList(List<SalaCaracteristica> salaCaracteristicaList) {
        this.salaCaracteristicaList = salaCaracteristicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSala != null ? idTipoSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSala)) {
            return false;
        }
        TipoSala other = (TipoSala) object;
        if ((this.idTipoSala == null && other.idTipoSala != null) || (this.idTipoSala != null && !this.idTipoSala.equals(other.idTipoSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala[ idTipoSala=" + idTipoSala + " ]";
    }

}
