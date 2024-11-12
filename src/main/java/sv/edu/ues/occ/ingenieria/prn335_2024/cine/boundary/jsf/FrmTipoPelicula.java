package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPago;

import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FrmTipoPelicula extends AbstractFrm<TipoPago> implements Serializable {

    @Inject
    TipoPagoBean tBean;
    @Inject
    FacesContext fc;
    @Override
    public void instanciarRegistro(){
        this.registro = new TipoPago();
        registro.setActivo(true);
    }

    public FrmTipoPelicula() {}

    @Override
    public String nameRefresh(){
        return "ar-TipoPago";
    }

    @Override
    public FacesContext getFacesContext() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<TipoPago> getPersistence(){
        return tBean;
    }

    @Override
    public String getIdByObject(TipoPago obj){
        if(obj.getIdTipoPago() != null){
            return obj.getIdTipoPago().toString();
        }
        return null;
    }

    @Override
    public TipoPago getObjectById(String id){
        if(id != null & modelo != null & modelo.getWrappedData() != null){
            return modelo.getWrappedData().stream()
                    .filter(r ->id.equals(r.getIdTipoPago().toString())).findFirst().
                    orElseGet(()->{
                        Logger.getLogger("No se ha encontrado el objecto");
                        return null;
                    });
        }
        return null;
    }

    @Override
    public void seleccionarFila(SelectEvent<TipoPago> event) {
        TipoPago filaSelect = event.getObject();
        FacesMessage mensaje = new FacesMessage("Tipo Pago seleccionado");
        fc.addMessage(null, mensaje);
        this.registro = filaSelect;
        this.estado = EstadosCRUD.MODIFICADO;
    }

    @Override
    public String getTituloPagina() {
        return "Tipo Pago";
    }
}