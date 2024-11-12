package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPago;

import java.io.Serializable;

@Stateless
@LocalBean
public class PeliculaCaracteristicaBean extends AbstractDataPersistence<PeliculaCaracteristica> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public PeliculaCaracteristicaBean() {
        super(PeliculaCaracteristica.class);
    }


}
