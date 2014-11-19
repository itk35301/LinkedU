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
import model.recruiter;


/**
 *
 * @author bjcox
 * This class handles everything that requires a database connection other than logging in.
 * It handles creating new users when they sign up and it handles updating user information.
 * It also handles pre-populating the update page.
 */
public class recruiterDAOImpl implements recruiterDAO{
    
    @Override
    public int createRecruiter(recruiter aRecruiter){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "recruiter");
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            if(aRecruiter.getRecid().contains("'")){
                aRecruiter.setRecid(aRecruiter.getRecid().replaceAll("'","''"));
            }
           
            insertString = "Insert INTO linkedu.STUDENT VALUES ('"
                + aRecruiter.getRecid()
                + "','" + aRecruiter.getUid()
                + "','" + aRecruiter.getVidgalleryid()
                + "','" + aRecruiter.getPhotogalleryid()
                + "','" + aRecruiter.getPassword()   
                + "','" + aRecruiter.getFname()
                + "','" + aRecruiter.getLname()
                + "','" + aRecruiter.getAddress()
                + "','" + aRecruiter.getPhone()
                + "','" + aRecruiter.getEmail()
                + "','" + aRecruiter.getUniversity()         
                + "','" + aRecruiter.getProfilePic()
                + "')";
            
            rowCount = stmt.executeUpdate(insertString);
            System.out.println("recruiterTable insert string =" + insertString);
            
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }

    @Override
    public ArrayList findByRECID(String aRECID) {
       String query = "SELECT * FROM linkedu.RECRUITER ";
        query += "WHERE RECID = '" + aRECID + "'";

        ArrayList aRecruiterCollection = selectProfilesFromDB(query);
        return aRecruiterCollection;
    }
    

    @Override
    public int updatePassword(recruiter aRecruiter) {
        Connection DBConn = null;
        int rowCount = 0;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "recruiter");
            
            String updateString;
            Statement stmt = DBConn.createStatement();
            updateString = "UPDATE linkedu.STUDENT SET "
                + "password = '" + aRecruiter.getPassword() + "' "
                + "WHERE STUID = '" + aRecruiter.getRecid() + "'";
            
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("recruiterTable update string =" + updateString);
            
           
            DBConn.close();  
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    
 
    
    
    @Override
    public int updateFName(recruiter aRecruiter) {
        Connection DBConn = null;
        int rowCount = 0;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "recruiter");
            
            String updateString;
            Statement stmt = DBConn.createStatement();
            
            updateString = "UPDATE linkedu.STUDENT SET "
                + "FNAME = '" + aRecruiter.getFname() + "', "
                
                + "WHERE STUID = '" + aRecruiter.getRecid() + "'";
            
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("recruiterTable update string =" + updateString);
            
            rowCount = stmt.executeUpdate(updateString);
            DBConn.close();  
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
    
    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aRecruiterCollection = new ArrayList();
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "recruiter");
            
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String recid, uid, vidgalleryid, photogalleryid, password, fname, lname,  address, phone, email, university, profilePic;
            
            
            recruiter aRecruiter;
            while(rs.next()){
                recid = rs.getString("RECID");
                uid = rs.getString("UID");
                vidgalleryid = rs.getString("VIDGALLERYID");
                photogalleryid = rs.getString("PHOTOGALLERYID");
                password = rs.getString("PASSWORD");
                fname = rs.getString("FNAME");
                lname = rs.getString("LNAME");
                address = rs.getString("ADDRESS");
                profilePic = rs.getString("PROFILEPIC");
                university = rs.getString("UNIVERSITY");
                phone = rs.getString("PHONE");
                email = rs.getString("EMAIL");
                
                aRecruiter = new recruiter(recid, uid, vidgalleryid, photogalleryid, password, fname, lname,  address, phone, email, university, profilePic);
                aRecruiterCollection.add(aRecruiter);
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
        return aRecruiterCollection;
    }
     @Override
    public ArrayList findloginRECID(String aRECID) {
       String query = "SELECT * FROM linkedu.RECRUITER ";
        query += "WHERE RECID = '" + aRECID + "'";

        ArrayList aRecruiterCollection = selectLoginFromRecruiter(query);
        return aRecruiterCollection;
    }
    
    @Override
    public ArrayList selectLoginFromRecruiter(String query) {
        ArrayList aLoginCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userid, password;

            while (rs.next()) {

                userid = rs.getString("RECID");
                password = rs.getString("PASSWORD");

                aLoginCollection.add(userid);
                aLoginCollection.add(password);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aLoginCollection;
    }
    @Override
    public boolean exist(String aRECID) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {

            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String queryCheck = "SELECT * from LINKEDU.RECRUITER WHERE RECID = '" + aRECID + "'";
            Statement st = DBConn.createStatement();
            ResultSet rs = st.executeQuery(queryCheck); // execute the query, and get a java resultset
            // if this ID already exists, we quit
            if (rs.next()) {
                DBConn.close();
               return true;
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}

