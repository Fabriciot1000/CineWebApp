package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import jakarta.faces.event.ActionEvent;
import org.primefaces.util.Lazy;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AbstractFrm<T> implements Serializable {

    public abstract AbstractDataPersistence<T> getPersistence();

    //public abstract String Rowkey(T object);

   // public abstract T RowData(String rowkey);

    public abstract void instanciarRegistro();
    public abstract String nameRefresh();

    public abstract FacesContext getFacesContext();

    public abstract String getIdByObject(T object);
    public abstract T getObjectById(String id);
    public abstract void seleccionarFila(SelectEvent<T> event);

    public abstract String getTituloPagina();
    private int registroPorPagina = 10; // Número de registros por página, puedes cambiarlo al valor deseado.


    //List<T> listaRegistro
    EstadosCRUD estado = EstadosCRUD.NINGUNO;
    LazyDataModel<T> modelo;
    T registro;
    List<T> registros;



    @PostConstruct
    public void inicializar() {
        inicializarRegistros();
    }

    //Las instancias de una clase que se inyecta no es accesible asi que se necesita el postconstruct
    //solo puede haber uno en la clase
    public void inicializarRegistros() {
        //this.listaRegistro = getDataAcces().findRange(0, 100000000);

        //Modelo de primeFaces para mostrar datos
        this.modelo = new LazyDataModel<T>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return contar();
            }
            @Override
            public List<T> load(int i, int i1, Map<String, SortMeta> map, Map<String, FilterMeta> map1){
                return cargarDatos(i, i1);
            }
            @Override
            public String getRowKey(T object) {
                if(object != null){
                    return getIdByObject(object);
                }
                return null;
            }
            @Override
            public T getRowData(String rowkey) {
                if(rowkey != null){
                    return getObjectById(rowkey);
                }
                return null;
            }
        };
    }

    public void btnCancelarHandler(ActionEvent ae){
        this.registro = null;
        this.estado = EstadosCRUD.NINGUNO;
    }

    public void btnNuevoHandler(ActionEvent ae){
        instanciarRegistro();
        this.estado = EstadosCRUD.NUEVO;
    }

    public void btnGuardarHandler(ActionEvent ae){
        FacesMessage mensaje = null;
        try{
            AbstractDataPersistence<T> trBean = getPersistence();
            trBean.create(registro);
            this.estado = EstadosCRUD.NINGUNO;
            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados exitosamente", null);
            getFacesContext().addMessage(null, mensaje);
        }catch (Exception ex){
            Logger.getLogger(AbstractFrm.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", ex.getMessage());
            getFacesContext().addMessage(null, mensaje);
        }
        this.registro = null;
    }

    public void btnModificarHandler(ActionEvent ae){
        T modify = null;
        FacesMessage mensaje = null;
        try{
            AbstractDataPersistence<T> trBean = getPersistence();
            modify = trBean.update(registro);
        }catch (Exception ex){
            Logger.getLogger(AbstractFrm.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(modify != null){
            this.estado = EstadosCRUD.NINGUNO;
            this.registro = null;
            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos modificados exitosamente", null);
        }else{
            mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos no existe", null);
        }
        getFacesContext().addMessage(null, mensaje);
    }

    public void btnEliminarHandler(ActionEvent ae){
        FacesMessage mensaje = null;
        try {
            AbstractDataPersistence<T> trBean = getPersistence();
            trBean.delete(registro);
            this.estado = EstadosCRUD.NINGUNO;
            this.registro = null;
            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", null);
            getFacesContext().addMessage(null, mensaje);
        }catch (Exception ex){
            Logger.getLogger(AbstractFrm.class.getName()).log(Level.SEVERE, null, ex);
            mensaje =  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar", ex.getMessage());
            getFacesContext().addMessage(null, mensaje);
        }
    }

    public void seleccionarRegistro(){
        this.estado = EstadosCRUD.NINGUNO;
    }

    public void btnSeleccionHandler(ActionEvent ae){
        Integer id = (Integer) ae.getComponent().getAttributes().get("id");
        if(id != null){
            this.registro = getPersistence().findById(id);
        }
    }
    //Filas del entity metodo count
    public int contar(){
        int resultado = 0;
        AbstractDataPersistence<T> trBean = null;
        try {
            trBean = getPersistence();
            return trBean.count();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return resultado;
    }

    public List<T> cargarDatos(int primero, int tamanio){
        List<T> resultado = null;
        try {
            AbstractDataPersistence<T> trBean = getPersistence();
            resultado = trBean.findRange(primero, registroPorPagina); // Usa registroPorPagina como el tamaño de la página.
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if(resultado == null) {
                resultado = Collections.emptyList();
            }
        }
        return resultado;
    }


    public List<T> getUpdateList() {
        AbstractDataPersistence<T> trBean = getPersistence();
        List<T> list;
        int max = trBean.count();
        try{
            list = trBean.findRange(0, max);
            return list;
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public EstadosCRUD getEstado() {
        return estado;
    }

    public void setEstado(EstadosCRUD estado) {
        this.estado = estado;
    }

    public int getRegistroPorPagina() {
        return registroPorPagina;
    }

    public void setRegistroPorPagina(int registroPorPagina) {
        this.registroPorPagina = registroPorPagina;
    }
}
