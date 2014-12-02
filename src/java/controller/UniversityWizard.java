package controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
 
@Named(value = "UniversityWizard")
@ViewScoped
public class UniversityWizard implements Serializable {
 
     
    public String onFlowProcess(FlowEvent event) {
            return event.getNewStep();
    }

}
