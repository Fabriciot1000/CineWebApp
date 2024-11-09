package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "factura_detalle_producto")
@NamedQueries({
        @NamedQuery(name = "FacturaDetalleProducto.findAll", query = "SELECT f FROM FacturaDetalleProducto f"),
        @NamedQuery(name = "FacturaDetalleProducto.findByIdFacturaDetalleProducto", query = "SELECT f FROM FacturaDetalleProducto f WHERE f.idFacturaDetalleProducto = :idFacturaDetalleProducto"),
        @NamedQuery(name = "FacturaDetalleProducto.findByMonto", query = "SELECT f FROM FacturaDetalleProducto f WHERE f.monto = :monto")})
public class FacturaDetalleProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_factura_detalle_producto")
    private Long idFacturaDetalleProducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private BigDecimal monto;
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    @ManyToOne
    private Factura idFactura;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne
    private Producto idProducto;

    public FacturaDetalleProducto() {
    }

    public FacturaDetalleProducto(Long idFacturaDetalleProducto) {
        this.idFacturaDetalleProducto = idFacturaDetalleProducto;
    }

    public Long getIdFacturaDetalleProducto() {
        return idFacturaDetalleProducto;
    }

    public void setIdFacturaDetalleProducto(Long idFacturaDetalleProducto) {
        this.idFacturaDetalleProducto = idFacturaDetalleProducto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacturaDetalleProducto != null ? idFacturaDetalleProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaDetalleProducto)) {
            return false;
        }
        FacturaDetalleProducto other = (FacturaDetalleProducto) object;
        if ((this.idFacturaDetalleProducto == null && other.idFacturaDetalleProducto != null) || (this.idFacturaDetalleProducto != null && !this.idFacturaDetalleProducto.equals(other.idFacturaDetalleProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.FacturaDetalleProducto[ idFacturaDetalleProducto=" + idFacturaDetalleProducto + " ]";
    }

}
