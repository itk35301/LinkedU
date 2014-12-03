/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author itmacuser
 */
import dao.universityDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.universityBean;


/**
 *
 * @author itk35301
 */
@Named(value = "SearchedUniversityProfileController")
@SessionScoped
public class SearchedUniversityProfileController implements Serializable {

    private universityBean uModel;
  
 

    public SearchedUniversityProfileController() {
        String uID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchedUni");
        universityDAOImpl ulogin = new universityDAOImpl();
        ArrayList uniCollection = ulogin.findByUID(uID);
        uModel = (universityBean)uniCollection.get(0);
    }
    
    public String goHome(){
        String stuID =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInStu");
        
        if(stuID != null){
            return "studentProfile?faces-redirect=true";
        }else
            return "universityProfile?faces-redirect=true";
    }

    public universityBean getUModel() {
        return uModel;
    }

    public void setUModel(universityBean uModel) {
        this.uModel = uModel;
    }
    
}