/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.universityDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.universityBean;


/**
 *
 * @author itmacuser
 */
@Named(value = "inforequestController")
@SessionScoped
public class InfoRequestController implements Serializable {
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String preference;
    private universityBean uModel;
    private String message;

    /**
     * @return the fname
     */
    
    public String getMessage() {
        return message;
    }
    public void handleEvent(AjaxBehaviorEvent event) {
        message = "Thank you for your interest, you will hear back shortly!";
    }
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the preference
     */
    public String getPreference() {
        return preference;
    }

    /**
     * @param preference the preference to set
     */
    public void setPreference(String preference) {
        this.preference = preference;
    }
    public void sendEmail(){
        
        String uID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchedUni");
        universityDAOImpl ulogin = new universityDAOImpl();
        ArrayList uniCollection = ulogin.findByUID(uID);
        this.uModel = (universityBean)uniCollection.get(0);


        // Recipient's email ID needs to be mentioned.
        String to = uModel.getEmail();

        // Sender's email ID needs to be mentioned
        String from = this.email;

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "kmbraul@ilstu.edu"); // if needed
        properties.setProperty("mail.password", "ppiceee8"); // if needed

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Appointment !");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>Student Request for Information: </br> First Name: " 
                    + this.fname
                    + "<br/> Last Name: " + this.lname 
                    + "<br/> Email: " + this.email
                    + "<br/> Phone: " + this.phone
                    + "<br/> Preffered Contact Method: " + this.preference,
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
            
}
