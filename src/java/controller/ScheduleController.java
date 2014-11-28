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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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

    
}
