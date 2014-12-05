/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.studentDAOImpl;
import dao.universityDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.studentBean;
import model.universityBean;

/**
 *
 * @author itk35301
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private studentBean stuModel;
    private universityBean uModel;
    private String userName;
    private String password;
    int Attempts = 0;

    public LoginController() {
        stuModel = new studentBean();
        uModel = new universityBean();
    }

    public String login() {
        studentDAOImpl stulogin = new studentDAOImpl();
        universityDAOImpl ulogin = new universityDAOImpl();
        if (stulogin.exist(userName)) {
            if (studentBean.authenticate(userName, password)) {
                ArrayList studentCollection = stulogin.findBySTUID(userName);
                stuModel = (studentBean)studentCollection.get(0);
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInStu", stuModel.getStuID());

                
           
                return "studentProfile.xhtml";
            }
        } else if (ulogin.exist(userName)) {
            if (universityBean.authenticate(userName, password)) {
                ArrayList uniCollection = ulogin.findByUID(userName);
                uModel = (universityBean)uniCollection.get(0);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUni", uModel.getUid());
                
                return "universityProfile.xhtml";
            }
        } else {
            Attempts++;
            if (Attempts >= 3) {
                return "loginlocked.xhtml";
            }
        }
        return "LoginBad.xhtml";

    }
    
    public studentBean getStuModel() {
        return stuModel;
    }

    public void setStuModel(studentBean stuModel) {
        this.stuModel = stuModel;
    }

    public universityBean getuModel() {
        return uModel;
    }

    public void setuModel(universityBean uModel) {
        this.uModel = uModel;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
