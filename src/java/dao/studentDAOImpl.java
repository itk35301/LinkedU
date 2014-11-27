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
import java.util.List;
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
    public ArrayList findAll() {
       String query = "SELECT * FROM LINKEDU.STUDENT";
       ArrayList aStudentCollection = selectProfilesFromDB(query);
       return aStudentCollection;
    }
    
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
           
            insertString = "Insert INTO LINKEDU.STUDENT VALUES ('"
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
    public ArrayList findloginSTUID(String aSTUID) {
       String query = "SELECT * FROM linkedu.STUDENT ";
        query += "WHERE STUID = '" + aSTUID + "'";

        ArrayList aStudentCollection = selectLoginFromStudent(query);
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

    public String findProfilePic(studentBean aStudent) {
       String query = "SELECT * FROM linkedu.STUDENT ";
        query += "WHERE STUID = '" + aStudent.getStuID() + "'";

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
            String profilepic;

            while (rs.next()) {

                profilepic= rs.getString("PROFILEPIC");
             
                return profilepic;
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
       return "";
    
    }
    
    @Override
    public ArrayList selectLoginFromStudent(String query) {
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

                userid = rs.getString("STUID");
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
    public boolean exist(String aSTUID) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {

            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String queryCheck = "SELECT * from LINKEDU.STUDENT WHERE STUID = '" + aSTUID + "'";
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

    @Override
    public ArrayList findByFirstName(String name) {
        String query = "SELECT * FROM LINKEDU.STUDENT";
        query += " WHERE fName = '" + name + "'";
        
        System.out.println(query);
        ArrayList aStudentCollection = selectProfilesFromDB(query);
        return aStudentCollection;
    }
    
    @Override
    public ArrayList findByLastName(String name) {
        String query = "SELECT * FROM LINKEDU.STUDENT";
        query += " WHERE lName = '" + name + "'";
        
        ArrayList aStudentCollection = selectProfilesFromDB(query);
        return aStudentCollection;
    }

    @Override
    public ArrayList findByHighSchool(String highSchool) {
        String query = "SELECT * FROM LINKEDU.STUDENT";
        query += " WHERE highschool LIKE '%" + highSchool + "%'";
         
        ArrayList aStudentCollection = selectProfilesFromDB(query);
        return aStudentCollection;
    }

    @Override
    public ArrayList findByGPA(String lowGpa, String highGpa) {
        String query = "SELECT * FROM LINKEDU.STUDENT";
        query += " WHERE (gpa >= '" + lowGpa + "' AND gpa <= '" + highGpa + "')";
         
        ArrayList aStudentCollection = selectProfilesFromDB(query);
        return aStudentCollection;
    }
    
}
