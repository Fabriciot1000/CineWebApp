package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPago;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;

import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ViewScoped
public class frmTipoPelicula extends AbstractFrm<TipoPelicula> implements Serializable {
    @Inject
    TipoPeliculaBean tpBean;
    @Inject
    FacesContext fc;
    @Override
    public void instanciarRegistro(){
        this.registro = new TipoPelicula();
        registro.setActivo(true);
    }

    public frmTipoPelicula() {}

    @Override
    public String nameRefresh(){
        return "ar-TipoPelicula";
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<TipoPelicula> getPersistence(){
        return tpBean;
    }

    @Override
    public String getIdByObject(TipoPelicula obj){
        if(obj.getIdTipoPelicula() != null){
            return obj.getIdTipoPelicula().toString();
        }
        return null;
    }

    @Override
    public TipoPelicula getObjectById(String id){
        if(id != null & modelo != null & modelo.getWrappedData() != null){
            return modelo.getWrappedData().stream()
                    .filter(r ->id.equals(r.getIdTipoPelicula().toString())).findFirst().
                    orElseGet(()->{
                        Logger.getLogger("No se ha encontrado el objecto");
                        return null;
                    });
        }
        return null;
    }

    @Override
    public void seleccionarFila(SelectEvent<TipoPelicula> event) {
        TipoPelicula filaSelect = event.getObject();
        FacesMessage mensaje = new FacesMessage("Tipo Pago seleccionado");
        fc.addMessage(null, mensaje);
        this.registro = filaSelect;
        this.estado = EstadosCRUD.MODIFICADO;
    }

    @Override
    public String getTituloPagina() {
        return "Tipo Pelicula";
    }
}
