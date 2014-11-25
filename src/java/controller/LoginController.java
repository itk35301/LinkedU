/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.studentDAOImpl;
import dao.universityDAOImpl;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.studentBean;
import model.universityBean;

/**
 *
 * @author itk35301
 */
@Named(value = "LoginController")
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
            stuModel.setStuID(getUserName());
            stuModel.setPassword(getPassword());
            if (studentBean.authenticate(getStuModel().getStuID(), getStuModel().getPassword())) {
                studentDAOImpl student = new studentDAOImpl();
                stuModel.setProfilePic(student.findProfilePic(stuModel));
           
                return "studentProfile.xhtml";
            }
        } else if (ulogin.exist(userName)) {
            uModel.setId(getUserName());
            uModel.setPassword(getPassword());
            if (universityBean.authenticate(getuModel().getUid(), getuModel().getPassword())) {
                return "profile.xhtml";
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
