/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.studentDAO;
import dao.studentDAOImpl;
import dao.universityDAO;
import dao.universityDAOImpl;
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
    private String lowGpa;
    private String highGpa;
    private String universityName;
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
            case "gpa":
                stuList = aStudentDAO.findByGPA(lowGpa, highGpa);
                return "searchResults";
        }
        
        return "error.xhtml";
    }
    
    public String showAll(){
        studentDAO student = new studentDAOImpl();
        stuList = student.findAll();
        return "searchResults";
    }
    
    public String universitySearch(){
        universityDAO aUniversityDAO = new universityDAOImpl();
        switch(searchType){
            case "universityName":
                uList = aUniversityDAO.findByUniversity(universityName);
                return "universityResults";
        }
        
        return "error.xhtml";
    }
    
    public String showAllUniversity(){
        universityDAO university = new universityDAOImpl();
        uList = university.findAll();
        return "universityResults";
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

    /**
     * @return the lowGpa
     */
    public String getLowGpa() {
        return lowGpa;
    }

    /**
     * @param lowGpa the lowGpa to set
     */
    public void setLowGpa(String lowGpa) {
        this.lowGpa = lowGpa;
    }

    /**
     * @return the highGpa
     */
    public String getHighGpa() {
        return highGpa;
    }

    /**
     * @param highGpa the highGpa to set
     */
    public void setHighGpa(String highGpa) {
        this.highGpa = highGpa;
    }

    /**
     * @return the universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName the universityName to set
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    
}
