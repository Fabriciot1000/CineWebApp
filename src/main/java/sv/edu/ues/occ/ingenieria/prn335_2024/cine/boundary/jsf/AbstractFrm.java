package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.util.Lazy;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AbstractFrm<T> implements Serializable {

    public abstract AbstractDataPersistence<T> getPersistence();

    public abstract String Rowkey(T object);

    public abstract T RowData(String rowkey);

    public abstract void instanciarRegistro();
    public abstract String nameRefresh();

    public abstract FacesContext getFacesContext();

    //List<T> listaRegistro
    LazyDataModel<T> modelo;
    T registro = null;
    EstadosCRUD estado = EstadosCRUD.NINGUNO;

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
                    return Rowkey(object);
                }
                return null;
            }
            @Override
            public T getRowData(String rowkey) {
                if(rowkey != null){
                    return RowData(rowkey);
                }
                return null;
            }
        };
    }

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
            resultado = trBean.findRange(primero, tamanio);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }finally {
            if(resultado==null){
                resultado = Collections.EMPTY_LIST;
            }
        }
        return resultado;
    }
}
