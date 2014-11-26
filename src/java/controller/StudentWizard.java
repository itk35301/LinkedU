package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;
 
@ManagedBean
@ViewScoped
public class StudentWizard implements Serializable {
 
     
    public String onFlowProcess(FlowEvent event) {
            return event.getNewStep();
    }

}
