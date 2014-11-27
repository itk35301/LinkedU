/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.ScheduleBean;

/**
 *
 * @author admin
 */
public interface ScheduleDAO {
   
    public ArrayList findAll();
    public ArrayList findAllEmty();
   
    public int createSchedule(ScheduleBean aSchedule); // either get one back or several if multiple same name allowed  
    
}
