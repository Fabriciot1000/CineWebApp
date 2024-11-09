package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "reserva_detalle", schema = "public")
@NamedQueries({
        @NamedQuery(name = "ReservaDetalle.findAll", query = "SELECT r FROM ReservaDetalle r"),
        @NamedQuery(name = "ReservaDetalle.findByIdReservaDetalle", query = "SELECT r FROM ReservaDetalle r WHERE r.idReservaDetalle = :idReservaDetalle"),
        @NamedQuery(name = "ReservaDetalle.findByEstado", query = "SELECT r FROM ReservaDetalle r WHERE r.estado = :estado")})
public class ReservaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserva_detalle")
    private Long idReservaDetalle;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_asiento", referencedColumnName = "id_asiento")
    @ManyToOne
    private Asiento idAsiento;
    @JoinColumn(name = "id_reserva", referencedColumnName = "id_reserva")
    @ManyToOne
    private Reserva idReserva;
    @OneToMany(mappedBy = "idReservaDetalle")
    private List<FacturaDetalleSala> facturaDetalleSalaList;

    public ReservaDetalle() {
    }

    public ReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public Long getIdReservaDetalle() {
        return idReservaDetalle;
    }

    public void setIdReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }

    public List<FacturaDetalleSala> getFacturaDetalleSalaList() {
        return facturaDetalleSalaList;
    }

    public void setFacturaDetalleSalaList(List<FacturaDetalleSala> facturaDetalleSalaList) {
        this.facturaDetalleSalaList = facturaDetalleSalaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservaDetalle != null ? idReservaDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaDetalle)) {
            return false;
        }
        ReservaDetalle other = (ReservaDetalle) object;
        if ((this.idReservaDetalle == null && other.idReservaDetalle != null) || (this.idReservaDetalle != null && !this.idReservaDetalle.equals(other.idReservaDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.ReservaDetalle[ idReservaDetalle=" + idReservaDetalle + " ]";
    }

}