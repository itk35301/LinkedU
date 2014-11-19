/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.studentBean;
import model.universityBean;
import model.recruiter;
/**
 *
 * @author itk35301
 */
@Named(value = "LoginController")
@SessionScoped
public class LoginController implements Serializable {
    private studentBean stuModel;
    private universityBean uModel;
    private recruiter recModel;
    int Attempts = 0;
    
    public LoginController() {
        stuModel = new studentBean();
        uModel = new universityBean();
        recModel = new recruiter();
    }
   
    public String login() {       

        if (studentBean.authenticate(getStuModel().getStuID(), getStuModel().getPassword())) {
            return "profile.xhtml";
        } 
        else if (universityBean.authenticate(getuModel().getUid(), getuModel().getPassword())) {
            return "profile.xhtml";
        }
        else if (recruiter.authenticate(getRecModel().getRecid(), getRecModel().getPassword())) {
            return "profile.xhtml";
        }
        else {
            Attempts++;
            if (Attempts >= 3) {
                return "loginlocked.xhtml";
            } else {
                return "LoginBad.xhtml";
            }

        }

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

    public recruiter getRecModel() {
        return recModel;
    }

    public void setRecModel(recruiter recModel) {
        this.recModel = recModel;
    }
}
