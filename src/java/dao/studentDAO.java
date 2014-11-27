/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.studentBean;

/**
 *
 * @author bjcox
 */
public interface studentDAO {
    
    public ArrayList findAll();
    public int createStudent(studentBean aStudent);
    public ArrayList findBySTUID(String aSTUID);
    public int updatePassword(studentBean aStudent);
    public int updateFName(studentBean aStudent);
    public ArrayList selectLoginFromStudent(String query);
    public ArrayList findloginSTUID(String aSTUID);
    public boolean exist(String aSTUID);
    public ArrayList findByFirstName(String name);
    public ArrayList findByLastName(String name);

    public ArrayList findByHighSchool(String highSchool);
  
}
