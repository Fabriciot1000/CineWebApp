package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@RequestScoped
public class HelloWorldController implements Serializable {
    private String hello = "Hello World!";
    public String getHello() {
        return hello;
    }

}
