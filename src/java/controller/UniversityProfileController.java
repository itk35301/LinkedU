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
import dao.universityDAO;
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
@Named(value = "UniversityProfileController")
@SessionScoped
public class UniversityProfileController implements Serializable {

    private universityBean uModel;
  
 

    public UniversityProfileController() {
        uModel = (universityBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInU");;
       
    }

    public universityBean getUModel() {
        return uModel;
    }

    public void setUModel(universityBean uModel) {
        this.uModel = uModel;
    }
    
    
    
}

