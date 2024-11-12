package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;

import java.io.Serializable;
import java.util.List;
@Stateless
@LocalBean
public class SucursalBean extends AbstractDataPersistence<Sucursal> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    private EntityManager em;

    public SucursalBean() {
        super(Sucursal.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    //Obtner todas las sucursales
    public List<Sucursal> findAllSucursal(int first, int pageSize) {
        Query q = em.createNamedQuery("Sucursal.findAllSucursal");
        q.setFirstResult(first);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    //Obtener Sucursales Por ID
    public List<Sucursal> findSucursalBySucursalId(Integer IdSucursal, int first, int pageSize) {
        Query q = em.createNamedQuery("Sucursal.findByIdSucursal");
        q.setParameter("idSucursal", IdSucursal);
        q.setFirstResult(first);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

}
