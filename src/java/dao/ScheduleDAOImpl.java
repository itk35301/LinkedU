    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.ScheduleBean;

/**
 *
 * @author admin
 */
public class ScheduleDAOImpl implements ScheduleDAO {

    @Override
    public int createSchedule(ScheduleBean aSchedule) {
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

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LINKEDU.Schedule");
            
            String date, timeslot,groupname;
            
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                timeslot = rs.getString("TimeSlot");
                groupname = rs.getString("GroupName");
                date = rs.getString("Date");

                if (timeslot.equals(aSchedule.getTimeslot()) && date.equals(aSchedule.getDate()) && groupname != null) {
                    return 2;
                }
                
            }
            rs.close();
            stmt.close();
            
            
            String insertString1;
            Statement stmt1 = DBConn.createStatement();
            insertString1 = "DELETE FROM LINKEDU.Schedule WHERE TimeSlot = '"
                    + aSchedule.getTimeslot()
                    + "'";
            rowCount = stmt1.executeUpdate(insertString1);
            
            
            
            String insertString2;
            Statement stmt2 = DBConn.createStatement();
            insertString2 = "INSERT INTO LINKEDU.Schedule VALUES ('"
                    + aSchedule.getTimeslot()
                    + "','" + aSchedule.getGroupname()
                    + "')";

            rowCount = stmt2.executeUpdate(insertString2);
            System.out.println("insert string =" + insertString2);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public ArrayList findAll() {

        String query = "SELECT * FROM LINKEDU.Schedule";
        ArrayList aScheduleCollection = selectSchedulesFromDB(query);
        return aScheduleCollection;

    }

    private ArrayList selectSchedulesFromDB(String query) {
        ArrayList aScheduleBeanCollection = new ArrayList();
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
            String date,timeslot, groupname;
            ScheduleBean aScheduleBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                timeslot = rs.getString("TimeSlot");
                groupname = rs.getString("GroupName");
                date = rs.getString("Date");

                // make a ProfileBean object out of the values
                aScheduleBean = new ScheduleBean(date, timeslot, groupname);
                // add the newly created object to the collection
                aScheduleBeanCollection.add(aScheduleBean);
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
        return aScheduleBeanCollection;
    }
    @Override
    public ArrayList findAllEmty() {

        String query = "SELECT * FROM LINKEDU.Schedule";
        ArrayList aScheduleCollection = selectSchedulesFromDBNoGroup(query);
        return aScheduleCollection;

    }
    
   

    private ArrayList selectSchedulesFromDBNoGroup(String query) {
        ArrayList aScheduleBeanCollection = new ArrayList();
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
            String date, timeslot, groupname;
            ScheduleBean aScheduleBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                timeslot = rs.getString("TimeSlot");
                groupname = rs.getString("GroupName");
                date = rs.getString("Date");

                if (groupname == null) {

                    // make a ProfileBean object out of the values
                    aScheduleBean = new ScheduleBean(date, timeslot, groupname);
                    // add the newly created object to the collection
                    aScheduleBeanCollection.add(aScheduleBean);

                }
                
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
        return aScheduleBeanCollection;
    }

}
