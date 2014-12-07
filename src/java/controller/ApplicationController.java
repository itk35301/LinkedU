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
import dao.applicationDAOImpl;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import model.application;

 
@Named(value = "ApplicationController")
@SessionScoped
public class ApplicationController implements Serializable {
     
    private application app;

    /**
     * @return the app
     */
    public ApplicationController(){
        app = new application();
    }
    public application getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(application app) {
        this.app = app;
    }
    
    public void Apply(){
        applicationDAOImpl apply = new applicationDAOImpl();
        apply.createApplication(app);
    }
 
    
}