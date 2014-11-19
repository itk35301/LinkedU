/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.studentBean;

/**
 *
 * @author bjcox
 */
public interface studentDAO {
    
    public int createStudent(studentBean aStudent);
    public ArrayList findBySTUID(String aSTUID);
    public int updatePassword(studentBean aStudent);
    public int updateFName(studentBean aStudent);
    public ArrayList selectLoginFromStudent(String query);
    public ArrayList findloginSTUID(String aSTUID);
    public boolean exist(String aSTUID);
  
}
