/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.studentDAO;
import dao.studentDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.studentBean;


/**
 *
 * @author itk35301
 */
@Named(value = "StudentProfileController")
@SessionScoped
public class StudentProfileController implements Serializable {

    private studentBean stuModel;
  
 

    public StudentProfileController() {
        stuModel = new studentBean();
       
    }
    
    
    
}
