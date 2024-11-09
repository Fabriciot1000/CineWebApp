package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "asiento", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Asiento.findAsientosBySala", query = "SELECT a FROM Asiento a WHERE a.idSala = :idSala"),
        @NamedQuery(name = "Asiento.findAll", query = "SELECT a FROM Asiento a"),
        @NamedQuery(name = "Asiento.findByIdAsiento", query = "SELECT a FROM Asiento a WHERE a.idAsiento = :idAsiento"),
        @NamedQuery(name = "Asiento.findByNombre", query = "SELECT a FROM Asiento a WHERE a.nombre = :nombre"),
        @NamedQuery(name = "Asiento.findByActivo", query = "SELECT a FROM Asiento a WHERE a.activo = :activo")})
public class Asiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asiento")
    private Long idAsiento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    @OneToMany(mappedBy = "idAsiento")
    private List<ReservaDetalle> reservaDetalleList;
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @ManyToOne
    private Sala idSala;
    @OneToMany(mappedBy = "idAsiento")
    private List<AsientoCaracteristica> asientoCaracteristicaList;

    public Asiento() {
    }

    public Asiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
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

    public List<ReservaDetalle> getReservaDetalleList() {
        return reservaDetalleList;
    }

    public void setReservaDetalleList(List<ReservaDetalle> reservaDetalleList) {
        this.reservaDetalleList = reservaDetalleList;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public List<AsientoCaracteristica> getAsientoCaracteristicaList() {
        return asientoCaracteristicaList;
    }

    public void setAsientoCaracteristicaList(List<AsientoCaracteristica> asientoCaracteristicaList) {
        this.asientoCaracteristicaList = asientoCaracteristicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsiento != null ? idAsiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asiento)) {
            return false;
        }
        Asiento other = (Asiento) object;
        if ((this.idAsiento == null && other.idAsiento != null) || (this.idAsiento != null && !this.idAsiento.equals(other.idAsiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento[ idAsiento=" + idAsiento + " ]";
    }

}
