/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author kmbraul
 */
import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.ScheduleBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kmbraul
 */
@Named(value = "scheduleController")
@SessionScoped
public class ScheduleController implements Serializable {

    private ArrayList timeSlots;
    private ScheduleBean theModel;
    

    public ScheduleController() {
        theModel = new ScheduleBean();
    }

    public ArrayList getTimeSlots() {
        ScheduleDAO aScheduleDAO = new ScheduleDAOImpl();
        this.timeSlots = aScheduleDAO.findAllEmty();
        return timeSlots;
    }

    public ScheduleBean getTheModel() {
        return theModel;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTheModel(ScheduleBean aModel) {
        this.theModel = aModel;
    }

    public String setSchedule() {
        ScheduleDAO aScheduleDAO = new ScheduleDAOImpl();

        if (aScheduleDAO.findAll().contains(theModel.getGroupname())) {

        }

        int rowCount = aScheduleDAO.createSchedule(theModel); // Doing anything with the object after this?

        if (rowCount == 1) {
            sendEmail();
            return "confirmation.xhtml"; // navigate to "ECHO.xhtml"
        } else if (rowCount == 2) {
            return "nolonger.xhtml";
        } else {
            return "error.xhml";
        }
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
    public void sendEmail(){
        // Recipient's email ID needs to be mentioned.
        String to = "kmbraul@ilstu.edu";

        // Sender's email ID needs to be mentioned
        String from = "Administrator@linkedU.com";

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
            message.setContent("<h1>Your Appointment " + theModel.getGroupname() + " will be on " + theModel.getDate() + " at " + theModel.getTimeslot() + ".<h1>",
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    
}
