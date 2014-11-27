/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author kmbraul
 */
public class ScheduleBean {
    
    private String timeslot;
    private String groupname;
    private String date;

    /**
     * @return the timeslot
     */
    public ScheduleBean(){
        
    }
    public ScheduleBean(String aDate, String atimeslot, String aGroupname){
        this.timeslot = atimeslot;
        this.groupname = aGroupname;
        this.groupname = aDate;
        
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
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
}
