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
import model.studentBean;


/**
 *
 * @author bjcox
 * This class handles everything that requires a database connection other than logging in.
 * It handles creating new users when they sign up and it handles updating user information.
 * It also handles pre-populating the update page.
 */
public class studentDAOImpl implements studentDAO{
    
    @Override
    public int createStudent(studentBean aStudent){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            if(aStudent.getStuID().contains("'")){
                aStudent.setStuID(aStudent.getStuID().replaceAll("'","''"));
            }
           
            insertString = "Insert INTO linkedu.STUDENT VALUES ('"
                + aStudent.getStuID()
                + "','" + aStudent.getVideogalleryid()
                + "','" + aStudent.getPhotogalleryid()
                + "','" + aStudent.getPassword()   
                + "','" + aStudent.getFname()
                + "','" + aStudent.getLname()
                + "','" + aStudent.getPhone()
                + "','" + aStudent.getEmail()
                + "','" + aStudent.getAbout()
                + "','" + aStudent.getHighschool()
                + "','" + aStudent.getInterests()
                + "','" + aStudent.getMajor()
                + "','" + aStudent.getExperience()
                + "','" + aStudent.getGpa()
                + "','" + aStudent.getProfilePic()
                + "')";
            
            rowCount = stmt.executeUpdate(insertString);
            System.out.println("studentTable insert string =" + insertString);
            
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }

    @Override
    public ArrayList findBySTUID(String aSTUID) {
       String query = "SELECT * FROM linkedu.STUDENT ";
        query += "WHERE STUID = '" + aSTUID + "'";

        ArrayList aStudentCollection = selectProfilesFromDB(query);
        return aStudentCollection;
    }
    

    @Override
    public int updatePassword(studentBean aStudent) {
        Connection DBConn = null;
        int rowCount = 0;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String updateString;
            Statement stmt = DBConn.createStatement();
            updateString = "UPDATE linkedu.STUDENT SET "
                + "password = '" + aStudent.getPassword() + "' "
                + "WHERE STUID = '" + aStudent.getStuID() + "'";
            
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("studentTable update string =" + updateString);
            
           
            DBConn.close();  
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    
 
    
    
    @Override
    public int updateFName(studentBean aStudent) {
        Connection DBConn = null;
        int rowCount = 0;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String updateString;
            Statement stmt = DBConn.createStatement();
            
            updateString = "UPDATE linkedu.STUDENT SET "
                + "FNAME = '" + aStudent.getFname() + "', "
                
                + "WHERE STUID = '" + aStudent.getStuID() + "'";
            
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("studentTable update string =" + updateString);
            
            rowCount = stmt.executeUpdate(updateString);
            DBConn.close();  
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    
    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aStudentCollection = new ArrayList();
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String id, password, profilePic, fname, lname, phone, email, about, highschool, interests, major, experience,gpa, vidgalleryid, photogalleryid;
            
            
            studentBean aStudent;
            while(rs.next()){
                id = rs.getString("STUID");
                vidgalleryid = rs.getString("VIDGALLERYID");
                photogalleryid = rs.getString("PHOTOGALLERYID");
                password = rs.getString("PASSWORD");
                fname = rs.getString("FNAME");
                lname = rs.getString("LNAME");
                about = rs.getString("ABOUT");
                profilePic = rs.getString("PROFILEPIC");
                highschool = rs.getString("HIGHSCHOOL");
                interests = rs.getString("INTERESTS");
                major = rs.getString("MAJOR");
                experience = rs.getString("EXPERIENCE");
                gpa = rs.getString("GPA");
                phone = rs.getString("PHONE");
                email = rs.getString("EMAIL");
                
                aStudent = new studentBean(id, password, fname, lname, phone, email, about, highschool, interests, major, experience, gpa, profilePic, photogalleryid, vidgalleryid);
                aStudentCollection.add(aStudent);
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
        return aStudentCollection;
    }
}
