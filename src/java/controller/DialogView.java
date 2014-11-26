package controller;
 
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
 
@ManagedBean
public class DialogView {
 
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

}