/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmbraul
 */
public class ScheduleBean {
    
    private String timeslot;
    private String groupname;
    private String date;
    private Date mydate;

    /**
     * @return the timeslot
     */
    public ScheduleBean(){
        
    }
    public ScheduleBean(String aDate, String atimeslot, String aGroupname){
        this.timeslot = atimeslot;
        this.groupname = aGroupname;
        this.date = aDate;
        try {
            this.mydate = new SimpleDateFormat("MM/dd/yyyy").parse(aDate);
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String getTimeslot() {
        return timeslot;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * @return the Groupname
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * @param Groupname the Groupname to set
     */
    public void setGroupname(String Groupname) {
        this.groupname = Groupname;
    }

    /**
     * @return the date
     */
    public String getDate() {
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       date = df.format(mydate);
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the mydate
     */
    public Date getMydate() {
        
        return mydate;
    }

    /**
     * @param mydate the mydate to set
     */
    public void setMydate(Date mydate) {
        this.mydate = mydate;
    }
}
