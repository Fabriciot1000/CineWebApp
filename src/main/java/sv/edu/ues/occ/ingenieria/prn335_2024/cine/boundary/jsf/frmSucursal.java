//package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
//
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.view.ViewScoped;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;
//import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
//import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SucursalBean;
//import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;
//
//import java.io.Serializable;
//import java.util.stream.Collectors;
//
//@Named
//@ViewScoped
//public class frmSucursal extends AbstractFrm<Sucursal> implements Serializable {
//    @Inject
//    SucursalBean sBean;
//    @Inject
//    FacesContext fc;
//
//    public frmSucursal() {}
//
//    @Override
//    public String getTituloPagina() {
//        return "Sucursal";
//    }
//
//    @Override
//    public String nameRefresh(){
//        return "te-Sucursal";
//    }
//
//    @Override
//    public FacesContext getFacesContext() {
//        return fc;
//    }
//
//    @Override
//    public AbstractDataPersistence<Sucursal> getPersistence() {
//        return sBean;
//    }
//
//    @Override
//    public String Rowkey(Sucursal object){
//        if(object != null && object.getIdSucursal() != null){
//            return object.getIdSucursal().toString();
//        }
//        return null;
//    }
//
//    @Override
//    public Sucursal RowData(String rowKey) {
//        if(rowKey != null && this.modelo != null && this.modelo.getWrappedData() != null){
//            return this.modelo.getWrappedData().stream()
//                    .filter(r -> r.getIdSucursal().toString().equals(rowKey)).collect(Collectors.toList()).get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public void instanciarRegistro(){
//        this.registro = new Sucursal();
//    }
//}
