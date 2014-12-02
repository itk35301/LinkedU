/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.studentDAO;
import dao.studentDAOImpl;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.studentBean;
import model.universityBean;

@ManagedBean
@SessionScoped
public class SignUpController implements Serializable {

    private studentBean theStudent;
    private universityBean theUniversity;
    private String signUpType;
    private String userName;
    private String password;
    private String email;
    private String passwordConfirm;

    public SignUpController() {
        theStudent = new studentBean();
        theUniversity = new universityBean();
    }
    
    
    public String signUpPartOne(){
        switch (signUpType) {
            case "student":
                theStudent.setStuID(userName);
                theStudent.setEmail(email);
                return "studentSignUp.xhtml";
            case "university":
                break;
        }
        return "error.xhtml";
    }
    
    public String signUpComplete(){
        studentDAO aStudentDAO = new studentDAOImpl();
        int rowCount = aStudentDAO.createStudent(theStudent);
        if (rowCount == 1){
            return "goodSignUp.xhtml";
        }else{
            return "There was an error.";
        }
    }

    public studentBean getTheStudent() {
        return theStudent;
    }

    public void setTheStudent(studentBean theStudent) {
        this.theStudent = theStudent;
    }

    public String getSignUpType() {
        return signUpType;
    }

    public void setSignUpType(String signUpType) {
        this.signUpType = signUpType;
    }

    public universityBean getTheUniversity() {
        return theUniversity;
    }

    public void setTheUniversity(universityBean theUniversity) {
        this.theUniversity = theUniversity;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    
    
    
}
