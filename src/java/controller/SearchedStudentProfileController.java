/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.studentDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.studentBean;


/**
 *
 * @author itk35301
 */
@Named(value = "SearchedStudentProfileController")
@RequestScoped
public class SearchedStudentProfileController implements Serializable {

    private studentBean stuModel;
  
 

    public SearchedStudentProfileController() {
        studentDAOImpl stulogin = new studentDAOImpl();
        String  stuID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchedStu");
        System.out.print("The sessionID passed in searched is " + stuID);
        ArrayList studentCollection = stulogin.findBySTUID(stuID);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("searchedStu");
        stuModel = (studentBean)studentCollection.get(0);
       
    }

    public String goHome(){
        String stuID =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInStu");
        
        if(stuID != null){
            return "studentProfile?faces-redirect=true";
        }else
            return "universityProfile?faces-redirect=true";
    }
    
    public studentBean getStuModel() {
        return stuModel;
    }

    public void setStuModel(studentBean stuModel) {
        this.stuModel = stuModel;
    }
    
    
    
}
