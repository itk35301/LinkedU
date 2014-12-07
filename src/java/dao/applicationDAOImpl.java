/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import model.application;
import model.studentBean;
import model.universityBean;


/**
 *
 * @author bjcox
 * This class handles everything that requires a database connection other than logging in.
 * It handles creating new users when they sign up and it handles updating user information.
 * It also handles pre-populating the update page.
 */
public class applicationDAOImpl implements applicationDAO{
    private studentBean stuModel;
    private universityBean uModel;
    
    @Override
    public int createApplication(application application){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        int rowCount = 0;
        try {
            
        studentDAOImpl stulogin = new studentDAOImpl();
        String  stuID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInStu");
        System.out.print("The sessionID passed in searched is " + stuID);
        ArrayList studentCollection = stulogin.findBySTUID(stuID);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loggedInStu");
        stuModel = (studentBean)studentCollection.get(0);
        
        String uID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchedUni");
        universityDAOImpl ulogin = new universityDAOImpl();
        ArrayList uniCollection = ulogin.findByUID(uID);
        uModel = (universityBean)uniCollection.get(0);
        
        
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            
           
            insertString = "Insert INTO linkedu.APPLICATION VALUES ('"
                + stuModel.getStuID()
                + "','" + application.getLetter()
                + "','" + application.getDate()
                + "','" + uModel.getUid()
                + "','" + stuModel.getFname()
                + "','" + stuModel.getLname()
                + "','" + stuModel.getEmail()
                + "')";
            
            rowCount = stmt.executeUpdate(insertString);
            System.out.println("applicationTable insert string =" + insertString);
            
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }

    @Override
    public ArrayList findByUID(String aUniversityID) {
       String query = "SELECT * FROM linkedu.UNIVERSITY ";
        query += "WHERE UID = '" + aUniversityID + "'";

        ArrayList aUniversityCollection = selectProfilesFromDB(query);
        return aUniversityCollection;
    }

    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aUniversityCollection = new ArrayList();
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String id, password, profilePic, uName, location, avgTuition, highlighted, about, vidgalleryid, photogalleryid, phone, email, city, state;
            
            
            universityBean aUniversity;
            while(rs.next()){
                id = rs.getString("UID");
                vidgalleryid = rs.getString("VIDGALLERYID");
                photogalleryid = rs.getString("PHOTOGALLERYID");
                password = rs.getString("PASSWORD");
                uName = rs.getString("UNAME");
                about = rs.getString("ABOUT");
                profilePic = rs.getString("PROFILEPIC");
                highlighted = rs.getString("HIGHLIGHTED");
                location = rs.getString("LOCATION");
                avgTuition = rs.getString("AVGTUITION");
                phone = rs.getString("PHONE");
                email = rs.getString("EMAIL");
                city = rs.getString("CITY");
                state = rs.getString("STATE");
                
                aUniversity = new universityBean(id, password, profilePic, uName, location, avgTuition, highlighted, about, vidgalleryid, photogalleryid, phone, email, city, state);
                aUniversityCollection.add(aUniversity);
            }
            rs.close();
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try{
            DBConn.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aUniversityCollection;
    }
    
   
   
}

