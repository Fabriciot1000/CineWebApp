package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reserva", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
        @NamedQuery(name = "Reserva.findByIdReserva", query = "SELECT r FROM Reserva r WHERE r.idReserva = :idReserva"),
        @NamedQuery(name = "Reserva.findByFechaReserva", query = "SELECT r FROM Reserva r WHERE r.fechaReserva = :fechaReserva"),
        @NamedQuery(name = "Reserva.findByEstado", query = "SELECT r FROM Reserva r WHERE r.estado = :estado"),
        @NamedQuery(name = "Reserva.findByObservaciones", query = "SELECT r FROM Reserva r WHERE r.observaciones = :observaciones")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserva")
    private Long idReserva;
    @Column(name = "fecha_reserva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReserva;
    @Column(name = "estado")
    private String estado;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "idReserva")
    private List<ReservaDetalle> reservaDetalleList;
    @JoinColumn(name = "id_programacion", referencedColumnName = "id_programacion")
    @ManyToOne
    private Programacion idProgramacion;
    @JoinColumn(name = "id_tipo_reserva", referencedColumnName = "id_tipo_reserva")
    @ManyToOne
    private TipoReserva idTipoReserva;

    public Reserva() {
    }

    public Reserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ReservaDetalle> getReservaDetalleList() {
        return reservaDetalleList;
    }

    public void setReservaDetalleList(List<ReservaDetalle> reservaDetalleList) {
        this.reservaDetalleList = reservaDetalleList;
    }

    public Programacion getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Programacion idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public TipoReserva getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(TipoReserva idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva[ idReserva=" + idReserva + " ]";
    }

}