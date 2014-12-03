/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named; 

import dao.PasswordRetrieveDAOImpl;
import model.PasswordRetrievalBean;

/**
 *
 * @author Huan_Nguyen
 */
@Named(value = "PasswordRetrieveController")
@SessionScoped
public class PasswordRetrieveController implements Serializable {
    private String passwordUsername;
    private String passwordEmail;
    private PasswordRetrievalBean theBean;
    private PasswordRetrieveDAOImpl dao;
    private String message;
    
    public String retrievePass() {
        theBean = new PasswordRetrievalBean(passwordUsername, passwordEmail, "");
        dao = new PasswordRetrieveDAOImpl();
       
        dao.retrievePass(theBean);
        
        setMessage(configureMessage());
        
        return "passwordRetrieveConfirm.xhtml";
    }
    
    private String configureMessage() {
        String theMessage = "";
        
        if (theBean.getPassword().isEmpty()) {
            theMessage += "Your Username or Email is invalid";
        }
        else {
            theMessage += "Your Password is " + theBean.getPassword();
        }
        
        return theMessage;
    }
    
    /**
     * @return the passwordUsername
     */
    public String getPasswordUsername() {
        return passwordUsername;
    }

    /**
     * @param passwordUsername the passwordUsername to set
     */
    public void setPasswordUsername(String passwordUsername) {
        this.passwordUsername = passwordUsername;
    }

    /**
     * @return the passwordEmail
     */
    public String getPasswordEmail() {
        return passwordEmail;
    }

    /**
     * @param passwordEmail the passwordEmail to set
     */
    public void setPasswordEmail(String passwordEmail) {
        this.passwordEmail = passwordEmail;
    }

    /**
     * @return the theBean
     */
    public PasswordRetrievalBean getTheBean() {
        return theBean;
    }

    /**
     * @param theBean the theBean to set
     */
    public void setTheBean(PasswordRetrievalBean theBean) {
        this.theBean = theBean;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
