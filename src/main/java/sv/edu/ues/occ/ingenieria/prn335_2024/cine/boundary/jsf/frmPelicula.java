package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;


import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ViewScoped
public class frmPelicula extends AbstractFrm<Pelicula> implements Serializable {

    @Inject
    PeliculaBean pBean;
    @Inject
    FacesContext fc;

    @Override
    public void instanciarRegistro(){
        this.registro = new Pelicula();
    }

    public frmPelicula(){}

    @Override
    public String nameRefresh(){
        return "ar-TipoPago";
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<Pelicula> getPersistence(){
        return pBean;
    }

    @Override
    public String getIdByObject(Pelicula obj){
        if(obj.getIdPelicula() != null){
            return obj.getIdPelicula().toString();
        }
        return null;
    }

    @Override
    public Pelicula getObjectById(String id){
        if(id != null & modelo != null & modelo.getWrappedData() != null){
            return modelo.getWrappedData().stream()
                    .filter(r ->id.equals(r.getIdPelicula().toString())).findFirst().
                    orElseGet(()->{
                        Logger.getLogger("No se ha encontrado el objecto");
                        return null;
                    });
        }
        return null;
    }

    @Override
    public void seleccionarFila(SelectEvent<Pelicula> event) {
        Pelicula filaSelect = event.getObject();
        FacesMessage mensaje = new FacesMessage("Pelicula "+ filaSelect.getNombre() + " seleccionado");
        fc.addMessage(null, mensaje);
        this.registro = filaSelect;
        this.estado = EstadosCRUD.MODIFICADO;
    }

    @Override
    public String getTituloPagina() {
        return "Pelicula";
    }


}
