package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;

import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class AsientoBean extends AbstractDataPersistence<Asiento> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AsientoBean() {
        super(Asiento.class);
    }

    //Buscar Asientos Por sala----> Devolver todos los asientos a una sala especifica
    public List<Asiento> findAsientosBySala(Integer idSala) {
        Query q = em.createNamedQuery("Asiento.findAsientosBySala");
        q.setParameter("idSala", idSala);
        return q.getResultList();
    }


}
