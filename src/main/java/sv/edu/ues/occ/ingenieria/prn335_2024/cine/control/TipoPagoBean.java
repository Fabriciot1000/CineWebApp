package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPago;

import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class TipoPagoBean extends AbstractDataPersistence<TipoPago> implements Serializable{

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public TipoPagoBean() {
        super(TipoPago.class);
    }


}
