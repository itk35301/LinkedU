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

import model.PasswordRetrievalBean;

/**
 *
 * @author Huan_Nguyen
 */
public class PasswordRetrieveDAOImpl implements PasswordRetrieveDAO {
    private PasswordRetrievalBean passRetBean;
    
    public void retrievePass(PasswordRetrievalBean passRetBean) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM LINKEDU.STUDENT WHERE STUID = '" + passRetBean.getUsername() + "' AND EMAIL = '" + passRetBean.getEmail() + "'");

            while (rs.next()) {
                passRetBean.setPassword(rs.getString("PASSWORD"));
            }
            rs.close();
            stmt.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
