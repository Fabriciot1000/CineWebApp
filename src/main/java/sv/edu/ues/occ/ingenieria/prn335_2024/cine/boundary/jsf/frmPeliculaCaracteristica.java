package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;

import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ViewScoped
public class frmPeliculaCaracteristica extends AbstractFrm<PeliculaCaracteristica> implements Serializable {

    @Inject
    PeliculaCaracteristicaBean pcBean;
    @Inject
    FacesContext fc;

    @Override
    public void instanciarRegistro(){
        this.registro = new PeliculaCaracteristica();
    }

    public frmPeliculaCaracteristica(){}

    @Override
    public String nameRefresh(){
        return "ar-PeliculaCaracteristica";
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<PeliculaCaracteristica> getPersistence(){
        return pcBean;
    }

    @Override
    public String getIdByObject(PeliculaCaracteristica obj){
        if(obj.getIdPelicula() != null){
            return obj.getIdPelicula().toString();
        }
        return null;
    }

    @Override
    public PeliculaCaracteristica getObjectById(String id){
        if(id != null & modelo != null & modelo.getWrappedData() != null){
            return modelo.getWrappedData().stream()
                    .filter(r ->id.equals(r.getIdPeliculaCaracteristica().toString())).findFirst().
                    orElseGet(()->{
                        Logger.getLogger("No se ha encontrado el objecto");
                        return null;
                    });
        }
        return null;
    }

    @Override
    public void seleccionarFila(SelectEvent<PeliculaCaracteristica> event) {
        PeliculaCaracteristica filaSelect = event.getObject();
        FacesMessage mensaje = new FacesMessage("Pelicula Caracteristica "+ filaSelect.getValor() + " seleccionado");
        fc.addMessage(null, mensaje);
        this.registro = filaSelect;
        this.estado = EstadosCRUD.MODIFICADO;
    }

    @Override
    public String getTituloPagina() {
        return "Pelicula Caracteristica";
    }
}
