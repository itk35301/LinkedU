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
import model.universityBean;

/**
 *
 * @author bjcox
 * This class handles everything that requires a database connection other than logging in.
 * It handles creating new users when they sign up and it handles updating user information.
 * It also handles pre-populating the update page.
 */
public class universityDAOImpl implements universityDAO{
    
    @Override
    public int createUniversity(universityBean aUniversity){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/linkedu";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            if(aUniversity.getName().contains("'")){
                aUniversity.setName(aUniversity.getName().replaceAll("'","''"));
            }
           
            insertString = "Insert INTO linkedu.UNIVERSITY VALUES ('"
                + aUniversity.getUid()
                + "','" + aUniversity.getVidgalleryid()
                + "','" + aUniversity.getPhotogalleryid()
                + "','" + aUniversity.getPassword()   
                + "','" + aUniversity.getName()
                + "','" + aUniversity.getAbout()
                + "','" + aUniversity.getProfilePic()
                + "','" + aUniversity.isHighlighted()
                + "','" + aUniversity.getLocation()
                + "','" + aUniversity.getAvgTuition()
                + "')";
            
            rowCount = stmt.executeUpdate(insertString);
            System.out.println("universityTable insert string =" + insertString);
            
            
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
    
        @Override
    public ArrayList findHighlighted() {
       String query = "SELECT * FROM linkedu.UNIVERSITY ";
        query += "WHERE HIGHLIGHTED = 'true'";

        ArrayList aUniversityCollection = selectProfilesFromDB(query);
        return aUniversityCollection;
    }

    @Override
    public int updatePassword(universityBean aUniversity) {
        Connection DBConn = null;
        int rowCount = 0;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/linkedu";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String updateString;
            Statement stmt = DBConn.createStatement();
            updateString = "UPDATE linkedu.UNIVERSITY SET "
                + "password = '" + aUniversity.getPassword() + "' "
                + "WHERE UID = '" + aUniversity.getUid() + "'";
            
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("universityTable update string =" + updateString);
            
           
            DBConn.close();  
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    
 
    
    
    @Override
    public int updateName(universityBean aUniversity) {
        Connection DBConn = null;
        int rowCount = 0;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/linkedu";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String updateString;
            Statement stmt = DBConn.createStatement();
            
            updateString = "UPDATE linkedu.UNIVERSITY SET "
                + "UNAME = '" + aUniversity.getName() + "', "
                
                + "WHERE UID = '" + aUniversity.getUid() + "'";
            
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("universityTable update string =" + updateString);
            
            rowCount = stmt.executeUpdate(updateString);
            DBConn.close();  
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    
    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aUniversityCollection = new ArrayList();
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/linkedu";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String id, password, profilePic, uName, location, avgTuition, highlighted, about, vidgalleryid, photogalleryid;
            
            
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
                
                aUniversity = new universityBean(id, password, profilePic, uName, location, avgTuition, highlighted, about, vidgalleryid, photogalleryid);
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
