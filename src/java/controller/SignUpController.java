/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.recruiter;
import model.studentBean;
import model.universityBean;

@ManagedBean
@SessionScoped
public class SignUpController implements Serializable {

    private studentBean theStudent;
    private universityBean theUniversity;
    private recruiter theRecruiter;
    private String signUpType;
    private String userName;
    private String password;
    private String email;

    public SignUpController() {
    }
    
    
    public String signUp(){
        System.out.println(signUpType);
        System.out.println(userName);
        System.out.println(password);
        System.out.println(email);
        if(signUpType.equals("student")){
            theStudent.setStuID(userName);
            theStudent.setEmail(email);
            theStudent.setPassword(password);
            return "studentSignUp.xhtml";
        }
        if(signUpType.equals("Recruiter")){
            
        }
        if(signUpType.equals("University")){
            
        }
        
        return "error.xhtml";
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

    public recruiter getTheRecruiter() {
        return theRecruiter;
    }

    public void setTheRecruiter(recruiter theRecruiter) {
        this.theRecruiter = theRecruiter;
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
    
    
    
    
}
