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

    /**
     * @return the timeslot
     */
    public ScheduleBean(){
        
    }
    public ScheduleBean(String atimeslot, String aGroupname){
        this.timeslot = atimeslot;
        this.groupname = aGroupname;
        
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
}
