/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.studentDAO;
import dao.studentDAOImpl;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import model.studentBean;
import model.universityBean;

/**
 *
 * @author Rurach
 */
@Named(value = "searchController")
@SessionScoped
public class SearchController implements Serializable {
    private String searchType;
    private String firstName;
    private String lastName;
    private String highSchool;
    private List<studentBean> stuList;
    private List<universityBean> uList;

    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
    }

    public String studentSearch(){
        studentDAO aStudentDAO = new studentDAOImpl();
        switch(searchType){
            case "fName":
                stuList = aStudentDAO.findByFirstName(firstName);
                return "searchResults";
            case "lName":
                stuList = aStudentDAO.findByLastName(lastName);
                return "searchResults";
            case "highSchool":
                stuList = aStudentDAO.findByHighSchool(highSchool);
                return "searchResults";
        }
                return "error.xhtml";
    }
    
    public String showAll(){
        studentDAO student = new studentDAOImpl();
        stuList = student.findAll();
        return "searchResults";
    }
    
    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<studentBean> getStuList() {
        return stuList;
    }

    public void setStuList(List<studentBean> stuList) {
        this.stuList = stuList;
    }

    public List<universityBean> getuList() {
        return uList;
    }

    public void setuList(List<universityBean> uList) {
        this.uList = uList;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }
    
    
    
}
